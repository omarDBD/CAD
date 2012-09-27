package csviewer;
/**
 *
 * @author ahmedhussein
 */

import static java.lang.Math.*;

public class Vector extends Point
{
    public Vector()
    {
        super.Initialize();
    }
    public Vector(Vector oVector)
    {
        Set(oVector.m_dX,oVector.m_dY,oVector.m_dZ);
    }
    public Vector(double dX,double dY,double dZ)
    {
        Set(dX,dY,dZ);
    }
    public Vector(Point oPoint)
    {
        Set(oPoint.m_dX,oPoint.m_dY,oPoint.m_dZ);
    }
    public Vector(Point oPoint1,Point oPoint2)
    {
        SetByPoints(oPoint1,oPoint2);
    }
    public void SetByPoints(Point oPoint1,Point oPoint2)
    {
        Set(oPoint2.m_dX - oPoint1.m_dX,oPoint2.m_dY - oPoint1.m_dY,oPoint2.m_dZ- oPoint1.m_dZ);
    }
    public Vector Add(Vector oVector)
    {
        return new Vector(m_dX + oVector.m_dX,m_dY + oVector.m_dY,m_dZ + oVector.m_dZ);
    }
    public Vector Subtract(Vector oVector)
    {
        return new Vector(m_dX - oVector.m_dX,m_dY - oVector.m_dY,m_dZ - oVector.m_dZ);
    }
    public double Dot(Vector oVector)
    {
        return (m_dX*oVector.m_dX + m_dY*oVector.m_dY + m_dZ*oVector.m_dZ);
    }
    public Vector Multiply(double dFactor)
    {
        return new Vector(dFactor*m_dX,dFactor*m_dY,dFactor*m_dZ);
    }
    public Vector Cross(Vector oVector)
    {
        Vector oResult = new Vector();
        oResult.SetX(m_dY*oVector.m_dZ - m_dZ*oVector.m_dY);
        oResult.SetY(m_dZ*oVector.m_dX - m_dX*oVector.m_dZ);
        oResult.SetZ(m_dX*oVector.m_dY - m_dY*oVector.m_dX);
        return oResult;
    }
    public double Length()
    {
        return sqrt(m_dX*m_dX + m_dY*m_dY + m_dZ*m_dZ);
    }
    public void Normalize()
    {
        double dLength = Length();
        if(dLength < 1E-20)
        {
            return;
        }
        m_dX = m_dX/dLength;
        m_dY = m_dY/dLength;
        m_dZ = m_dZ/dLength;
    }
    public double GetAngle(Vector oVector)
    {
        Vector oV1 = this;
        Vector oV2 = oVector;
        oV1.Normalize();
        oV2.Normalize();
        return acos(oV1.Dot(oV2));
    }
    public Vector GetDirection()
    {
        Vector oDirection = this;
        oDirection.Normalize();
        return oDirection;
    }
    public double GetXAngle()
    {
        Vector oV = this;
        oV.Normalize();
        return acos(oV.GetX());
    }
    public double GetYAngle()
    {
        Vector oV = this;
        oV.Normalize();
        return acos(oV.GetY());
    }
    public double GetZAngle()
    {
        Vector oV = this;
        oV.Normalize();
        return acos(oV.GetZ());
    }
    public void Reverse()
    {
        m_dX = -m_dX;
        m_dY = -m_dY;
        m_dZ = -m_dZ;
    }
}


