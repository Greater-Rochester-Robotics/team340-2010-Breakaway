/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.AnalogChannel;

public class ultrasonicBallSeeker {
    private Jaguar leftJag;
    private Jaguar rightJag;

    private AnalogChannel leftRangeFinder;
    private AnalogChannel rightRangeFinder;
    public ultrasonicBallSeeker(AnalogChannel left, AnalogChannel right){
        leftRangeFinder = left;
        rightRangeFinder = right;
    }

    public void seekBall()
    {
        double deltaVoltage = leftRangeFinder.getVoltage() - rightRangeFinder.getVoltage();
        if(deltaVoltage > .5)
        {
            //turn right
        }else if(deltaVoltage<-.5){
            //turn left
        }else{
            //go forward
        }

    }
}
