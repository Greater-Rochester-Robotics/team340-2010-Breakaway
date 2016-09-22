/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Servo;

public class Kicker {
  // Assign passed in objects to local names
  private Victor kickMotor;
  private DigitalInput limit;
  private DigitalInput cockedSwitch;
  private Victor kickRelease;
  private Servo clutchRelease;

  // create a local timer
  private Timer time = new Timer();

  //time variables
  private double kickMotorValue;
  private double kickStart=0;
  private double kickEnd;
  private double retractStart;
  private double retractEnd;
  private double limitWaitEnd;

  // Motor drive constants
  private final double KICK_MOTOR_RETRACT = -1.0;
  private final double KICK_MOTOR_OFF     = 0.0;
  
  // Servo Actuator constants
  private final double CLUTCH_PIN_OUT     = 0.5;
  private final double CLUTCH_PIN_IN      = 0.0;
  private final double KICK_GATE_CLOSE    = 1.0;
  private final double KICK_GATE_OPEN     = 0.33;
  
  // Time constants
  private final double TWO_SEC_COUNT      = 2.0;
  private final double KICK_DURATION      = 0.75;
  private final double RETRACT_TIME_LIMIT = 1.5;
  private int count;

  private boolean kickEnabled;
  private boolean kickerReady;

  //cases
  private final int START = 0;
  private final int CLUTCHENGAGE = 1;
  private final int COCKING = 2;
  private final int OVERDRIVE = 3;
  private final int BACKUP = 4;
  private final int RELEASECLUTCH =5;
  private final int READY =6;
  private final int KICKING =7;
  private final int WAIT = 8;
  private final int RESET = 9;
  

  private int currState;

  //Constructor
  public Kicker(Victor lclVic,
                DigitalInput lclLim,
                DigitalInput lclSwitch,
                Victor lclRelease,
                Servo lclClutchRelease)
  {
    // Assign objects passed in to local references
    kickMotor    = lclVic;
    cockedSwitch = lclSwitch;
    kickRelease  = lclRelease;
    clutchRelease   = lclClutchRelease;
    limit = lclLim;

    // Initialize local variables
    currState   = START;
    kickerReady = false;
    kickEnabled = false;

    // Start the local timer
    System.out.println("Kicker is initialized!!!");
  }
  //Method1
  public void KickMechanism()
  {
    
    switch(currState){
      case(START):
        kickEnabled = false;
        kickRelease.set(0);
        count=0;
        currState= CLUTCHENGAGE;
        break;

      case(CLUTCHENGAGE):  //engageing clutch
        clutchRelease.set(0);
        count++;
        if(count>20)
        {
            currState=COCKING;
            count=0;
        }
        break;

      case(COCKING)://pulling back
        kickMotor.set(-1);
        count++;
        System.out.println(count);
        if(count>38)
        {
            count = 0;
            currState =BACKUP;
            
        }
        break;

     /* case(OVERDRIVE)://pushing little more
         count ++;
         if(count<7)
         {
            kickMotor.set(1);
         }
         else
         {
             kickMotor.set(0);
             count = 0;
             currState= BACKUP;
         }
        break;*/

      case(BACKUP):
        count++;
        if(count<10)
        {
            kickMotor.set(.3);
        }
        else
        {
            kickMotor.set(0);
            currState = RELEASECLUTCH;
            count=0;
        }
         
        break;
      case(RELEASECLUTCH):
        count++;
        if(count<40)
        {
            clutchRelease.set(.5);
        }
        else
        {
            currState = READY;
            count = 0;
        }
        break;
      case(READY):
        currState = KICKING;
        break;
      case(KICKING):
        if(kickEnabled==true)
        {
            kickRelease.set(-1);
            currState = WAIT;
        }
        break;
      case(WAIT):
          count++;
          if(count>22)
          {
              kickRelease.set(0);
              currState = RESET;
              count=0;
          }
        break;
      case(RESET):
      {
          
          count++;
          if(count<22)
          {
              kickRelease.set(1);
          }
          else
          {
              currState = START;
              count=0;
          }
          break;
      }
    }
  }

  //Method2
  public void Kick()
  {
    //System.out.println("Lclint: " +lcl);
    kickEnabled = true;
  }

  // Method3
  public boolean KickReady()
  {
    return cockedSwitch.get();
  }
}
