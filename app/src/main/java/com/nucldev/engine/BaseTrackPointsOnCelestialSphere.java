package com.nucldev.engine;

import com.nucldev.engine.exceptions.DoNotSettingPlanetException;
import com.nucldev.engine.utils.NumberOfLocalTimeZone;
import com.nucldev.engine.utils.TimeConvertors;

public class BaseTrackPointsOnCelestialSphere {
    private double risingAzimuth;
    private double settingAzimuth;
    private double risingTime;
    private double settingTime;
    private double culminationHeight;

    public BaseTrackPointsOnCelestialSphere(double latitude, double longitude, double[] equatorialCoordinates) {
        try {
            this.calculateBaseTrackPoints(latitude,longitude,equatorialCoordinates);
        } catch (DoNotSettingPlanetException e) {
            System.out.println("This planet does not go beyond the horizon.");
        }
    }

    public double getRisingAzimuth() {
        return risingAzimuth;
    }

    public double getSettingAzimuth() {
        return settingAzimuth;
    }

    public double getRisingTime() {
        return risingTime;
    }

    public double getSettingTime() {
        return settingTime;
    }

    public double getCulminationHeight() {
        return culminationHeight;
    }

    private void calculateBaseTrackPoints(double latitude, double longitude, double[] equatorialCoordinates) throws DoNotSettingPlanetException {
        double cosAzimuthRising = Math.sin(Math.toRadians(equatorialCoordinates[1]))/Math.cos(Math.toRadians(latitude));
        if (cosAzimuthRising<(-1)|cosAzimuthRising>1)
            throw new DoNotSettingPlanetException("This planet does not go beyond the horizon.");
        double azimuthRising = Math.toDegrees(Math.acos(cosAzimuthRising));
        double azimuthSetting = 360 - azimuthRising;
        double h = Math.toDegrees(Math.acos(-1*Math.tan(Math.toRadians(latitude))*Math.tan(Math.toRadians(equatorialCoordinates[1])))/15);
        double risingLST = 24+equatorialCoordinates[0]/15-h;
        if (risingLST>24)
            while (risingLST>24)
                risingLST-=24;
        double settingLST = equatorialCoordinates[0]/15+h;
        if (settingLST>24)
            while (settingLST>24)
                settingLST-=24;


        this.settingAzimuth = azimuthSetting;
        this.risingAzimuth = azimuthRising;
        NumberOfLocalTimeZone noltz = new NumberOfLocalTimeZone();
        double risingGST = TimeConvertors.fromLSTtoGST(risingLST,longitude);
        double risingGMT = TimeConvertors.fromGSTtoGMT(risingGST);
        this.risingTime = TimeConvertors.fromGMTtoUTC(risingGMT, noltz.getNumberOfLocalTimeZone());
        double settingGST = TimeConvertors.fromLSTtoGST(settingLST,longitude);
        double settingGMT = TimeConvertors.fromGSTtoGMT(settingGST);
        this.settingTime = TimeConvertors.fromGMTtoUTC(settingGMT,noltz.getNumberOfLocalTimeZone());
        this.culminationHeight = 90-(latitude-equatorialCoordinates[1]);
    }
}
