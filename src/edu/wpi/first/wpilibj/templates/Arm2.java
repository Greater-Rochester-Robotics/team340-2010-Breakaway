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

/**
 *
 * @author JRees
 */
public class Arm2 {
  // Assign passed in objects to local names
  private Victor ArmMotor;
  private boolean Out;
  private boolean In;
  private boolean Bnr;
  private boolean RD;
  // create a local timer
  private Timer time = new Timer();

  private int RmpCntr;
  //cases
  private final int NoMove    = 0;
  private final int OutRmpUp  = 1;
  private final int OutSteady = 2;
  private final int OutRmpDn  = 3;
  private final int OutStop   = 4;
  private final int InRmpUp   = 5;
  private final int InSteady  = 6;
  private final int InRmpDn   = 7;
  private final int InStop    = 8;

  private int currState;

  private final double MaxOutSpd =  0.35;
  private final double MaxInSpd  = -0.65;

  private final double StepSize  =  0.01;

  private double ArmSpd;

  //Constructor
  public Arm2(Victor lclVic,
              boolean lclOut,
              boolean lclIn,
              boolean lclBnr)
  {
    // Assign objects passed in to local references
    ArmMotor    = lclVic;
    Out         = lclOut;
    In          = lclIn;
    Bnr         = lclBnr;
    RD          = false;

    // Initialize local variables
    currState   = NoMove;
    ArmSpd      = 0;

    // Start the local timer
    time.start();
    System.out.println("Kicker is initialized!!!");
  }
  //Method1
  public void Arm2Call()
  {

    switch(currState){
      ////////////////////////////////////////////////
      case (NoMove):
        RD        = false;
        RmpCntr   = 0;
        ArmSpd    = 0;
        if (Out == true) {
          currState = OutRmpUp;
        }
        else if (In == true){
          currState = InRmpUp;
        }
        break;

        ////////////////////////////////////////////
      case (OutRmpUp) :
        ArmSpd = ArmSpd + StepSize;
        if (Out == false){
            currState = OutRmpDn;
        }

        else if(ArmSpd >= MaxOutSpd){
            currState = OutSteady;
        }
        break;
        ////////////////////////////////////////////////
      case (OutSteady) :
        if (Out == false || Bnr == true){
            currState = OutRmpDn;
        }
        break;
        ////////////////////////////////////////////////
        case (OutRmpDn) :
        if (ArmSpd >= 0){
            ArmSpd = ArmSpd - StepSize;
        }

        if(ArmSpd <= 0){
            currState = OutStop;
        }
        break;
        //////////////////////////////////////////////////
        case (OutStop) :
        if (Out == false){
            currState = NoMove;
        }
        break;
        /////////////////////////////////////////////////////
     case (InRmpUp) :
        ArmSpd = ArmSpd - StepSize;
        if (In == false){
            currState = InRmpDn;
        }

        else if(ArmSpd <= MaxInSpd){
            currState = InSteady;
        }
        break;
        ////////////////////////////////////////////////
      case (InSteady) :
        if ( In == false || Bnr == true){
            currState = InRmpDn;
        }
        break;
        ////////////////////////////////////////////////
        case (InRmpDn) :
        if (ArmSpd <= 0){
            ArmSpd = ArmSpd + StepSize;
        }

        if(ArmSpd >= 0){
            currState = InStop;
        }
        break;
        //////////////////////////////////////////////////
        case (InStop) :
        if (In == false){
            currState = NoMove;
        }
        break;
    }
    ArmMotor.set(ArmSpd);
  }
  public void outTrue()
  {
      Out = true;
  }
  public void inTrue()
  {
      In = true;
  }
  public void outFalse()
  {
      Out = false;
  }
  public void inFalse()
  {
      In = false;
  }
}
