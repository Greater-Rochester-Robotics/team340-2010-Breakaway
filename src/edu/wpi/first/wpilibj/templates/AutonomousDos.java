/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author JShortino && MarkMark
 */
public class AutonomousDos {
    private static final int DRIVE_FORWARD = 0;
    private static final int STOP = 1;
    private static final int PAUSE = 2;
    private static final int DONE = 3;
    private static final int KICK = 4;
    private static final int DRIVE_FORWARD2 = 5;
    private static final int DRIVE_FORWARD3 = 6;
    int stateIndex;

    private static final int ENC_REV_FORWARD_1 = 40, ENC_REV_FORWARD_2 = 80, ENC_REV_FORWARD_3 = 120;

    int events[] = {
        DRIVE_FORWARD,
        STOP,
       // KICK,
        PAUSE,
        DRIVE_FORWARD2,
        STOP,
      //  KICK,
        PAUSE,
        DRIVE_FORWARD3,
        STOP,
       // KICK,
        PAUSE
    };

    private Kicker kicker;
    private BallSucker sucker;
    private Victor leftVic;
    private Victor rightVic;
    private Encoder lCoder, rCoder;
    private double initTime = 0;
    private final double TIME_DELAY = 1;

    public AutonomousDos(Victor lftVic, Victor rghtVic, Kicker k, BallSucker lclBall, Encoder leftCoder, Encoder rightCoder)
    {
        kicker = k;
        sucker = lclBall;
        leftVic = lftVic;
        rightVic = rghtVic;
        lCoder = leftCoder;
        rCoder = rightCoder;
    }

    public void handler() {
        kicker.KickMechanism();
        boolean done = false;

        switch (events[stateIndex]) {
            case DRIVE_FORWARD:
                if (lCoder.get() <= ENC_REV_FORWARD_1)
                {
                    Robot.leftJagValue = .5;
                }
                if(rCoder.get() <= ENC_REV_FORWARD_1)
                {
                    Robot.rightJagValue = .5;
                }
                else if(rCoder.get() > ENC_REV_FORWARD_1)
                    done = true;

                break;
            case DRIVE_FORWARD2:
                if (lCoder.get() <= ENC_REV_FORWARD_2)
                {
                    Robot.leftJagValue = .5;
                }
                if(rCoder.get() <= ENC_REV_FORWARD_2)
                {
                    Robot.rightJagValue = .5;
                }
                else if(rCoder.get() > ENC_REV_FORWARD_2)
                    done = true;

            case DRIVE_FORWARD3:
                if (lCoder.get() <= ENC_REV_FORWARD_3)
                {
                    Robot.leftJagValue = .5;
                }
                if(rCoder.get() <= ENC_REV_FORWARD_3)
                {
                    Robot.rightJagValue = .5;
                }
                else if(rCoder.get() > ENC_REV_FORWARD_3)
                    done = true;

            case STOP:
                Robot.leftJagValue = 0;
                Robot.rightJagValue = 0;
                break;
            case KICK:
                kicker.Kick();

            case PAUSE:
                if(kicker.KickReady())
                    done = true;
                break;
            case DONE:
            default:
                System.out.println("I am done stupid");
                break;
        }

        if (done) {
            System.out.println("Moving to next state");
            ++stateIndex;
        }
    }
}
