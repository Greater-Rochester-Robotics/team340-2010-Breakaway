/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Servo;
/**
 *
 * @author JRees
 */
public class MaffettWinch
{
    Servo srv1,srv2;
    public MaffettWinch(Servo servo1,Servo servo2)
    {
        srv1=servo1;
        srv2=servo2;
    }
    public void winchRunner()
    {
        srv1.set(.25);//10
        srv2.set(.75);//9
    }
    public void winchRunnerUndo()
    {
        srv1.set(.75);//10
        srv2.set(.25);//9
    }

}
