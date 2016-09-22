package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;


public class BallSucker
{
    public Victor ballSuckerMotor;
    private AnalogChannel ultrasonic;
    private Joystick joystick;
    private DriverStationEnhancedIO cypress;

    public BallSucker(Victor jag)//, AnalogChannel ana)
    {
        ballSuckerMotor = jag;
        //ultrasonic = ana;
    }

    public void ballSuckerHandler(boolean button1)
    {
        double ballSuckerMotorVal=0;
       
       
            if (button1 == true)
            {
                //if(ultrasonic.getVoltage()>.1)
                    ballSuckerMotorVal=-1;//in
                //else
                //    ballSuckerMotorVal=0;//stop
            }
            else
            {
                ballSuckerMotorVal=0;
            }
            ballSuckerMotor.set(ballSuckerMotorVal);
            //System.err.print("unable to reach cypress board dio for ball sucker");
            //ballSuckerMotor.set(-1);
        }
    

    public void suckOut()
    {
        ballSuckerMotor.set(1);
    }
     public void turnOn()
     {
         ballSuckerMotor.set(-1);
     }  
     
     public void turnOff()
     {
         ballSuckerMotor.set(0);
     }
}



/*GRAVEYARD
 * if(!joystick.getRawButton(button1)&&!joystick.getRawButton(button2))
        {
            ballSuckerMotorVal=0; //stop
        }
        else if (!joystick.getRawButton(button1)&&joystick.getRawButton(button2))
        {
            ballSuckerMotorVal=-1; //out
        }
        else if (joystick.getRawButton(button1)&&!joystick.getRawButton(button2))
        {
            ballSuckerMotorVal=1; //in
        }
        else if (joystick.getRawButton(button1)&& joystick.getRawButton(button2))
        {
            ballSuckerMotorVal=1; //in
        }
        if (joystick.getRawButton(4)==true) //change to whatever kicker is
        {
            ballSuckerMotorVal=0; //stop
        }*/