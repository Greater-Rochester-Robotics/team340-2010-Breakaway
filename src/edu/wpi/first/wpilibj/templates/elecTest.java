/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;


public class elecTest {
    
    double pos = .5;
    Timer time = new Timer();
    final double WAIT = 1;


    public elecTest(){

    }

    public void test(Joystick joy, int btn, Victor vic){
        if(joy.getRawButton(btn) == true){
            vic.set(joy.getY());
        }
        else
        {
            vic.set(0);
        }
    }

    public void test(Joystick joy, int btn, Servo serv){
        if(joy.getRawButton(btn) == true)
        {

            if(joy.getRawButton(4) == true && time.get() >= WAIT)
            {
                time.reset();
                pos = pos-.1; // servo increment down .1
                if (pos <0)
                {
                    pos = 0; //min pos = 0
                }
                time.start();
            }
            else if(joy.getRawButton(3) == true && time.get() >= WAIT)
            {
                time.reset();
                pos = .5; // center servo
                time.start();
            }
            else if(joy.getRawButton(5) == true && time.get() >= WAIT)
            {
                time.reset();
                pos = pos+.1;// servo increment up .1
                if (pos > 1)
                {
                    pos = 1; // max pos = 1
                }
                time.start();

            }
            serv.set(pos);
        }

}
}

