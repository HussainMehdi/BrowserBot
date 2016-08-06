/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package browser_bot;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hussain-X201
 */
public class TimeoutThread extends Thread {
    @Override
    public void run()
    {
        try {
            Thread.sleep(30000);
            Browser_Bot.Timeout=true;
        } catch (InterruptedException ex) {
            Logger.getLogger(TimeoutThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
