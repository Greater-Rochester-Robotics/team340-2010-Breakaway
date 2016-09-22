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
public class ServoTest {
    Servo srv;
    private boolean pressed;
    private int currstate;
    private int count,flipcount1,flipcount2;
    private boolean flip1;
    private boolean flip2;
    public ServoTest(Servo servo)
    {
        srv=servo;
        pressed=false;
        flip1=false;
        flip2=false;

        currstate=0;
        count=0;
        flipcount1=0;
        flipcount2=0;

    }
    public void servoTester()
    {
       
       switch(currstate)
       {
           case 0:
           {
               srv.set(.5);
               if(pressed==true)
               {
                   count++;
                   if(count>20)
                   {
                        currstate=1;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 1:
           {
               srv.set(.6);
               if(pressed==true)
               {
                  count++;
                   if(count>20)
                   {
                        currstate=2;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 2:
           {
               srv.set(.7);
               if(pressed==true)
               {
                count++;
                   if(count>20)
                   {
                        currstate=3;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 3:
           {
               srv.set(.8);
               if(pressed==true)
               {
                count++;
                   if(count>20)
                   {
                        currstate=4;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 4:
           {
               srv.set(.9);
               if(pressed==true)
               {
                  count++;
                   if(count>20)
                   {
                        currstate=5;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 5:
           {
               srv.set(1);
               if(pressed==true)
               {
                  count++;
                   if(count>20)
                   {
                        currstate=6;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 6:
           {
               srv.set(.5);
               if(pressed==true)
               {
                  count++;
                   if(count>20)
                   {
                        currstate=7;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 7:
           {
               srv.set(.4);
               if(pressed==true)
               {
                  count++;
                   if(count>20)
                   {
                        currstate=8;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 8:
           {
               srv.set(.3);
               if(pressed==true)
               {
                  count++;
                   if(count>20)
                   {
                        currstate=9;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
               
           }
           case 9:
           {
               srv.set(.2);
               if(pressed==true)
               {
                  count++;
                   if(count>20)
                   {
                        currstate=10;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 10:
           {
               srv.set(.1);
               if(pressed==true)
               {
                  count++;
                   if(count>20)
                   {
                        currstate=11;
                        pressed=false;
                        count=0;
                   }
               }
               else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 11:
           {
                srv.set(0);
                if(pressed==true)
               {
                  count++;
                   if(count>20)
                   {
                        currstate=12;
                        pressed=false;
                        count=0;
                   }
               }
                else if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                        }
               else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
               break;
           }
           case 12:
           {
               if(flip1==true)
                   {
                         flipcount1++;
                         if(flipcount1>20)
                         {
                              currstate=0;
                              flip1=false;
                              flipcount1=0;
                         }
                   }
                else if(flip2==true)
                   {
                        flipcount2++;
                        if(flipcount2>20)
                        {
                              currstate=6;
                              flip2=false;
                              flipcount2=0;
                        }
                   }
           
               break;
           }
       }

    }

    public void buttonPressed()
    {
        pressed=true;
    }
    public void reZero1()
    {
        flip1=true;
    }
    public void reZero2()
    {
        flip2=true;
    }

}
