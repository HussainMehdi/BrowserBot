/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browserbot;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 *
 * @author Hussain
 */
public class AppiumController
{
    private AndroidDriver _androidDriver;
    public long CommandTimeout = 1800;
    public boolean NoReset = true;
    public boolean FullReset = false;
    public boolean SaveIterationCache = false;
    public String AppPackage;
    public String DeviceName;
    public String AppActivity;
    public String ServerUrl;   

    DesiredCapabilities cap = new DesiredCapabilities();
     /**
     * Get the Primary AndroidDriver
     * @return Android Driver
     */
    public AndroidDriver getDriver(){return _androidDriver;}
    
     /**
     * Get the screenshot of current activity
     * @return Screenshot File
     */
    public File getScreenshot() throws IOException{
        if(_androidDriver!=null)
         return _androidDriver.getScreenshotAs(OutputType.FILE);
        return null;
    }
    
  
     /**
     * Starts the Appium Android Driver for specified app
     * @param AppPath Path of application to launch
     * @param DeviceName If multiple devices are connected, set device name to launch that app onto
     * @param WaitForActivity Start Driver and wait for other operations until that activity is not launched
     * @param Server_URL  URL where Appium Server is listening
     */
     public void AppiumDriverStart() throws MalformedURLException, IOException
    {
       /* if (null == AppPath ||
            null == DeviceName ||
            null == WaitForActivity ||
            null == ServerUrl)
        {
            System.err.println("One or more required parameters not specified. Aborting AppiumDriverStart invocation.");
            return;
        }
        */
        
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
        cap.setCapability(MobileCapabilityType.APP_PACKAGE, AppPackage);
        cap.setCapability(MobileCapabilityType.APP_ACTIVITY, AppActivity);
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, CommandTimeout);
        //cap.setCapability("autoAcceptAlerts", true);
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, DeviceName);
        //cap.setCapability("autoLaunch", "false");
        cap.setCapability("fullReset", FullReset);
        cap.setCapability("noReset", NoReset);
        _androidDriver = new AndroidDriver(new URL(ServerUrl), cap);
    }
    
     public void ClickUIComponent(String ComponentID)
     { 
        _androidDriver.findElementById(ComponentID).click();
     }
     
     
     
     
}
