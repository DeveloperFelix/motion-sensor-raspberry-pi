/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IOT;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.dio.DeviceManager;
import jdk.dio.gpio.GPIOPin;

/**
 *
 * @author Anon
 */
public class LED {
    
    
   private GPIOPin gpio_pin;
    
LED(int pin_id){
        try {
            gpio_pin=(GPIOPin) DeviceManager.open(pin_id);
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
        
    }
public void on(){
        try {
            gpio_pin.setValue(true);
        } catch (IOException ex) {
            Logger.getLogger(LED.class.getName()).log(Level.SEVERE, null, ex);
        }
}
public void off(){
        try {
            gpio_pin.setValue(false);
        } catch (IOException ex) {
            Logger.getLogger(LED.class.getName()).log(Level.SEVERE, null, ex);
        }
}
  public void blink(int ms){
      
       for(int i=0; i<5; i++){
           try {
               gpio_pin.setValue(true);
               Thread.sleep(ms);
               gpio_pin.setValue(false);
               Thread.sleep(ms);
           } catch (IOException | InterruptedException ex) {
               
               System.out.println("Blink:"+ex.getMessage());
           }
       }

  }
  

}
