/*
 * Note:  Changed default main package by
 * changing \resources\META-INF\MANIFEST.MF
 *
 * from
 * MIDlet-1: Robot, , edu.wpi.first.wpilibj.templates.Robot
 * to
 * MIDlet-1: Robot, , main.Robot
 *
 * 2010-04-14 from Maffet, formatted by Hays
 */

package edu.wpi.first.wpilibj.templates;

// ####### Project Imports ####### //

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStation;


public class Robot extends IterativeRobot {

  public int count;

  Victor leftJag            = new Victor(1);
  Victor rightJag           = new Victor(2);
  Victor suckerVict         = new Victor(3);
  Victor kickMotor          = new Victor(4);

  Victor kickRelease        = new Victor(8);
  Servo kickClutch          = new Servo(7);

  //Arm Actutator objects
  Victor armExtend          = new Victor(6);
  //Victor armPivot           = new Victor(5);// no longer needed
  Encoder coder             = new Encoder(1,2);
  Encoder coder2            = new Encoder(3,4);

  DigitalInput kickLimit    = new DigitalInput(5);
  DigitalInput banner       = new DigitalInput(9);
  DigitalInput cockedSwitch = new DigitalInput(10);

  Servo winchClutch         = new Servo (10); //need left & right winch servo (9,10)
  Servo winchClutch2        = new Servo (9);

  Joystick leftJoy          = new Joystick (1);
  Joystick rightJoy         = new Joystick (2);

  int leftButton = 4, rightButton = 5, midButton = 3;

  RobotDrive driver = new RobotDrive(leftJag,rightJag);
  DriverStationEnhancedIO dseio;
  BallSucker sucker;
  Drive drive;
  Kicker kicker;
  Arm2 arm;
  elecTest test;
  ServoTest srvTest;
  MaffettWinch winch;
  MaffettKickerReset kckreset;
  TrackerDashboard trackerDashboard;
  Watchdog watchdog = Watchdog.getInstance();
  AutonActions auton;
  //CircleTrackerDemo cam = new CircleTrackerDemo(driver);
  public static double leftJagValue;
  public static double rightJagValue;
  //public booleaDriverStationEnhancedIOn first,second;
  boolean out,in,stop;
  boolean suck,testEn,kckres;

  public void robotInit() {
    watchdog.feed();
    trackerDashboard = new TrackerDashboard();
    winch = new MaffettWinch(winchClutch,winchClutch2);
    //srvTest = new ServoTest(kickRelease);
    test = new elecTest();
    kckreset=new MaffettKickerReset(kickRelease);
    sucker = new BallSucker (suckerVict);//,anaChannel);
    // driveWinch = new Winch (leftJoy, rightJoy, winchClutch);
    drive = new Drive(leftJoy, rightJoy);
    kicker = new Kicker(kickMotor,
                        kickLimit,
                        cockedSwitch,
                        kickRelease,
                        kickClutch);
    arm = new Arm2(armExtend,out,in,stop);
    try {
      //kicker = new Kick(kickClutch, kickRelease, kickMotor, cockedSwitch);
      //coder.start();
      //coder2.start();
      //circle = new CircleTrackerDemo(/*kicker*/);
      dseio = DriverStation.getInstance().getEnhancedIO();
      dseio.setDigitalConfig(8, DriverStationEnhancedIO.tDigitalConfig.kInputPullUp);
      auton = new AutonActions(leftJag,
                               rightJag,
                               kickMotor,
                               kickRelease,
                               kicker,
                               sucker,
                               coder,
                               coder2);
    }
    catch(Exception e){
      // System.err.println("we couldn't set the cypress dio to our desired mode");
      System.out.println(e);
    }
  }

  /**
   * This function is called periodically during autonomous
   */
  public void autonomousPeriodic() {
    //irst=dseio.getDigital(2);
    // second=dseio.getDigital(8);
    auton.action();
  }

  /**
   * This function is called periodically during operator control
   */
  public void teleopPeriodic() {
    watchdog.feed();
    // drive.DriveHandler();
    watchdog.setExpiration(1);
    stop = false;// need to set to the banner sensor
    try {
      if(dseio.getDigital(1) == true &&
         dseio.getDigital(3) == true &&
         dseio.getDigital(5) == true) {
        kckres=true;
        if(rightJoy.getRawButton(6)) {
          kckreset.presser();
          kckreset.setLeft();
        }
        else if(rightJoy.getRawButton(7)) {
          kckreset.presser();
          kckreset.setRight();
        }
      }
      else {
        kckres=false;
      }

      if(dseio.getDigital(7)) {
        winch.winchRunner();
      }
      else if(dseio.getDigital(7) == false) {
        winch.winchRunnerUndo();
      }
        
      if(dseio.getAnalogIn(1) < 1.0) {
        arm.outFalse();
        arm.inTrue();
      }
      else if(dseio.getAnalogIn(1) > 2.0) {
        arm.outTrue();
        arm.inFalse();
      }
      else {
        arm.outFalse();
        arm.inFalse();
      }

      if(!dseio.getDigital(6)) {
        testEn = true;
      }
      else {
        testEn = false;
      }

      if(dseio.getDigital(2) == true) {
        suck = true;
      }
      else {
        suck = false;
      }
    }
    catch(Exception e) {
      System.err.println(e);
    }

    if(kckres == true) {
      kckreset.KickerReset();
    }

    if (testEn == true) {//autonomous mode switch all the way to right
      test.test(leftJoy , 4,  leftJag);
      test.test(leftJoy , 3,  rightJag);
      test.test(leftJoy , 5,  suckerVict);
      test.test(leftJoy , 2,  kickMotor);
      test.test(leftJoy , 11, kickRelease);
      test.test(leftJoy , 10, armExtend);
      test.test(rightJoy, 6,  kickClutch);
      test.test(rightJoy, 8,  winchClutch);
      test.test(rightJoy, 9,  winchClutch2);
      System.out.println("1 encoder " + coder.get());
      System.out.println("2 encoder " + coder2.get());
    }
    else if(testEn == false && kckres == false) { //if normal stops working change this
      /* if(leftJoy.getRawButton(11)) {             // start of srvo test stuff
        srvTest.buttonPressed();                  // get the values from this
      }
      if(leftJoy.getRawButton(9)) {               // then comment out durring comp.
        srvTest.reZero1();                        // 11 incriments by .1
      }
      if(leftJoy.getRawButton(8)) {               // 9 sets it back to middle
        srvTest.reZero2();// going right, 8 puts it back
      }
      // going left.*/
      leftJag.set(leftJoy.getY());
      rightJag.set(-rightJoy.getY());
      sucker.ballSuckerHandler(suck);
      kicker.KickMechanism();
      arm.Arm2Call();
      //srvTest.servoTester();
      if(rightJoy.getRawButton(1)  == true ||
         rightJoy.getRawButton(1)  == true ||
         rightJoy.getRawButton(11) == true ||
         rightJoy.getRawButton(10) == true ||
         leftJoy.getRawButton(1)   == true ||
         leftJoy.getRawButton(6)   == true ||
         leftJoy.getRawButton(7)   == true) {
        sucker.turnOff();
        kicker.Kick();
      }
    }
  }

  public static void println(String s) {
    System.out.println(s);
  }
}