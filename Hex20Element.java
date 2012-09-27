package csviewer;
/**
 *
 * @author ahmedhussein
 */

import static org.lwjgl.opengl.GL11.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Hex20Element extends Drawable
{
    public Hex20Element()
    {
        Initialize();
    }
    protected void Initialize()
    {
        super.Initialize();
        m_vpoNodes = new ArrayList<Point>();
        m_vpoNodes.clear();
    }
    public void DrawObject()
    {
        Point oP1 = m_vpoNodes.get(0);
        Point oP2 = m_vpoNodes.get(1);
        Point oP3 = m_vpoNodes.get(2);
        Point oP4 = m_vpoNodes.get(3);
        Point oP5 = m_vpoNodes.get(4);
        Point oP6 = m_vpoNodes.get(5);
        Point oP7 = m_vpoNodes.get(6);
        Point oP8 = m_vpoNodes.get(7);
        Point oP9 = m_vpoNodes.get(8);
        Point oP10 = m_vpoNodes.get(9);
        Point oP11 = m_vpoNodes.get(10);
        Point oP12 = m_vpoNodes.get(11);
        Point oP13 = m_vpoNodes.get(12);
        Point oP14 = m_vpoNodes.get(13);
        Point oP15 = m_vpoNodes.get(14);
        Point oP16 = m_vpoNodes.get(15);
        Point oP17 = m_vpoNodes.get(16);
        Point oP18 = m_vpoNodes.get(17);
        Point oP19 = m_vpoNodes.get(18);
        Point oP20 = m_vpoNodes.get(19);
        
        glBegin(GL_LINES);
        
        glVertex3d(oP1.GetX(),oP1.GetY(),oP1.GetZ());
        glVertex3d(oP9.GetX(),oP9.GetY(),oP9.GetZ());
        
        glVertex3d(oP9.GetX(),oP9.GetY(),oP9.GetZ());
        glVertex3d(oP2.GetX(),oP2.GetY(),oP2.GetZ());
        
        glVertex3d(oP2.GetX(),oP2.GetY(),oP2.GetZ());
        glVertex3d(oP10.GetX(),oP10.GetY(),oP10.GetZ());
        
        glVertex3d(oP10.GetX(),oP10.GetY(),oP10.GetZ());
        glVertex3d(oP3.GetX(),oP3.GetY(),oP3.GetZ());
        
        glVertex3d(oP3.GetX(),oP3.GetY(),oP3.GetZ());
        glVertex3d(oP11.GetX(),oP11.GetY(),oP11.GetZ());
        
        glVertex3d(oP11.GetX(),oP11.GetY(),oP11.GetZ());
        glVertex3d(oP4.GetX(),oP4.GetY(),oP4.GetZ());
        
        glVertex3d(oP4.GetX(),oP4.GetY(),oP4.GetZ());
        glVertex3d(oP12.GetX(),oP12.GetY(),oP12.GetZ());
        
        glVertex3d(oP12.GetX(),oP12.GetY(),oP12.GetZ());
        glVertex3d(oP1.GetX(),oP1.GetY(),oP1.GetZ());
        
        glVertex3d(oP5.GetX(),oP5.GetY(),oP5.GetZ());
        glVertex3d(oP13.GetX(),oP13.GetY(),oP13.GetZ());
        
        glVertex3d(oP13.GetX(),oP13.GetY(),oP13.GetZ());
        glVertex3d(oP6.GetX(),oP6.GetY(),oP6.GetZ());
        
        glVertex3d(oP6.GetX(),oP6.GetY(),oP6.GetZ());
        glVertex3d(oP14.GetX(),oP14.GetY(),oP14.GetZ());
        
        glVertex3d(oP14.GetX(),oP14.GetY(),oP14.GetZ());
        glVertex3d(oP7.GetX(),oP7.GetY(),oP7.GetZ());
        
        glVertex3d(oP7.GetX(),oP7.GetY(),oP7.GetZ());
        glVertex3d(oP15.GetX(),oP15.GetY(),oP15.GetZ());
        
        glVertex3d(oP15.GetX(),oP15.GetY(),oP15.GetZ());
        glVertex3d(oP8.GetX(),oP8.GetY(),oP8.GetZ());
        
        glVertex3d(oP8.GetX(),oP8.GetY(),oP8.GetZ());
        glVertex3d(oP16.GetX(),oP16.GetY(),oP16.GetZ());
        
        glVertex3d(oP16.GetX(),oP16.GetY(),oP16.GetZ());
        glVertex3d(oP5.GetX(),oP5.GetY(),oP5.GetZ());
        
        glVertex3d(oP1.GetX(),oP1.GetY(),oP1.GetZ());
        glVertex3d(oP17.GetX(),oP17.GetY(),oP17.GetZ());
        
        glVertex3d(oP17.GetX(),oP17.GetY(),oP17.GetZ());
        glVertex3d(oP5.GetX(),oP5.GetY(),oP5.GetZ());
        
        glVertex3d(oP2.GetX(),oP2.GetY(),oP2.GetZ());
        glVertex3d(oP18.GetX(),oP18.GetY(),oP18.GetZ());
        
        glVertex3d(oP18.GetX(),oP18.GetY(),oP18.GetZ());
        glVertex3d(oP6.GetX(),oP6.GetY(),oP6.GetZ());
        
        glVertex3d(oP3.GetX(),oP3.GetY(),oP3.GetZ());
        glVertex3d(oP19.GetX(),oP19.GetY(),oP19.GetZ());
        
        glVertex3d(oP19.GetX(),oP19.GetY(),oP19.GetZ());
        glVertex3d(oP7.GetX(),oP7.GetY(),oP7.GetZ());
        
        glVertex3d(oP4.GetX(),oP4.GetY(),oP4.GetZ());
        glVertex3d(oP20.GetX(),oP20.GetY(),oP20.GetZ());
        
        glVertex3d(oP20.GetX(),oP20.GetY(),oP20.GetZ());
        glVertex3d(oP8.GetX(),oP8.GetY(),oP8.GetZ());
        
        glEnd();
    }
    public void Set(ArrayList<Point> voNodes)
    {
        if(voNodes.size() < iNodesCount)
        {
            return;
        }
        int i = 0;
        m_vpoNodes.clear();
        for(i = 0 ; i < iNodesCount ; i++)
        {
            m_vpoNodes.add(voNodes.get(i));
        }
    }
    public Point GetNode(int iIndex)
    {
        return m_vpoNodes.get(iIndex);
    }
    public void Read(Scanner oFileScanner,Scene oScene,ArrayList<DrawableCategory> voCategories) throws IOException
    {   
        super.Read(oFileScanner,oScene,voCategories);
        // get nodes array from the scene object
        ArrayList<Drawable> voPoints = ((Scene)oScene).GetArrayOfType(1);
        int i = 0;
        m_vpoNodes.clear();
        for(i = 0 ; i < iNodesCount ; i++)
        {
            m_vpoNodes.add((Point)voPoints.get(oFileScanner.nextInt() - 1));
        }
    }
    public int GetType()
    {
        return 6;
    }
    protected ArrayList<Point> m_vpoNodes;
    static int iNodesCount = 20;
}

