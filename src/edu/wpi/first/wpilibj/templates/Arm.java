/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/*
 * 8-2 arm angle switch
 * 7-4 arm extend switch
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;

/**
 *
 * @author Justin Shortino
 */
public class Arm {
    private boolean lastBanner;
    private Victor armPivot;
    private Victor armExtend;
    //private Joystick joy;
    private DigitalInput banner, angleMax, angleMin;
    private int  pos = -1;
    private DriverStationEnhancedIO cypress;
    private Joystick joy;
    private final double ARM_EXTEND = 1;
    private final double ARM_RETRACT = -1;
    private final double ARM_PIVOT_TO_MAX = 1;
    private final double ARM_PIVOT_TO_MIN = -1;

    ////////////////////////////////////////////
    // Constructor
    ////////////////////////////////////////////
    public Arm(Victor pivot,
               Victor extend,
               DigitalInput ban,
               DigitalInput angMax,
               DigitalInput angMin,
               Joystick j) {
        armPivot = pivot;
        armExtend = extend;
        banner = ban;
        angleMax = angMax;
        angleMin = angMin;
        lastBanner = banner.get();
        joy = j;
    }

    ////////////////////////////////////////////////////////////////////
    // Extend Method
    // Expects to used buttons on the Joystick
    //   One for up and one for down
    // Banner sensor detects a reflective target on the timing belt
    //   One piece of tape is detected when the Arm is retracted
    //   One piece of tape is detected when the Arm is extended
    //   You CANNOT determine if the arm is extended or retracted
    //      by the Banner Switch.
    ///////////////////////////////////////////////////////////////////


    public void ArmExtend() {
        boolean bannerState = banner.get();

        //if(joy.getRawButton(btnExtend) == true && !(!lastBanner && temp)) {
        try{
            if(joy.getRawButton(2) && pos != 1){
                armExtend.set(1);
                if(pos == -1 && !bannerState)
                    pos = 0;
                else if(pos == 0 && !lastBanner && bannerState)
                    pos = 1;

            }
            //else if(joy.getRawButton(btnRetract) == true && !(!lastBanner && temp)) {
            else if(joy.getRawButton(3) && pos != -1) {
                armExtend.set(-1);
                if(pos == 1 && !bannerState)
                    pos = 0;
                else if(pos == 0 && !lastBanner && bannerState)
                    pos = -1;
            }
            else {
                armExtend.set(0);
            }
            lastBanner = bannerState;
        }catch(Exception e){
            System.err.print(e);
        }


    }

    ////////////////////////////////////////////////////////////////////
    //
    public void ArmPivot() {
        //if(joy.getRawButton(btnUp) == true && (!angleMax.get())) {
        try{
            if(joy.getY() <= -.5 && (!angleMax.get())) {
                armPivot.set(-.6);
            }else if(joy.getY() >= .5 && (!angleMin.get())) {
                armPivot.set(.4);
            }else{
                armPivot.set(0);
            }
        }catch(Exception e){
            System.err.print(e);
        }
    }
}
