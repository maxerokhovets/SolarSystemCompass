package com.nucldev.engine.utils;

import com.nucldev.engine.Constants;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DaysFromEra {
    private long daysfrom;
    private Calendar today;

    public DaysFromEra() {
        this.today = new GregorianCalendar();
        this.calculateDaysFrom();
    }
    public DaysFromEra(int year, int month, int day) {
        this.today = new GregorianCalendar(year, month, day);
        this.calculateDaysFrom();
    }

    public long getDaysfrom() {
        return daysfrom;
    }

    private void calculateDaysFrom(){
        Date todayDate = this.today.getTime();
        long todayMillis = todayDate.getTime();
        long supportingDateMillis  = Constants.supportingDate.getTime();
        long diffMillis = todayMillis-supportingDateMillis;
        this.daysfrom = diffMillis/1000/60/60/24;
    }
}
