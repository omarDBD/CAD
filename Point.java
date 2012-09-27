package csviewer;
/**
 *
 * @author ahmedhussein
 */

import static org.lwjgl.opengl.GL11.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Point extends Drawable
{
    public Point()
    {
        Initialize();
    }
    public Point(double dX,double dY,double dZ)
    {
        Initialize();
        Set(dX,dY,dZ);
    }
    public Point(Point oPoint)
    {
        Initialize();
        Set(oPoint.m_dX,oPoint.m_dY,oPoint.m_dZ);
    }
    public void Set(double dX,double dY,double dZ)
    {
        m_dX = dX;
        m_dY = dY;
        m_dZ = dZ;
    }
    public void SetX(double dValue)
    {
        m_dX = dValue;
    }
    public void SetY(double dValue)
    {
        m_dY = dValue;
    }
    public void SetZ(double dValue)
    {
        m_dZ = dValue;
    }
    public double GetX()
    {
        return m_dX;
    }
    public double GetY()
    {
        return m_dY;
    }
    public double GetZ()
    {
        return m_dZ;
    }
    public void DrawObject()
    {
        glPointSize(3.f);
        glBegin(GL_POINTS);
        glVertex3d(m_dX,m_dY,m_dZ);
        glEnd();
        glPointSize(1.0f);
    }
    protected void Initialize()
    {
        super.Initialize();
        m_dX = 0.0;
        m_dY = 0.0;
        m_dZ = 0.0;
    }
    public void Read(Scanner oFileScanner,Scene oScene,ArrayList<DrawableCategory> voCategories) throws IOException
    {
        super.Read(oFileScanner,oScene,voCategories);
        m_dX = oFileScanner.nextDouble();
        m_dY = oFileScanner.nextDouble();
        m_dZ = oFileScanner.nextDouble();
    }
    public int GetType()
    {
        return 1;
    }
    protected double m_dX;
    protected double m_dY;
    protected double m_dZ;
}

