/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browser_bot;



import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.xml.sax.SAXException;

/**
 *
 * @author Madan
 */
public class AppiumController {
    private AndroidDriver _androidDriver;
    public static long COMMAND_TIMEOUT = 1800;
     /**
     * Get the Primary AndroidDriver
     * @return Android Driver
     */
    public AndroidDriver getDriver(){return _androidDriver;}
    
    
     /**
     * Starts the Appium Android Driver for specified app
     * @param AppPath Path of application to launch
     * @param DeviceName If multiple devices are connected, set device name to launch that app onto
     * @param WaitForActivity Start Driver and wait for other operations until that activity is not launched
     * @param Server_URL  URL where Appium Server is listening
     */
     public void AppiumDriverStart(String AppPath,URL Server_URL) throws MalformedURLException, IOException
    {
       // WaitForActivity = null;
        
        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        //cap.setCapability(MobileCapabilityType.APP,AppPath);
        cap.setCapability(MobileCapabilityType.APP_ACTIVITY, "www.ijoysoft.browser.activities.MainActivity");
        cap.setCapability(MobileCapabilityType.APP_PACKAGE, "net.fast.web.browser");
        cap.setCapability(MobileCapabilityType.LAUNCH_TIMEOUT, 0);
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, COMMAND_TIMEOUT);
        cap.setCapability("fullReset", false);
        cap.setCapability("noReset", false);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "");
       // cap.setCapability("udid", "emulator-5554");
        cap.setCapability(MobileCapabilityType.APP_WAIT_ACTIVITY, "www.ijoysoft.browser.activities.MainActivity");
        /*
        if (DeviceName!=null)
        {
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
            cap.setCapability("udid", DeviceName);
        }

        if (WaitForActivity!=null)
        {
           // cap.setCapability(MobileCapabilityType.APP_WAIT_ACTIVITY, WaitForActivity);
        }
        */
        _androidDriver = new AndroidDriver(Server_URL, cap);
    }
    
     public void ClickUIComponent(String ComponentID)
     { 
        _androidDriver.findElementById(ComponentID).click();
     }
     
     public String GetPageXML()
     {
         // remove checked component and return XML
         return _androidDriver.getPageSource().replace("checked=\"false\"", "").replace("checked=\"true\"", "");
     }
     
      
}
