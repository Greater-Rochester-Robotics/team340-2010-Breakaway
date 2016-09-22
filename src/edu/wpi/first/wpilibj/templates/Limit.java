package edu.wpi.first.wpilibj.templates;
import edu.wpi.first.wpilibj.DigitalInput;
public class Limit
{
    DigitalInput top_limit, bottom_limit;
    public Limit(DigitalInput max)
    {
        top_limit = max;
    }
    public Limit (DigitalInput max, DigitalInput min)
    {
            top_limit = max;
            bottom_limit = min;
    }
    public boolean CheckMax() {
            if (top_limit.get()==true) {
                    return true;
            }
            return false;
    }
    public boolean CheckMin() {
            if (bottom_limit.get()==true) {
                    return true;
            }
            else {
                    return false;
            }
    }
}

