package csviewer;
/**
 *
 * @author ahmedhussein
 */

import java.util.*;
import java.io.*;
import static org.lwjgl.opengl.GL11.*;
import java.util.Scanner;

public class Scene extends Visible
{
    public Scene(Animation oAnimation)
    {
        Initialize();
        m_oAnimation = oAnimation;
    }
    protected void Initialize()
    {
        m_voPoints = new ArrayList<Drawable>();
        m_voLines = new ArrayList<Drawable>();
        m_voTriangles = new ArrayList<Drawable>();
        m_voBoxes = new ArrayList<Drawable>();
        m_voSpheres = new ArrayList<Drawable>();
        m_voHex20Elements = new ArrayList<Drawable>();
        
        m_voPoints.clear();
        m_voLines.clear();
        m_voTriangles.clear();
        m_voBoxes.clear();
        m_voSpheres.clear();
        m_voHex20Elements.clear();
        
        m_oAnimation = null;
    }
    public void AddObject(Drawable oObject)
    {
        ArrayList<Drawable> voDrawable = GetArrayOfType(oObject.GetType());
        if(voDrawable != null)
        {
            voDrawable.add(oObject);
        }
    }
    public void DrawFrame()
    {
        glLineWidth(3);
        glBegin(GL_LINES);
        glColor3d(1.0,0.0,0.0);
        glVertex3d(0.0,0.0,0.0);
        glVertex3d(10.0,0.0,0.0);
        glColor3d(0.0,1.0,0.0);
        glVertex3d(0.0,0.0,0.0);
        glVertex3d(0.0,10.0,0.0);
        glColor3d(0.0,0.0,1.0);
        glVertex3d(0.0,0.0,0.0);
        glVertex3d(0.0,0.0,10.0);
        glEnd();
        glLineWidth(1);
        glColor3d(1.0,1.0,1.0);
    }
    public void Paint()
    {
        int i = 0;
        int j = 0;
        ArrayList<Drawable> voDrawable = null;
        int iObjectsCount = 0;
        for(i = 0 ; i < TotalTypesCount ; i++)
        {
            voDrawable = GetArrayOfType(i + 1);
            if(voDrawable == null)
            {
                continue;
            }

            iObjectsCount = voDrawable.size();
            for(j = 0 ; j < iObjectsCount ; j++)
            {
                voDrawable.get(j).Draw();
            }
        }
    }
    public void Read(String sFileName) throws IOException
    {
        File InFile = new File(sFileName);
        Scanner FileScanner = new Scanner(InFile);        
        ClearObjects();
        int iTypesCount = FileScanner.nextInt();
        int iObjectsCount = 0;
        int i = 0;
        int j = 0;
        Drawable oObject = null;
        int iObjectTypeIdentifier = 0;
        ArrayList<Drawable> voDrawable = null;
        ArrayList<DrawableCategory> voCategories = m_oAnimation.GetCategories();
        for(i = 0 ; i < iTypesCount ; i++)
        {
            iObjectTypeIdentifier = FileScanner.nextInt();
            voDrawable = GetArrayOfType(iObjectTypeIdentifier);
            if(voDrawable == null)
            {
                continue;
            }
            iObjectsCount = FileScanner.nextInt();
            voDrawable.ensureCapacity(iObjectsCount);
            for(j = 0 ; j < iObjectsCount ; j++)
            {
                oObject = CreateObjectOfType(iObjectTypeIdentifier);
                oObject.Read(FileScanner,this,voCategories);
                voDrawable.add(oObject);
            }
        }
        FileScanner.close();
    }
    Drawable CreateObjectOfType(int iObjectTypeIdentifier)
    {
        if(iObjectTypeIdentifier == 0)
        {
            return null;
        }
        else if(iObjectTypeIdentifier == 1)
        {
            return new Point();
        }
        else if(iObjectTypeIdentifier == 2)
        {
            return new Line();
        }
        else if(iObjectTypeIdentifier == 3)
        {
            return new Triangle();
        }
        else if(iObjectTypeIdentifier == 4)
        {
            return new Box();
        }
        else if(iObjectTypeIdentifier == 5)
        {
            return new Sphere();
        }
        else if(iObjectTypeIdentifier == 6)
        {
            return new Hex20Element();
        }
        return null;
    }
    public ArrayList<Drawable> GetArrayOfType(int iObjectTypeIdentifier)
    {
        if(iObjectTypeIdentifier == 0)
        {
            return null;
        }
        else if(iObjectTypeIdentifier == 1)
        {
            return m_voPoints;
        }
        else if(iObjectTypeIdentifier == 2)
        {
            return m_voLines;
        }
        else if(iObjectTypeIdentifier == 3)
        {
            return m_voTriangles;
        }
        else if(iObjectTypeIdentifier == 4)
        {
            return m_voBoxes;
        }
        else if(iObjectTypeIdentifier == 5)
        {
            return m_voSpheres;
        }
        else if(iObjectTypeIdentifier == 6)
        {
            return m_voHex20Elements;
        }
        return null;
    }
    protected void ClearObjects()
    {
        m_voPoints.clear();
        m_voLines.clear();
        m_voTriangles.clear();
        m_voBoxes.clear();
        m_voSpheres.clear();
        m_voHex20Elements.clear();
    }
    protected ArrayList<Drawable> m_voPoints;
    protected ArrayList<Drawable> m_voLines;
    protected ArrayList<Drawable> m_voTriangles;
    protected ArrayList<Drawable> m_voBoxes;
    protected ArrayList<Drawable> m_voSpheres;
    protected ArrayList<Drawable> m_voHex20Elements;
    Animation m_oAnimation;
    static final protected int TotalTypesCount = 6;
}

