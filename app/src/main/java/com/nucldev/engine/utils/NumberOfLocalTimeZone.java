package com.nucldev.engine.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class NumberOfLocalTimeZone {
    private  double numberOfLocalTimeZone;

    public NumberOfLocalTimeZone() {
        this.numberOfLocalTimeZone = computeNumberOfLocalTimeZone();
    }

    public double getNumberOfLocalTimeZone() {
        return numberOfLocalTimeZone;
    }

    private double computeNumberOfLocalTimeZone(){
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        long dateL = date.getTime();
        TimeZone localTimeZone = calendar.getTimeZone();
        double numberOfLocalTimeZone = localTimeZone.getOffset(dateL)/1000/3600;
        return numberOfLocalTimeZone;
    }
}
