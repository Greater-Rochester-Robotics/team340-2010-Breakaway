/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author JShortino
 */
public class Kick {
    private Servo release;
    private Servo clutch;
    private Victor kickMotor;
    private DigitalInput latch;
    private double time;
    private Timer timer;

    public Kick(Servo cltch, Servo rel, Victor kickMot, DigitalInput ltch)
    {
        clutch = cltch;
        release = rel;
        latch = ltch;
        clutch.set(.75);
        release.set(0);
        timer = new Timer();
        timer.start();
        time = timer.get();
        kickMotor = kickMot;
    }

    public void kick()
    {
        clutch.set(.75);
        release.set(.25);
        time = timer.get();
    }

    public void kickPeriodic()
    {
        double t = timer.get() - time;


             if(t >= .4 && t < .7)
             {
                 release.set(.9);
                 clutch.set(.25);
             }

           else if(t <= 1.9)
                {
                    if(latch.get())
                    {
                        kickMotor.set(1);
                    }
                    else
                        kickMotor.set(0);
                }


                else
                {
                kickMotor.set(0);
                clutch.set(.75);
                }
        }



}
