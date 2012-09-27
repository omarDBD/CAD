package csviewer;
/**
 *
 * @author ahmedhussein
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;


public abstract class Drawable extends Visible
{
    public void Draw()
    {
        if(m_oCategory.IsDrawn())
        {
            glColor3d(m_oCategory.GetRedColorValue(),m_oCategory.GetGreenColorValue(),m_oCategory.GetBlueColorValue());
            DrawObject();
            glColor3d(0.0,0.0,0.0);
        }
    }
    abstract protected void DrawObject();
    public void Read(Scanner oFileScanner,Scene oScene,ArrayList<DrawableCategory> voCategories) throws IOException
    {
        m_oCategory = voCategories.get(oFileScanner.nextInt() - 1);
    }
    abstract public int GetType();
    protected void Initialize()
    {
        m_oCategory = null;
    }
    protected DrawableCategory m_oCategory;
}

