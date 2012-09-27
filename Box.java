package csviewer;
/**
 *
 * @author ahmedhussein
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;

public class Box extends Drawable
{
    Box()
    {
        Initialize();
    }
    public void DrawObject()
    {
        glBegin(GL_LINES);
        glVertex3d(m_dXMin,m_dYMin,m_dZMin);
        glVertex3d(m_dXMax,m_dYMin,m_dZMin);
        
        glVertex3d(m_dXMax,m_dYMin,m_dZMin);
        glVertex3d(m_dXMax,m_dYMax,m_dZMin);
        
        glVertex3d(m_dXMax,m_dYMax,m_dZMin);
        glVertex3d(m_dXMin,m_dYMax,m_dZMin);
        
        glVertex3d(m_dXMin,m_dYMax,m_dZMin);
        glVertex3d(m_dXMin,m_dYMin,m_dZMin);
        
        glVertex3d(m_dXMin,m_dYMin,m_dZMax);
        glVertex3d(m_dXMax,m_dYMin,m_dZMax);
        
        glVertex3d(m_dXMax,m_dYMin,m_dZMax);
        glVertex3d(m_dXMax,m_dYMax,m_dZMax);
        
        glVertex3d(m_dXMax,m_dYMax,m_dZMax);
        glVertex3d(m_dXMin,m_dYMax,m_dZMax);
        
        glVertex3d(m_dXMin,m_dYMax,m_dZMax);
        glVertex3d(m_dXMin,m_dYMin,m_dZMax);
        
        glVertex3d(m_dXMin,m_dYMin,m_dZMin);
        glVertex3d(m_dXMin,m_dYMin,m_dZMax);
        
        glVertex3d(m_dXMax,m_dYMin,m_dZMin);
        glVertex3d(m_dXMax,m_dYMin,m_dZMax);
        
        glVertex3d(m_dXMax,m_dYMax,m_dZMin);
        glVertex3d(m_dXMax,m_dYMax,m_dZMax);
        
        glVertex3d(m_dXMin,m_dYMax,m_dZMin);
        glVertex3d(m_dXMin,m_dYMax,m_dZMax);
        glEnd();
    }
    protected void Initialize()
    {
        super.Initialize();
        m_dXMin = 0.0;
        m_dXMax = 0.0;
        m_dYMin = 0.0;
        m_dYMax = 0.0;
        m_dZMin = 0.0;
        m_dZMax = 0.0;
    }
    public void Read(Scanner oFileScanner,Scene oScene,ArrayList<DrawableCategory> voCategories) throws IOException
    {
        super.Read(oFileScanner,oScene,voCategories);
        m_dXMin = oFileScanner.nextDouble();
        m_dXMax = oFileScanner.nextDouble();
        m_dYMin = oFileScanner.nextDouble();
        m_dYMax = oFileScanner.nextDouble();
        m_dZMin = oFileScanner.nextDouble();
        m_dZMax = oFileScanner.nextDouble();
    }
    public void LightRead(Scanner oFileScanner)
    {
        m_dXMin = oFileScanner.nextDouble();
        m_dXMax = oFileScanner.nextDouble();
        m_dYMin = oFileScanner.nextDouble();
        m_dYMax = oFileScanner.nextDouble();
        m_dZMin = oFileScanner.nextDouble();
        m_dZMax = oFileScanner.nextDouble(); 
    }
    public int GetType()
    {
        return 4;
    }
    protected double m_dXMin;
    protected double m_dXMax;
    protected double m_dYMin;
    protected double m_dYMax;
    protected double m_dZMin;
    protected double m_dZMax;
}

