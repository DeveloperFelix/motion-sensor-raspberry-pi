/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IOT;

import java.io.IOException;
import javax.microedition.midlet.MIDlet;
import jdk.dio.DeviceManager;
import jdk.dio.gpio.GPIOPin;

/**
 *
 * @author Anon
 */
public class IOT extends MIDlet{
    
    
//    int red_led          = 6; //GPIO23
    
   private final int sensor_device_id = 1; //GPIO4
   private final int blue_led         = 4; //GPIO18
    
    boolean detect       = false;
    
    private LED          led;
    private GPIOPin      pin;
    
    MotionThread motion_thread;
    
    @Override
    public void startApp() {
   
            try {
                
                led = new LED(blue_led);
                pin = (GPIOPin) DeviceManager.open(sensor_device_id);
                
            } catch (IOException ex) {
                 System.out.println(ex.getMessage());
            }
            
          motion_thread = new MotionThread();
          motion_thread.start();
    }
    @Override
    public void pauseApp() {
    }
    @Override
    public void destroyApp(boolean unconditional) {
       detect=true;
    }
    private class MotionThread extends Thread{

        @Override
        public void run(){
            
            while(!detect){
                
                try {
                    
                    if(pin.getValue()){
            
                          led.blink(50);
                    }
                          led.off();

                     Thread.sleep(1000);
                
                   } catch (IOException | InterruptedException ex) {
                      System.out.println(ex.getMessage());
                }
            }
        }
    }
}
