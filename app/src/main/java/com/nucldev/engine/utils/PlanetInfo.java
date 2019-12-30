package com.nucldev.engine.utils;

import com.nucldev.engine.BaseTrackPointsOnCelestialSphere;
import com.nucldev.engine.exceptions.DoNotRisingPlanetException;
import com.nucldev.engine.planets.Planet;

import java.util.List;

public class PlanetInfo {
    private String planetName;
    private double alpha; // прямое восхождение
    private double sigma; // склонение
    private double risingAzimuth;
    private double settingAzimuth;
    private double risingTime;
    private double settingTime;
    private double culminationHeigh;
    private Double currentAzimuth;
    private Double currentAltitude;

    public double getCulminationHeigh() {
        return culminationHeigh;
    }

    public Double getCurrentAzimuth() {
        return currentAzimuth;
    }

    public Double getCurrentAltitude() {
        return currentAltitude;
    }

    public String getPlanetName() {
        return planetName;
    }

    public PlanetInfo(List infoList) {
        Planet p = (Planet) infoList.get(0);
        this.planetName = p.getPlanetName();
        double[] eq = (double[]) infoList.get(1);
        this.alpha = eq[0];
        this.sigma = eq[1];
        BaseTrackPointsOnCelestialSphere btpocs = (BaseTrackPointsOnCelestialSphere) infoList.get(2);
        this.risingAzimuth = btpocs.getRisingAzimuth();
        this.settingAzimuth = btpocs.getSettingAzimuth();
        this.risingTime = btpocs.getRisingTime();
        this.settingTime = btpocs.getSettingTime();
        this.culminationHeigh = btpocs.getCulminationHeight();
        this.currentAzimuth = (Double) infoList.get(3);
        this.currentAltitude = (Double) infoList.get(4);

    }

    public void print(){
        System.out.println(this.planetName);
        System.out.println("Equatorial coordinates");
        int[] alpha = DegreesConvertion.fromDecimal(DegreesConvertion.toHours(this.alpha));
        System.out.println("Right ascension (α): "+alpha[0]+"h"+alpha[1]+"m"+alpha[2]+"s");
        int[] sigma = DegreesConvertion.fromDecimal(this.sigma);
        for (int k=1; k<sigma.length;k++)
            if (sigma[k]<0)
                sigma[k]*=(-1);
        System.out.println("Declination (δ): "+ sigma[0]+"°"+sigma[1]+"'"+sigma[2]+"\"");
        int[] rTime = DegreesConvertion.fromDecimal(this.risingTime);
        int[] rAz = DegreesConvertion.fromDecimal(this.risingAzimuth);
        System.out.println("Rising time: "+ rTime[0]+"h"+rTime[1]+"m  Rising azimuth: "+rAz[0]+"°"+rAz[1]+"'"+rAz[2]+"\"");
        int[] sTime = DegreesConvertion.fromDecimal(this.settingTime);
        int[] sAz = DegreesConvertion.fromDecimal(this.settingAzimuth);
        System.out.println("Setting time: "+ sTime[0]+"h"+sTime[1]+"m  Setting azimuth: "+sAz[0]+"°"+sAz[1]+"'"+sAz[2]+"\"");
        int[] culmH = DegreesConvertion.fromDecimal(this.culminationHeigh);
        System.out.println("Culmination altitude (A): "+ culmH[0]+"°"+culmH[1]+"'"+culmH[2]+"\"");
        if(this.currentAzimuth!=null){
        System.out.println("Horizontal coordinates");
        int[] currAz = DegreesConvertion.fromDecimal(this.currentAzimuth);
        System.out.println("Current azimuth: "+ currAz[0]+"°"+currAz[1]+"'"+currAz[2]+"\"");
        int[] currAlt = DegreesConvertion.fromDecimal(this.currentAltitude);
        System.out.println("Current altitude: "+ currAlt[0]+"°"+currAlt[1]+"'"+currAlt[2]+"\"");
        }
        else
            System.out.println("Planet is over the horizon.");

    }

    @Override
    public String toString() {
        String retStr;
        try {
            if (this.culminationHeigh <=0)
                throw new DoNotRisingPlanetException("Planet does not rise above the horizon");
            int[] alpha = DegreesConvertion.fromDecimal(DegreesConvertion.toHours(this.alpha));
            int[] sigma = DegreesConvertion.fromDecimal(this.sigma);
            for (int k = 1; k < sigma.length; k++)
                if (sigma[k] < 0)
                    sigma[k] *= (-1);
            int[] rTime = DegreesConvertion.fromDecimal(this.risingTime);
            int[] rAz = DegreesConvertion.fromDecimal(this.risingAzimuth);
            int[] sTime = DegreesConvertion.fromDecimal(this.settingTime);
            int[] sAz = DegreesConvertion.fromDecimal(this.settingAzimuth);
            int[] culmH = DegreesConvertion.fromDecimal(this.culminationHeigh);
            int[] currAz = new int[3];
            int[] currAlt = new int[3];
            if (this.currentAzimuth != null) {
                currAz = DegreesConvertion.fromDecimal(this.currentAzimuth);
                currAlt = DegreesConvertion.fromDecimal(this.currentAltitude);
            }

            if (this.currentAzimuth != null) {
                retStr = this.planetName + "\n"
                        + "Equatorial coordinates" + "\n"
                        + "Right ascension (α): " + alpha[0] + "h" + alpha[1] + "m" + alpha[2] + "s" + "\n"
                        + "Declination (δ): " + sigma[0] + "°" + sigma[1] + "'" + sigma[2] + "\"" + "\n"
                        + "Rise time: " + rTime[0] + "h" + rTime[1] + "m" + "\n"
                        + "Rise azimuth: " + rAz[0] + "°" + rAz[1] + "'" + rAz[2] + "\"" + "\n"
                        + "Set time: " + sTime[0] + "h" + sTime[1] + "m" + "\n"
                        + "Set azimuth: " + sAz[0] + "°" + sAz[1] + "'" + sAz[2] + "\"" + "\n"
                        + "Culmination altitude (A): " + culmH[0] + "°" + culmH[1] + "'" + culmH[2] + "\"" + "\n"
                        + "Horizontal coordinates" + "\n"
                        + "Current azimuth: " + currAz[0] + "°" + currAz[1] + "'" + currAz[2] + "\"" + "\n"
                        + "Current altitude: " + currAlt[0] + "°" + currAlt[1] + "'" + currAlt[2] + "\"";
            } else {
                retStr = this.planetName + "\n"
                        + "Equatorial coordinates" + "\n"
                        + "Right ascension (α): " + alpha[0] + "h" + alpha[1] + "m" + alpha[2] + "s" + "\n"
                        + "Declination (δ): " + sigma[0] + "°" + sigma[1] + "'" + sigma[2] + "\"" + "\n"
                        + "Rise time: " + rTime[0] + "h" + rTime[1] + "m" + "\n"
                        + "Rise azimuth: " + rAz[0] + "°" + rAz[1] + "'" + rAz[2] + "\"" + "\n"
                        + "Set time: " + sTime[0] + "h" + sTime[1] + "m" + "\n"
                        + "Set azimuth: " + sAz[0] + "°" + sAz[1] + "'" + sAz[2] + "\"" + "\n"
                        + "Culmination altitude (A): " + culmH[0] + "°" + culmH[1] + "'" + culmH[2] + "\"" + "\n"
                        + "Planet is over the horizon";
            }
        }catch (DoNotRisingPlanetException e){
            retStr = "Planet does not rise above the horizon";
        }
       return retStr;
    }
}
