package com.nucldev.engine;

import com.nucldev.engine.utils.DegreesConvertion;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Constants {
    public static int[] eclipticEquatorAngle = {23,26,0}; // угол мепжду земным экватором и эклиптикой равен 23 градуса 26 минут
    public static Calendar calendar;
    static {
        calendar = new GregorianCalendar(1980, 0,0);
    }
    public static final Date supportingDate =  calendar.getTime();
    public  static final double ecliptiEquatorDecimalAngle = DegreesConvertion.toDecimal(eclipticEquatorAngle);
}
