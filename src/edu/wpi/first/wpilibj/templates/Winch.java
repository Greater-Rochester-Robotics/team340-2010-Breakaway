

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class Winch {

    private Joystick joy;
    private Servo winchServo;
    private final double ENGAGE_WINCH =    1.0;
    private final double DISENGAGE_WINCH = 0.0;
    private final double PULL_UP_SPEED = 0.5;
    private final int WINCH_VAL = 5;
    private double time;
    private int loop = 0;
    private Timer timer;
    private boolean pulling = false;

    public Winch(Joystick j, Servo cltch)
    {
        //leftJag = lft;
        //rightJag = rgt;
        joy = j;
        winchServo = cltch;
        this.disengageWinch();
    }
    public void DriveAndWinchHandler(int button1)
    {
        /*if(leftJoy.getRawButton(button1) && rightJoy.getRawButton(button1))
            //press both buttons at the same time engage winch
        {
            this.engageWinch();
            if(loop >= WINCH_VAL)
            {
                if(Robot.leftJagValue<0)
                    Robot.leftJagValue=0;

                if(Robot.rightJagValue<0)
                    Robot.rightJagValue=0;
            }
            else
                loop++;
        }*/
        if(joy.getRawButton(button1))
            //if you press one button pull up
        {
            if(winchServo.get() != ENGAGE_WINCH)
                this.engageWinch();
            if(loop >= WINCH_VAL)
            {
                this.pullUp();
            }
            else
                loop++;
        }
        else if(!pulling)
            //disengage winch
        {
            this.disengageWinch();
            loop = 0;
      }
    }

    public void engageWinch()
    {
        winchServo.set(ENGAGE_WINCH);
        time = timer.get();
    }

    public void disengageWinch()
    {
        winchServo.set(DISENGAGE_WINCH);
    }
    public void pullUp()
    {
        if(timer.get() - time >= 1)
        {
           Robot.leftJagValue = PULL_UP_SPEED;
           Robot.rightJagValue = PULL_UP_SPEED;
        }
        pulling = true;
    }
}
