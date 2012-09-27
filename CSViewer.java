package csviewer;
/**
 *
 * @author ahmedhussein
 */

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;
import java.awt.*;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import java.io.*;
import java.util.ArrayList;
import static java.lang.Math.*;

public class CSViewer
{
    Toolkit oToolKit = Toolkit.getDefaultToolkit();
    Dimension oScreenDimensions = oToolKit.getScreenSize();
    private int DisplayWidth = (int)(0.8*(double)oScreenDimensions.width);
    private int DisplayHeight = (int)(0.8*(double)oScreenDimensions.height);
    
    public static void main(String[] args)
    {
        CSViewer MainWindow = null;
        String sFileName = "";
        try
        {   
            sFileName = args[0];
            System.out.println("The animation file name is "+sFileName);
            int iFrameRate = Integer.parseInt(args[1]);
            MainWindow = new CSViewer(sFileName,iFrameRate);
            MainWindow.Create();
            System.out.println("viewer object created ");
            MainWindow.Run();
        }
        catch(Exception oException)
        {
            System.out.println("problem creating visualizer window ... exiting");
            return;
        }
        finally
        {
            if(MainWindow != null)
            {
                MainWindow.Destroy();
            }
        }
    }
    public CSViewer(String sFileName,int iFrameRate) throws IOException
    {
        InitializeTransformations();
        m_sFileName = sFileName;
        m_oAnimation = new Animation();
        ReadInput();
        m_iFrameRate = iFrameRate;
    }
    public void ReadInput() throws IOException
    {
        System.out.println("Reading file " + m_sFileName);
        System.out.println("Animation file found");
        m_oAnimation.Read(m_sFileName);
    }
    private void Create() throws LWJGLException,InterruptedException,IOException
    {
        //Display
        Display.setDisplayMode(new DisplayMode(DisplayWidth,DisplayHeight));
        Display.setFullscreen(false);
        Display.setTitle("CSViewer");
        Display.create();
        //Keyboard
        Keyboard.create(); 
        //Mouse
        Mouse.setGrabbed(false);
        Mouse.create();
        //OpenGL
        InitializeGL();
        BuildAnimation();
    }
    private void Run() throws InterruptedException
    {   
        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE))
        {   
            if(Display.isVisible())
            {   
                if(Mouse.isButtonDown(0))
                {
                    if(!LeftMouseButtonDown)
                    {
                        MouseDownX = Mouse.getX();
                        MouseDownY = Mouse.getY();
                        MouseDownX = MouseDownX - 0.5f*((float)Display.getDisplayMode().getWidth());
                        MouseDownY = -MouseDownY + 0.5f*((float)Display.getDisplayMode().getHeight());
                        LeftMouseButtonDown = true;
                        RightMouseButtonDown = false;  
                    } 
                }
                else if(Mouse.isButtonDown(1))
                {
                    if(!RightMouseButtonDown)
                    {
                        MouseDownX = Mouse.getX();
                        MouseDownY = Mouse.getY();
                        MouseDownX = MouseDownX - 0.5f*((float)Display.getDisplayMode().getWidth());
                        MouseDownY = -MouseDownY + 0.5f*((float)Display.getDisplayMode().getHeight());
                        LeftMouseButtonDown = false;
                        RightMouseButtonDown = true;  
                    }
                }
                else
                {
                    LeftMouseButtonDown = false;
                    RightMouseButtonDown = false; 
                }
                ProcessMouse();
                Draw();
            }
            else
            {
                if(Display.isDirty())
                {
                    Draw();
                }
                try
                {
                    Thread.sleep(100);
                }
                catch(InterruptedException ex)
                {

                }
            }
            Display.update();
            Display.sync(60);
        }
    }
    private void Destroy()
    {
        Mouse.destroy();
        Keyboard.destroy();
        Display.destroy();
    }
    public void InitializeGL() throws IOException
    {   
        EyeX = 2.0f;
        EyeY = 2.0f;
        EyeZ = 0.0f;
        
        TargetX = 0.0f;
        TargetY = 0.0f;
        TargetZ = 0.0f;
         
        ScreenUpX = 0.0f;
        ScreenUpY = 0.0f;
        ScreenUpZ = 1.0f;
         
	FieldOfView = 120.0f;
	AspectRatio = (float)DisplayWidth/(float)DisplayHeight;
	NearField = 0.1f;
	FarField = 400.0f;
         
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glViewport(0,0,DisplayWidth,DisplayHeight);
	glClearColor(1.0f,0.0f,0.0f,0.0f);
        glEnable(GL_DEPTH_TEST);
	glDepthFunc(GL_LEQUAL);

	SetPerspective();
	LookAt(EyeX,EyeY,EyeZ,TargetX,TargetY,TargetZ,ScreenUpX,ScreenUpY,ScreenUpZ);
	ClearAll();
    }
    public void SetPerspective()
    {
        gluPerspective(FieldOfView,AspectRatio,NearField,FarField);
    } 
    protected void SetPerspective(float dAngle,float dAspectRatio,float dNearField,float dFarField)
    {
        FieldOfView = dAngle;
        AspectRatio = dAspectRatio;
        NearField = dNearField;
        FarField = dFarField;
        gluPerspective(FieldOfView,AspectRatio,NearField,FarField);
    }
    double GetFieldOfView()
    {
	return FieldOfView;
    }
    double GetAspectRatio()
    {
	return AspectRatio;
    }
    void ClearAll()
    {
	glClearColor(0.0f,0.0f,0.0f,0.0f);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	Display.update();
    }
    protected void InitializeTransformations()
    {
	MouseDownX = 0.0f;
	MouseDownY = 0.0f;
	CurrentMouseX = 0.0f;
	CurrentMouseY = 0.0f;
	LeftMouseButtonDown = false;
	RightMouseButtonDown = false;
    }
    protected void LookAt(float dEyeX,float dEyeY,float dEyeZ,float dTargetX,float dTargetY,float dTargetZ,float dScreenUpX,float dScreenUpY,float dScreenUpZ)
    {
	EyeX = dEyeX;
	EyeY = dEyeY;
	EyeZ = dEyeZ;

	TargetX = dTargetX;
	TargetY = dTargetY;
	TargetZ = dTargetZ;

	ScreenUpX = dScreenUpX;
	ScreenUpY = dScreenUpY;
	ScreenUpZ = dScreenUpZ;

	gluLookAt(EyeX,EyeY,EyeZ,TargetX,TargetY,TargetZ,ScreenUpX,ScreenUpY,ScreenUpZ);
    }
    public void ProcessMouse() throws InterruptedException
    { 
        if(LeftMouseButtonDown || RightMouseButtonDown)
        {
            CurrentMouseX = Mouse.getX();
            CurrentMouseY = Mouse.getY();
            CurrentMouseX = CurrentMouseX - 0.5f*((float)Display.getDisplayMode().getWidth());
	    CurrentMouseY = -CurrentMouseY + 0.5f*((float)Display.getDisplayMode().getHeight());
            if(LeftMouseButtonDown)
            {
                RealRotateView();
            }
            else if(RightMouseButtonDown)
            {
                TranslateView();
            }
            MouseDownX = CurrentMouseX;
            MouseDownY = CurrentMouseY;
            return;
        }
    }
    private void RotateView() throws InterruptedException
    {
        double dStartX = 2.0*MouseDownX/DisplayWidth - 1.0;
        double dStartY = 2.0*MouseDownY/DisplayHeight - 1.0;
        double dStartZ = 0.0;

        double dTemp = dStartX*dStartX + dStartY*dStartY;

        if(dTemp <= 1.0)
        {
            dStartZ = sqrt(1.0 - dTemp);
        }
        else
        {
            dStartX = dStartX/dTemp;
            dStartY = dStartY/dTemp;
        }
        
        double dEndX = 2.0*CurrentMouseX/DisplayWidth - 1.0;
        double dEndY = 2.0*CurrentMouseY/DisplayHeight - 1.0;
        double dEndZ = 0.0;

        dStartY = -dStartY;
        dEndY = -dEndY;

        double dAxisX = (dStartY*dEndZ - dStartZ*dEndY);
        double dAxisY = (dStartZ*dEndX - dStartX*dEndZ);
        double dAxisZ = (dStartX*dEndY - dStartY*dEndX);

        double dAxisNorm = sqrt(dAxisX*dAxisX + dAxisY*dAxisY + dAxisZ*dAxisZ);
        if(dAxisNorm < 1E-6)
        {
            return;
        }
        double dAngle = (dStartX*dEndX + dStartY*dEndY + dStartZ*dEndZ);
        glRotatef(5.0f*(float)dAngle,(float)dAxisX,(float)dAxisY,(float)dAxisZ);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        Draw();
    }
    private void RealRotateView() throws InterruptedException
    {
        IntBuffer iaViewPort = BufferUtils.createIntBuffer(16);
        FloatBuffer daModelView = BufferUtils.createFloatBuffer(16);
        FloatBuffer daProjection = BufferUtils.createFloatBuffer(16);
        FloatBuffer winZ = BufferUtils.createFloatBuffer(16);
        float winX = 0.0f;
        float winY = 0.0f;
        FloatBuffer daStart = BufferUtils.createFloatBuffer(16);
        FloatBuffer daEnd = BufferUtils.createFloatBuffer(16);
        glGetFloat(GL_MODELVIEW_MATRIX,daModelView);
        glGetFloat(GL_PROJECTION_MATRIX,daProjection);
        glGetInteger(GL_VIEWPORT,iaViewPort);

        winX = (float)MouseDownX;
        winY = (float)MouseDownY;
        glReadPixels((int)MouseDownX,(int)MouseDownY,1,1,GL_DEPTH_COMPONENT,GL_FLOAT,winZ);
        gluUnProject(winX,winY,winZ.get(),daModelView,daProjection,iaViewPort,daStart);
    
        winX = (float)CurrentMouseX;
        winY = (float)CurrentMouseY;
        glReadPixels((int)CurrentMouseX,(int)CurrentMouseY,1,1,GL_DEPTH_COMPONENT,GL_FLOAT,winZ);
        gluUnProject(winX,winY,winZ.get(),daModelView,daProjection,iaViewPort,daEnd);
 
        double dStartX = daStart.get(0);
        double dStartY = daStart.get(1);
        double dStartZ = daStart.get(2);
        double dEndX = daEnd.get(0);
        double dEndY = daEnd.get(1);
        double dEndZ = daEnd.get(2);

        dStartY = -dStartY;
        dEndY = -dEndY;

        double dStartPointNorm = Math.sqrt(dStartX*dStartX + dStartY*dStartY + dStartZ*dStartZ);
        double dEndPointNorm = Math.sqrt(dEndX*dEndX + dEndY*dEndY + dEndZ*dEndZ);

        double dAxisX = (dStartY*dEndZ - dStartZ*dEndY)/dStartPointNorm/dEndPointNorm;
        double dAxisY = (dStartZ*dEndX - dStartX*dEndZ)/dStartPointNorm/dEndPointNorm;
        double dAxisZ = (dStartX*dEndY - dStartY*dEndX)/dStartPointNorm/dEndPointNorm;

        double dAxisNorm = Math.sqrt(dAxisX*dAxisX + dAxisY*dAxisY + dAxisZ*dAxisZ);
        if(dAxisNorm < 1E-6)
        {
            return;
        }
        double dAngle = (dStartX*dEndX + dStartY*dEndY + dStartZ*dEndZ)/dStartPointNorm/dEndPointNorm;
        glRotatef(5.0f*(float)dAngle,(float)dAxisX,(float)dAxisY,(float)dAxisZ);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        Draw();
    }
    private void TranslateView() throws InterruptedException
    {
	double dSpeedFactor = 0.01;
	glTranslated(dSpeedFactor*(CurrentMouseX - MouseDownX),dSpeedFactor*(CurrentMouseY - MouseDownY),0.0);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	Draw();
    }
    void BuildAnimation()
    {
        m_oAnimation.Build();
	//m_iDrawingListIndex = glGenLists(1);
	//glNewList(m_iDrawingListIndex,GL_COMPILE);
        //oScene.DrawFrame();
	//oScene.Draw();
	//glEndList();
	//m_bDrawingListCreated = true;
    }
    void Draw() throws InterruptedException
    {   
        m_oAnimation.Play(m_iFrameRate);
	//if(m_bDrawingListCreated)
	//{
        //    glCallList(m_iDrawingListIndex);
        //    Display.update();
	//}
    }

    private float MouseDownX;
    private float MouseDownY;
    private float CurrentMouseX;
    private float CurrentMouseY;
    private boolean LeftMouseButtonDown;
    private boolean RightMouseButtonDown;

    private float FieldOfView;
    private float AspectRatio;
    private float NearField;
    private float FarField;
    
    private float EyeX;
    private float EyeY;
    private float EyeZ;
    
    private float TargetX;
    private float TargetY;
    private float TargetZ;
    
    private float ScreenUpX;
    private float ScreenUpY;
    private float ScreenUpZ;
    
    private String m_sFileName = "";
    private Animation m_oAnimation;
    private int m_iFrameRate;
}

