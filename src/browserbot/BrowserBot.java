/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browserbot;

import java.io.IOException;

/**
 *
 * @author Hussain-X201
 */
public class BrowserBot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        AppiumController appiumController = new AppiumController();
        appiumController.AppActivity = "org.chromium.chrome.browser.ChromeTabbedActivity";
        appiumController.AppPackage  = "com.android.chrome";
        appiumController.ServerUrl   = "http://127.0.0.1:4723/wd/hub";
        
        appiumController.AppiumDriverStart();
        
        appiumController.getDriver().get("http://apple.com");
    }
    
}
