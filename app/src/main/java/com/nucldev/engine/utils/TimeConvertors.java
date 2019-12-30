package com.nucldev.engine.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TimeConvertors {
    public static double fromLSTtoGST (double localSiderialTime, double longitude){
        double longitudeH = Math.abs(longitude/15);
        double greenvichSiderealTime = 0;
        if(longitude<0)
            greenvichSiderealTime = localSiderialTime + longitudeH;
        else
            greenvichSiderealTime = localSiderialTime - longitudeH;
        if(greenvichSiderealTime>24)
            while (greenvichSiderealTime>24)
            greenvichSiderealTime-=24;
        if(greenvichSiderealTime<0)
            while (greenvichSiderealTime<0)
            greenvichSiderealTime+=24;
        return greenvichSiderealTime;
    }
    public static double fromGMTtoUTC(double gmt, double timeZone){
        double timeUTC = gmt+timeZone;
        if (timeUTC>24)
            while (timeUTC>24)
                timeUTC-=24;
        if (timeUTC<0)
            while (timeUTC<0)
                timeUTC+=24;
        return timeUTC;
    }

    public static double fromGSTtoGMT(double greenvichSiderealTime){
        Calendar calendar = new GregorianCalendar();
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("D");
        int d = Integer.parseInt(sdf.format(date));
        double t0 = d*0.0657098;
        t0-=computeB();
        if (t0<0)
            t0+=24;
        double greenvichMeridianTime = greenvichSiderealTime-t0;
        if (greenvichMeridianTime<0)
            while (greenvichMeridianTime<0)
            greenvichMeridianTime+=24;
        greenvichMeridianTime*=0.997270;
        return greenvichMeridianTime;
    }

    private static double computeB (){
        Calendar calend = new GregorianCalendar();
        int y = calend.get(Calendar.YEAR);
        int m = calend.get(Calendar.MONTH);
        int d = calend.get(Calendar.DATE);
        if (m==0 & d==0)
            y+=1;
        Calendar calendar = new GregorianCalendar(y,0,0);
        double s = TimeConvertors.computeJulianDate(calendar)-2415020;
        double t = s/36525;
        double r = 6.6460656+2400.051262*t+0.00002581*t*t;
        int year = calendar.get(Calendar.YEAR)+1;
        double u = r-24*(year-1900);
        double b = 24-u;
        return b;
    }

    private static double computeJulianDate (Calendar calendar){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        if (month<=2){
            year-=1;
            month+=12;
        }
        int a = (int) year/100;
        int b = 2-a+(int)a/4;
        int c = (int) (365.25*year);
        int d = (int) (30.6001*(month+1));
        return  b+c+d+day+ 1_720_994.5;
    }
}
