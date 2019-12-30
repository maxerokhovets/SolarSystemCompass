package com.nucldev.engine.utils;

import java.util.Calendar;

public class CurrentTimeObject {
    private double currentTime;

    public CurrentTimeObject(Calendar calendar) {
        compute(calendar);
    }

    public double getCurrentTime() {
        return currentTime;
    }

    private void compute(Calendar calendar){
       int[] timeH = new int[3];
       timeH[0]=calendar.get(Calendar.HOUR_OF_DAY);
       timeH[1]=calendar.get(Calendar.MINUTE);
       timeH[2]=calendar.get(Calendar.SECOND);
       this.currentTime = DegreesConvertion.toDecimal(timeH);
    }
}
