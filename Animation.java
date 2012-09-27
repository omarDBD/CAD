package csviewer;

/**
 *
 * @author ahmedhussein
 */

import java.io.IOException;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.Display;
import java.io.*;
import java.util.Scanner;

public class Animation extends Visible
{
    public Animation()
    {
        Initialize();
    }
    public void Read(String sFileName) throws IOException
    {
        File fAnimationFile = new File(sFileName);
        Scanner oAnimationFileScanner = new Scanner(fAnimationFile);
        ReadCategories(oAnimationFileScanner);
        System.out.println("reading scenes");
        ReadScenes(oAnimationFileScanner);
        System.out.println("done reading");
        oAnimationFileScanner.close();
    }
    public void ReadCategories(Scanner oAnimationFileScanner)
    {
        int iCategoriesCount = oAnimationFileScanner.nextInt();
        m_voCategories.clear();
        m_voCategories.ensureCapacity(iCategoriesCount);
        int i = 0;
        DrawableCategory poCategory = null;
        for(i = 0 ; i < iCategoriesCount ; i++)
        {
            poCategory = new DrawableCategory();
            poCategory.Read(oAnimationFileScanner);
            m_voCategories.add(poCategory);
        }
    }
    public void ReadScenes(Scanner oAnimationFileScanner) throws IOException
    {
        int iScenesCount = oAnimationFileScanner.nextInt();
        m_voFrames.clear();
        m_voFrames.ensureCapacity(iScenesCount);
        int i = 0;
        Scene poTempScene = null;
        String sSceneFileName = new String();
        for(i = 0 ; i < iScenesCount ; i++)
        {
            
            sSceneFileName = oAnimationFileScanner.next();
            System.out.println("reading scene "+i+" file name "+sSceneFileName);
            poTempScene = new Scene(this);
            poTempScene.Read(sSceneFileName);
            m_voFrames.add(poTempScene);
        }
    }
    public void Play(int iFramesPerSecond) throws InterruptedException
    {
        int i = 0;
        int iSize = m_voFrames.size();
        int iSleepDuration = 1000/iFramesPerSecond;
        if(iSize == 1)
        {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glCallList(m_iListIndex);
            Display.update();     
        }
        else
        {
            for(i = 0 ; i < iSize ; i++)
            {
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glCallList(m_iListIndex + i);
                Display.update();
                Thread.sleep(iSleepDuration);
            }
        }

    }
    public ArrayList<DrawableCategory> GetCategories()
    {
        return m_voCategories;
    }
    public void Build()
    {
        int i = 0;
        int iSize = m_voFrames.size();
        m_iListIndex = glGenLists(iSize);
        Scene poScene = null;
        for(i = 0 ; i < iSize ; i++)
        {
            glNewList(m_iListIndex + i,GL_COMPILE);
            poScene = m_voFrames.get(i);
            glClear(GL_COLOR_BUFFER_BIT);
            poScene.DrawFrame();
            poScene.Paint();
            glEndList();
        }
    }
    protected void Initialize()
    {
        m_voFrames = new ArrayList<Scene>();
        m_voFrames.clear();
        m_voCategories = new ArrayList<DrawableCategory>();
        m_voCategories.clear();
        m_iListIndex = 0;
    }
    protected ArrayList<DrawableCategory> m_voCategories;
    protected ArrayList<Scene> m_voFrames;
    int m_iListIndex;
}


