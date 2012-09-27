package csviewer;

/**
 *
 * @author ahmedhussein
 */

import static org.lwjgl.opengl.GL11.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Triangle extends Drawable
{
    public Triangle()
    {
        Initialize();
    }
    protected void Initialize()
    {
        super.Initialize();
        m_oPoint1 = null;
        m_oPoint2 = null;
        m_oPoint3 = null;
    }
    public void DrawObject()
    {
        glBegin(GL_LINES);
        glVertex3d(m_oPoint1.GetX(),m_oPoint1.GetY(),m_oPoint1.GetZ());
        glVertex3d(m_oPoint2.GetX(),m_oPoint2.GetY(),m_oPoint2.GetZ());
        glVertex3d(m_oPoint2.GetX(),m_oPoint2.GetY(),m_oPoint2.GetZ());
        glVertex3d(m_oPoint3.GetX(),m_oPoint3.GetY(),m_oPoint3.GetZ());
        glVertex3d(m_oPoint3.GetX(),m_oPoint3.GetY(),m_oPoint3.GetZ());
        glVertex3d(m_oPoint1.GetX(),m_oPoint1.GetY(),m_oPoint1.GetZ());
        glEnd();
        //glBegin(GL_TRIANGLES);
        //glVertex3d(m_oPoint1.GetX(),m_oPoint1.GetY(),m_oPoint1.GetZ());
        //glVertex3d(m_oPoint2.GetX(),m_oPoint2.GetY(),m_oPoint2.GetZ());
        //glVertex3d(m_oPoint3.GetX(),m_oPoint3.GetY(),m_oPoint3.GetZ());
        //glEnd();
    }
    public void Set(Point oPoint1,Point oPoint2,Point oPoint3)
    {
        m_oPoint1 = oPoint1;
        m_oPoint2 = oPoint2;
        m_oPoint3 = oPoint3;
    }
    public Point GetPoint1()
    {
        return m_oPoint1;
    }
    public Point GetPoint2()
    {
        return m_oPoint2;
    }
    public Point GetPoint3()
    {
        return m_oPoint3;
    }
    public void Read(Scanner oFileScanner,Scene oScene,ArrayList<DrawableCategory> voCategories) throws IOException
    {   
        super.Read(oFileScanner,oScene,voCategories);
        ArrayList<Drawable> voPoints = ((Scene)oScene).GetArrayOfType(1);
        m_oPoint1 = (Point)voPoints.get(oFileScanner.nextInt() - 1);
        m_oPoint2 = (Point)voPoints.get(oFileScanner.nextInt() - 1);
        m_oPoint3 = (Point)voPoints.get(oFileScanner.nextInt() - 1);
    }
    public int GetType()
    {
        return 3;
    }
    protected Point m_oPoint1;
    protected Point m_oPoint2;
    protected Point m_oPoint3;
}

