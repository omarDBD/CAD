package csviewer;
/**
 *
 * @author ahmedhussein
 */

import java.io.*;
import java.util.Scanner;

public class DrawableCategory
{
    public DrawableCategory()
    {
        Initialize();
    }
    protected void Initialize()
    {
        m_bIsDrawn = true;
        m_dRedColorValue = 1.0;
        m_dGreenColorValue = 1.0;
        m_dBlueColorValue = 1.0;
    }
    public void Read(Scanner oFileScanner)
    {
        int iIsDrawn = oFileScanner.nextInt();
        if(iIsDrawn == 0)
        {
            m_bIsDrawn = false;
        }
        else
        {
            m_bIsDrawn = true;
        }
        m_dRedColorValue = oFileScanner.nextDouble();
        m_dGreenColorValue = oFileScanner.nextDouble();
        m_dBlueColorValue = oFileScanner.nextDouble();
    }
    public boolean IsDrawn()
    {
        return m_bIsDrawn;
    }
    public double GetRedColorValue()
    {
        return m_dRedColorValue;
    }
    public double GetGreenColorValue()
    {
        return m_dGreenColorValue;
    }
    public double GetBlueColorValue()
    {
        return m_dBlueColorValue;
    }
    protected boolean m_bIsDrawn;
    protected double m_dRedColorValue;
    protected double m_dGreenColorValue;
    protected double m_dBlueColorValue;
}

