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
    public LoadingUpdater(AndroidDriver ad)
     {androidDriver = ad; }
    
    @Override
    public void run()
    {
        while(!StopTimeArrived)
        {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadingUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            List<WebElement> textView = (List<WebElement>) androidDriver.findElementsById("net.fast.web.browser:id/enterUrl");
            if(textView==null || textView.size()<1) destroy();
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
            catch (Exception ex){destroy();}
            
                
        }
        
    }
    @Override
    public void destroy()
    {
        StopTimeArrived=true;
    }
}

