/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;
/*
//TODO: switch to using alternate blocking function call with specific task
//TODO: fix PCVideo server failure killing crio
//TODO: Tune loop better
//TODO: add more joystick functionality
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
//import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.NIVisionException;
//import edu.wpi.first.wpilibj.Servo;
//import java.lang.Math;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
public class CircleTrackerDemo{

    double kScoreThreshold = .01;
    final float SERVO_INC = (float).007;
    float camX = (float).5,
          camY = (float).5,
          tiltInc = (float).1,
          camLow = (float).5,
          camHigh = (float) .9,
          centerLimit = (float).5,
          centerDec= (float).5;
    final int accuracy = 10;
    int loop = 0,
        panDir = 1,
        kickLoop = 0;
    boolean lastTrigger = false;
    AxisCamera cam;
    Gyro gyro;
    //RobotDrive drive = new RobotDrive(1, 2);
    PIDController turnController;
    TrackerDashboard trackerDashboard;
   // Kicker kicker;

    private boolean enabled = false;

    //////////////////////////////////////////////////////////
    // Constructor
    //////////////////////////////////////////////////////////
    public CircleTrackerDemo(){
        gyro = new Gyro(1);

        turnController = new PIDController(.08,
                                           0.0,
                                           0.5,
                                           gyro,
                                           new PIDOutput() {

            public void pidWrite(double output) {
                  rotate((float)(output));
            }
        }, .005);
        trackerDashboard = new TrackerDashboard();
        Timer.delay(10.0);

        cam = AxisCamera.getInstance();
        cam.writeResolution(AxisCamera.ResolutionT.k320x240);

        //cam.writeResolution(AxisCamera.ResolutionT.k640x480);

        cam.writeBrightness(0);
        gyro.setSensitivity(.007);

        turnController.setInputRange(-360.0, 360.0);
        turnController.setTolerance(1 / 90. * 100);
        turnController.disable();
        lastTrigger = false;
    }
    
    public void setEnabled(boolean en)
    {
        enabled = en;
    }

    public void camPeriodic()
    {
        target();
        Robot.println("Angle = " + gyro.getAngle());
    }

    //////////////////////////////////////////////////////////
    // target method
    //////////////////////////////////////////////////////////
    public boolean target() {
        boolean output = false;
        long startTime = Timer.getUsClock();
        if (!enabled) {
            if (lastTrigger) {
                turnController.disable();
            }
            lastTrigger = false;
        } else {
            if (!lastTrigger) {
                //System.out.println("ThereHere1");
                turnController.enable();
                turnController.setSetpoint(gyro.pidGet());
                //System.out.println("ThereHere2");
            }
            lastTrigger = true;
            ColorImage image = null;
            //image.free();
            try {
                //System.out.println("ThereHere3");
                if (cam.freshImage()) {// && turnController.onTarget()) {
                    double gyroAngle = gyro.pidGet();
                    image = cam.getImage();
                    //Thread.yield();
                    Target[] targets = Target.findCircularTargets(image);
                    //Thread.yield();
                    if (targets.length == 0 ||
                        targets[0].m_score < kScoreThreshold) {
                        System.out.println("No target found");
                        Target[] newTargets = new Target[targets.length + 1];
                        newTargets[0] = new Target();
                        newTargets[0].m_majorRadius = 0;
                        newTargets[0].m_minorRadius = 0;
                        newTargets[0].m_score = 0;
                        for (int i = 0; i < targets.length; i++) {
                            newTargets[i + 1] = targets[i];
                        }
                        trackerDashboard.updateVisionDashboard(0.0,
                                                               gyro.getAngle(),
                                                               0.0,
                                                               0.0,
                                                               newTargets);
                    } else {
                        //System.out.println(targets[0]);
                        if(targets[0].getHorizontalAngle() < accuracy)
                        {
                            output = true;
                        }
                        //System.out.println("Target Angle: " +
                        //                    targets[0].getHorizontalAngle());
                        turnController.setSetpoint(gyroAngle +
                                                   targets[0].getHorizontalAngle());
                        trackerDashboard.updateVisionDashboard(0.0,
                                               gyro.getAngle(),
                                               0.0,
                                               targets[0].m_xPos / targets[0].m_xMax,
                                               targets);
                    } 
                }
            } catch (NIVisionException ex) {
                System.err.println("NIVisionException1");
                ex.printStackTrace();
            } catch (AxisCameraException ex) {
                System.err.println("AxisCameraException");
                ex.printStackTrace();
            } finally {
                try {
                    if (image != null) {
                        image.free();
                    }
                } catch (NIVisionException ex) {
                    System.err.println("NIVisionException");
                    ex.printStackTrace();
                }
            }
            Robot.println("Time : " + (Timer.getUsClock() - startTime) / 1000000.0);
            Robot.println("Gyro Angle: " + gyro.getAngle());
        }
        Robot.println("" + output);
        return output;
    }

    public boolean center()
    {
        float newAngle = checkBounds((float)gyro.getAngle());
        if(newAngle < -centerLimit)
            rotate(-1);
        else if(newAngle > centerLimit)
            rotate(1);
        float oldAngle = Math.abs(newAngle);
        newAngle = checkBounds((float)gyro.getAngle());
        while(Math.abs(newAngle/oldAngle) > centerDec)
        {
            rotate(-newAngle/oldAngle);
            oldAngle = newAngle;
            newAngle = (float)gyro.getAngle();
        }

        return true;
    }

    private float checkBounds(float n)
    {
        n%=360;
        if(n < -180)
            n+=360;
        else if(n > 180)
            n-=360;
        return n;
    }

    private synchronized void rotate(float n)
    {
        Robot.rightJagValue = n;
        Robot.leftJagValue = -n;
    }

     /*private void tilt()
    {
        if(yServo.get()>(camHigh-tiltInc))
        {
            panDir = -1;
            System.out.println("panDir changed to -1");
        }
        else if(yServo.get()<(camLow +tiltInc))
        {
            panDir = 1;
            System.out.println("panDir changed to 1");
        }
        yServo.set(yServo.get()+tiltInc*panDir);

        if(loop == 24)
        {
            loop = 0;
            System.out.println("panning and panDir is " + panDir);
        }
        else
            loop++;
    }
}*/
