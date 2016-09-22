package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
public class AutonActions{
    private static int state;
    private int count;
    private boolean digTwo;
    Kicker kck;
    BallSucker sck;
    
    Encoder cdr1,cdr2;
    Victor lft,rht,kcr,launch;
    
    public AutonActions(Victor left,Victor right,Victor kick,Victor release,Kicker kicker,BallSucker sucker,Encoder coder1,Encoder coder2){
            kck=kicker;
            sck=sucker;
            kcr=kick;
            launch = release;
            lft=left;
            rht=right;
            cdr1=coder1;
            cdr2=coder2;
            state=0;
            count=0;
            digTwo=false;


        }

    public void action()
    {
       
       switch(state)
       {
           case 0:
           {
               
               sck.turnOn();
               count++;
               if(count<70)
               {
                   lft.set(-.2);
                   rht.set(.2);

               }
               else
               {
                   lft.set(0);
                   rht.set(0);
                   if(kck.KickReady()==true)
                   {
                       state=1;
                       count=0;
                   }
               }
               break;
           }
           case 1:
           {
              /*count ++;
              if(count<30)
              {
                  lft.set(.2);
                  rht.set(-.2);
              }
              else
              {
                  lft.set(0);
                  rht.set(0);*/
                  state=2;
                  count=0;
              //}
              break;
           }
           case 2:
           {
               count++;
               sck.turnOff();
               if(count>3)
               {
                   if(count<22)
                   {
                      launch.set(-1);
                   }
                   else if(count>25)
                   {
                       launch.set(0);
                   }
                   
                   if(count>26)
                   {
                       state=3;
                       count=0;
                   }
               }
               break;

           }
           case 3:
           {
               count++;
               if(count<22)
               {
                   launch.set(1);

               }
               else
               {
                   launch.set(0);
               }
               break;
           }

       }

        
       

    }
    public void setTwo()
    {
        digTwo = true;
    }
}