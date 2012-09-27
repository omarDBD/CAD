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

public class Sphere extends Drawable
{
    public Sphere()
    {
        Initialize();
    }
    protected void Initialize()
    {
        super.Initialize();
        m_oCenter = null;
        m_dRadius = 0.0;
        m_iResolution = 5;
    }
    public void DrawObject()
    {
        int i = 0;
        int j = 0;
        double dPhiIncrement = PI/(m_iResolution);
        double dThetaIncrement = 2.0*PI/(m_iResolution);
        double dPhi = 0.0;
        double dTheta = 0.0;
        double dNextPhi = 0.0;
        double dNextTheta = 0.0;
        double dX = 0.0;
        double dY = 0.0;
        double dZ = 0.0;
//        glBegin(GL_LINES);
//        for(i = 0 ; i < m_iResolution ; i++)
//        {
//            dPhi = i*dPhiIncrement;
//            dNextPhi = (i + 1)*dPhiIncrement;
//            for(j = 0 ; j < m_iResolution ; j++)
//            {
//                dTheta = j*dThetaIncrement;
//                dNextTheta = (j + 1)*dThetaIncrement;
//                
//                dX = m_oCenter.GetX() + m_dRadius*sin(dPhi)*cos(dTheta);
//                dY = m_oCenter.GetY() + m_dRadius*sin(dPhi)*sin(dTheta);
//                dZ = m_oCenter.GetZ() + m_dRadius*cos(dPhi);
//                glVertex3d(dX,dY,dZ);
//                
//                dX = m_oCenter.GetX() + m_dRadius*sin(dPhi)*cos(dNextTheta);
//                dY = m_oCenter.GetY() + m_dRadius*sin(dPhi)*sin(dNextTheta);
//                dZ = m_oCenter.GetZ() + m_dRadius*cos(dPhi);
//                glVertex3d(dX,dY,dZ);
//                
//                glVertex3d(dX,dY,dZ);
//                dX = m_oCenter.GetX() + m_dRadius*sin(dNextPhi)*cos(dNextTheta);
//                dY = m_oCenter.GetY() + m_dRadius*sin(dNextPhi)*sin(dNextTheta);
//                dZ = m_oCenter.GetZ() + m_dRadius*cos(dNextPhi);
//                glVertex3d(dX,dY,dZ);
//                
//                glVertex3d(dX,dY,dZ);
//                dX = m_oCenter.GetX() + m_dRadius*sin(dNextPhi)*cos(dTheta);
//                dY = m_oCenter.GetY() + m_dRadius*sin(dNextPhi)*sin(dTheta);
//                dZ = m_oCenter.GetZ() + m_dRadius*cos(dNextPhi);
//                glVertex3d(dX,dY,dZ);
//                
//                glVertex3d(dX,dY,dZ);
//                dX = m_oCenter.GetX() + m_dRadius*sin(dPhi)*cos(dTheta);
//                dY = m_oCenter.GetY() + m_dRadius*sin(dPhi)*sin(dTheta);
//                dZ = m_oCenter.GetZ() + m_dRadius*cos(dPhi);
//                glVertex3d(dX,dY,dZ);
//            }
//        }
//        glEnd();
        
        
        glBegin(GL_QUADS);
        for(i = 0 ; i < m_iResolution ; i++)
        {
            dPhi = i*dPhiIncrement;
            dNextPhi = (i + 1)*dPhiIncrement;
            for(j = 0 ; j < m_iResolution ; j++)
            {
                dTheta = j*dThetaIncrement;
                dNextTheta = (j + 1)*dThetaIncrement;
                
                dX = m_oCenter.GetX() + m_dRadius*sin(dPhi)*cos(dTheta);
                dY = m_oCenter.GetY() + m_dRadius*sin(dPhi)*sin(dTheta);
                dZ = m_oCenter.GetZ() + m_dRadius*cos(dPhi);
                glVertex3d(dX,dY,dZ);
                
                dX = m_oCenter.GetX() + m_dRadius*sin(dPhi)*cos(dNextTheta);
                dY = m_oCenter.GetY() + m_dRadius*sin(dPhi)*sin(dNextTheta);
                dZ = m_oCenter.GetZ() + m_dRadius*cos(dPhi);
                glVertex3d(dX,dY,dZ);
                
                dX = m_oCenter.GetX() + m_dRadius*sin(dNextPhi)*cos(dNextTheta);
                dY = m_oCenter.GetY() + m_dRadius*sin(dNextPhi)*sin(dNextTheta);
                dZ = m_oCenter.GetZ() + m_dRadius*cos(dNextPhi);
                glVertex3d(dX,dY,dZ);
                
                dX = m_oCenter.GetX() + m_dRadius*sin(dNextPhi)*cos(dTheta);
                dY = m_oCenter.GetY() + m_dRadius*sin(dNextPhi)*sin(dTheta);
                dZ = m_oCenter.GetZ() + m_dRadius*cos(dNextPhi);
                glVertex3d(dX,dY,dZ);
            }
        }
        glEnd();
    }
    public void Set(Point oCenter,double dRadius)
    {
        m_oCenter = oCenter;
        m_dRadius = dRadius;
    }
    public Point GetCenter()
    {
        return m_oCenter;
    }
    public double GetRadius()
    {
        return m_dRadius;
    }
    public void Read(Scanner oFileScanner,Scene oScene,ArrayList<DrawableCategory> voCategories) throws IOException
    {   
        super.Read(oFileScanner,oScene,voCategories);
        ArrayList<Drawable> voPoints = ((Scene)oScene).GetArrayOfType(1);
        m_oCenter = (Point)voPoints.get(oFileScanner.nextInt() - 1);
        m_dRadius = oFileScanner.nextDouble();
        m_iResolution = oFileScanner.nextInt();
    }
    public int GetType()
    {
        return 5;
    }
    protected Point m_oCenter;
    protected double m_dRadius;
    protected int m_iResolution;
}

