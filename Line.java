package csviewer;
/**
 *
 * @author ahmedhussein
 */

import static org.lwjgl.opengl.GL11.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import static java.lang.Math.*;

public class Line extends Drawable
{
    public Line()
    {
        Initialize();
    }
    protected void Initialize()
    {
        super.Initialize();
        m_oStartPoint = null;
        m_oEndPoint = null;
    }
    public void Set(Point oStartPoint,Point oEndPoint)
    {
        m_oStartPoint = oStartPoint;
        m_oEndPoint = oEndPoint;
    }
    public void DrawObject()
    {
        glLineWidth(1.0f);
        glBegin(GL_LINES);
        glVertex3d(m_oStartPoint.GetX(),m_oStartPoint.GetY(),m_oStartPoint.GetZ());
        glVertex3d(m_oEndPoint.GetX(),m_oEndPoint.GetY(),m_oEndPoint.GetZ());
        glEnd();
        glLineWidth(1.0f);
    }
    public Point GetStartPoint()
    {
        return m_oStartPoint;
    }
    public Point GetEndPoint()
    {
        return m_oEndPoint;
    }
    public void Read(Scanner oFileScanner,Scene oScene,ArrayList<DrawableCategory> voCategories) throws IOException
    {
        super.Read(oFileScanner,oScene,voCategories);
        ArrayList<Drawable> voPoints = ((Scene)oScene).GetArrayOfType(1);
        m_oStartPoint = (Point)voPoints.get(oFileScanner.nextInt() - 1);
        m_oEndPoint = (Point)voPoints.get(oFileScanner.nextInt() - 1);
    }
    public int GetType()
    {
        return 2;
    }
    protected Point m_oStartPoint;
    protected Point m_oEndPoint;
}

