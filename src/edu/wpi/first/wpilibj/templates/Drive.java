
package edu.wpi.first.wpilibj.templates;

//import Robot.Robot;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick;

public class Drive
{
    private Joystick leftJoy;
    private Joystick rightJoy;
    public Drive(Joystick lft, Joystick rgt)
    {
        leftJoy = lft;
        rightJoy = rgt;
    }

    public void DriveHandler()
    {
        Robot.leftJagValue=leftJoy.getY();
        Robot.rightJagValue=rightJoy.getY();
    }
}