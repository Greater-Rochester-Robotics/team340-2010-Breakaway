/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Victor;
/**
 *
 * @author JRees
 */
public class BallHandler {

    Victor sck;
    public BallHandler(Victor vic)
    {
        sck=vic;
    }
    public void suckIn()
    {
        sck.set(1);
    }
    public void turnOff()
    {
        sck.set(0);
    }
    public void suckOut()
    {
        sck.set(-1);
    }

}
