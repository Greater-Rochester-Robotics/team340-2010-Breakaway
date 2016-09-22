package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Victor;

public class MaffettKickerReset {
  Victor kick;
  private boolean pressed;
  private int currstate;
  private int count;
  private boolean flip1,flip2;

  public MaffettKickerReset(Victor kicker) {
    kick      = kicker;
    pressed   = false;
    flip1     = false;
    flip2     = false;
    currstate = 0;
    count     = 0;
  }

  public void KickerReset() {
    switch(currstate) {
      case 0: {
        kick.set(0);
        if(flip1 == true) {
          currstate = 1;
          flip1     = false;
        }
        else if(flip2 == true) {
          currstate = 2;
          flip2     = false;
        }
        break;
      }
      case 1: {
        if(pressed == true) {
          kick.set(1);
          count++;
          if (count > 2) {
            kick.set(0);
            currstate = 0;
            pressed   = false;
          }
        }
        break;
      }
      case 2: {
        if(pressed==true) {
          kick.set(-1);
          count++;
          if (count>2) {
            kick.set(0);
            currstate=0;
            pressed=false;
          }
        }
        break;
      }
    }
  }
  public void setLeft() {
    flip1 = true;
  }

  public void setRight() {
    flip2 = true;
  }

  public void presser() {
    pressed = true;
  }
}