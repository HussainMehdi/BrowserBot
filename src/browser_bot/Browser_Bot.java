/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browser_bot;

import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.openqa.selenium.WebElement;
import org.xml.sax.SAXException;

/**
 *
 * @author Madan Lal
 */
public class Browser_Bot {
    private static boolean  FinishTest = false;
    public static boolean Timeout=false;
    private static int CertificatePopupLimit = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        open_browser(args[0]);
    }
    
    public static void open_browser(String URL) throws InterruptedException
    {
        AppiumController _appiumController = new AppiumController();
        
        String path = new File("browser.apk").getAbsolutePath();
        
        try {
            _appiumController.AppiumDriverStart(path, new URL("http://127.0.0.1:4723/wd/hub"),"'"+URL+"'");
        } catch (IOException ex) {
            Logger.getLogger(Browser_Bot.class.getName()).log(Level.SEVERE, null, ex);
        }
        Thread.sleep(5000);
        
        //_appiumController.getDriver().sendKeyEvent(4);
        
        //_appiumController.getDriver().getKeyboard().sendKeys(4);
        //Thread.sleep(3000);
        //List<WebElement> textView = (List<WebElement>) _appiumController.getDriver().findElementsById("net.fast.web.browser:id/enterUrl");
        //textView.get(0).click();
    //     Thread.sleep(1000);
        
      //  _appiumController.getDriver().sendKeyEvent(66); //Press Enter
        Thread LoaderThread = new Thread(new LoadingUpdater(_appiumController.getDriver())); 
        Thread.sleep(1000);
       
        LoaderThread.start();
        
        Thread timeoutThread = new Thread(new TimeoutThread());
        timeoutThread.start();
        //Thread.sleep(10000); //wait 10 seconds for redirections
        
        //While pages are loading
        while(LoaderThread.isAlive() && !FinishTest && !Timeout)
        {
            try {
                //Popup work
                PopupResolver(_appiumController.getDriver());
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(Browser_Bot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(Browser_Bot.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Browser_Bot.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        if (_appiumController.getDriver().currentActivity().equals("com.google.android.finsky.activities.MainActivity"))
            System.out.println("Google Play was loaded");
        else if(CertificatePopupLimit==3 && FinishTest)
        {
            System.out.println("More than 3 certificates");
        }
        else if(Timeout)
            System.out.println("Internet problem");
        else
            System.out.println("sucessfully loaded page");
        LoaderThread.stop();
        timeoutThread.stop();
        
    }
    
    public static void PopupResolver(AndroidDriver ad) throws ParserConfigurationException, SAXException, IOException
    {
          if (ad.currentActivity().equals("com.google.android.finsky.activities.MainActivity")){FinishTest = true;return;}
          
        List<UI_component> comps =  new Layout_UI_Scrapper().XML_parser(ad.getPageSource());
        for (UI_component c : comps)
        {
            if(c.getClassName().equals("android.widget.TextView")
                    && c.getResourceId().equals("android:id/message"))
            {
                String Text = c.getText();
                Text = Text.toLowerCase().trim();
                
                String CertificateText = "The certificate of the site is not trusted. Proceed anyway?";
                CertificateText  = CertificateText.toLowerCase().trim();
                
                if(CertificateText.equals(Text ))
                {
                    try
                    {
                        List<WebElement> Yesbutton = (List<WebElement>) ad.findElementsById("android:id/button1");
                        Yesbutton.get(0).click();
                        CertificatePopupLimit++;
                        if(CertificatePopupLimit==3) FinishTest = true;
                    }
                    catch (Exception ex){}
                }
                else
                {
                    ad.sendKeyEvent(4); // Back Key
                }
            }
        }
    }
}
