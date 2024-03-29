/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browser_bot;

import io.appium.java_client.android.AndroidDriver;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebElement;

/**
 *
 * @author Hussain-X201
 */
public class LoadingUpdater extends Thread{
    private AndroidDriver androidDriver = null;
    private String PreviousString = "-1";
    private boolean StopTimeArrived = false;
    private int errorDepth =0;
    public LoadingUpdater(AndroidDriver ad)
     {androidDriver = ad; }
    
    @Override
    public void run()
    {
        while(!StopTimeArrived)
        {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadingUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (androidDriver.currentActivity().equals("com.google.android.finsky.activities.MainActivity")){destroy();}
            List<WebElement> textView = (List<WebElement>) androidDriver.findElementsById("net.fast.web.browser:id/enterUrl");
            if(textView==null || textView.size()<1) {errorDepth++; if(errorDepth>3)destroy();}
            try
            {
            WebElement e = textView.get(0);
            
            String NewString = e.getAttribute("text");
   
            
            if(NewString.equals(PreviousString))
            {
               // System.out.println("Not Loading now");
                destroy();
            }
            else
            {
            //    System.out.println("Loading Again");
            }
            
            PreviousString = NewString;
            }
            catch (Exception ex){errorDepth++; if(errorDepth>3)destroy();}
            
                
        }
        
    }
    @Override
    public void destroy()
    {
        StopTimeArrived=true;
    }
}

