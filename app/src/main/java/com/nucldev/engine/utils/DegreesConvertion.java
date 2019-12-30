package com.nucldev.engine.utils;

public class DegreesConvertion {
    // Методы данного класса конвертируют величины, выраженные в градусах, из представления в виде
    // десятичой дроби в вид градусы, минуты, секунды и наоборот.
    public static int[] fromDecimal(double arg){
        int[] degMinSec = new int[3];
        degMinSec[0]=(int) arg;
        double fraction = arg-degMinSec[0];
        double fractionSeconds = fraction*3600;  // В одном градусе 3600 секунд
        int fractionSeconds1 = (int) fractionSeconds;
        degMinSec[1] = fractionSeconds1/60;
        degMinSec[2] = fractionSeconds1-degMinSec[1]*60;
        return degMinSec;
    }

    public static double toDecimal(int[] degMinSec){
        double seconds = (double)(degMinSec[1]*60+degMinSec[2])/3600;
        double degrWdecimal = degMinSec[0]+seconds;
        return degrWdecimal;
    }

    public static double toHours(double arg){
        return  arg/15;
    }
    public static double fromHoursToDegrees (double arg){
        return arg*15;
    }

}
