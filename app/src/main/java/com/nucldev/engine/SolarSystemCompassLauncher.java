package com.nucldev.engine;

import com.nucldev.engine.exceptions.OverTheHorizonException;
import com.nucldev.engine.planets.*;
import com.nucldev.engine.utils.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class SolarSystemCompassLauncher {
    public static PlanetInfo planetInfo;
    public static int[] latitude;
    public static int[] longitude;

    public static void main(String[] args) {
        double latitudeDec = DegreesConvertion.toDecimal(latitude);
        double longitudeDec = DegreesConvertion.toDecimal(longitude);
        DaysFromEra dfsd = new DaysFromEra();
        Calendar calendar = new GregorianCalendar();
        CurrentTimeObject cto = new CurrentTimeObject(calendar);
        List infoList = new ArrayList();

        switch (args[0]){
            case ("Mercury"): {
                Earth earth = new Earth();
                Mercury mercury = new Mercury();
                earth.computeOrbitalCoordinates(dfsd.getDaysfrom());
                mercury.computeOrbitalCoordinates(dfsd.getDaysfrom());
                mercury.computeGeocentricCoordinates(earth.getOrbitalCoordinates());
                double[] equatorialCoordinates = CoordinateSystemsConvertion.fromEclipticGeocentricToEquatorial(mercury.getGeocentricCoordinates()[0], mercury.getGeocentricCoordinates()[1]);
                BaseTrackPointsOnCelestialSphere btpocs = new BaseTrackPointsOnCelestialSphere(latitudeDec, longitudeDec, equatorialCoordinates);
                TrackOnCelestialSphere tocs = new TrackOnCelestialSphere(btpocs);
                Double azimuth = null;
                try {
                    azimuth = tocs.computeCurrentAzimuth(cto, btpocs);
                } catch (OverTheHorizonException e) {}
                Double altitude = null;
                if(azimuth!=null)
                altitude = tocs.computeCurrentAltitude(azimuth);
                infoList.add(mercury);
                infoList.add(equatorialCoordinates);
                infoList.add(btpocs);
                infoList.add(azimuth);
                infoList.add(altitude);
            }
                break;
            case ("Venus"):{
                Earth earth = new Earth();
                Venus venus = new Venus();
                earth.computeOrbitalCoordinates(dfsd.getDaysfrom());
                venus.computeOrbitalCoordinates(dfsd.getDaysfrom());
                venus.computeGeocentricCoordinates(earth.getOrbitalCoordinates());
                double[] equatorialCoordinates = CoordinateSystemsConvertion.fromEclipticGeocentricToEquatorial(venus.getGeocentricCoordinates()[0],venus.getGeocentricCoordinates()[1]);
                BaseTrackPointsOnCelestialSphere btpocs = new BaseTrackPointsOnCelestialSphere(latitudeDec,longitudeDec,equatorialCoordinates);
                TrackOnCelestialSphere tocs = new TrackOnCelestialSphere(btpocs);
                Double azimuth = null;
                try {
                    azimuth = tocs.computeCurrentAzimuth(cto,btpocs);
                } catch (OverTheHorizonException e) {}
                Double altitude = null;
                if(azimuth!=null)
                    altitude = tocs.computeCurrentAltitude(azimuth);
                infoList.add(venus);
                infoList.add(equatorialCoordinates);
                infoList.add(btpocs);
                infoList.add(azimuth);
                infoList.add(altitude);
            }
                break;

            case ("Mars"):{
                Earth earth = new Earth();
                Mars mars = new Mars();
                earth.computeOrbitalCoordinates(dfsd.getDaysfrom());
                mars.computeOrbitalCoordinates(dfsd.getDaysfrom());
                mars.computeGeocentricCoordinates(earth.getOrbitalCoordinates());
                double[] equatorialCoordinates = CoordinateSystemsConvertion.fromEclipticGeocentricToEquatorial(mars.getGeocentricCoordinates()[0],mars.getGeocentricCoordinates()[1]);
                BaseTrackPointsOnCelestialSphere btpocs = new BaseTrackPointsOnCelestialSphere(latitudeDec,longitudeDec,equatorialCoordinates);
                TrackOnCelestialSphere tocs = new TrackOnCelestialSphere(btpocs);
                Double azimuth = null;
                try {
                    azimuth = tocs.computeCurrentAzimuth(cto,btpocs);
                } catch (OverTheHorizonException e) {}
                Double altitude = null;
                if(azimuth!=null)
                    altitude = tocs.computeCurrentAltitude(azimuth);
                infoList.add(mars);
                infoList.add(equatorialCoordinates);
                infoList.add(btpocs);
                infoList.add(azimuth);
                infoList.add(altitude);
            }
            break;

            case ("Jupiter"):{
                Earth earth = new Earth();
                Jupiter jupiter = new Jupiter();
                earth.computeOrbitalCoordinates(dfsd.getDaysfrom());
                jupiter.computeOrbitalCoordinates(dfsd.getDaysfrom());
                jupiter.computeGeocentricCoordinates(earth.getOrbitalCoordinates());
                double[] equatorialCoordinates = CoordinateSystemsConvertion.fromEclipticGeocentricToEquatorial(jupiter.getGeocentricCoordinates()[0], jupiter.getGeocentricCoordinates()[1]);
                BaseTrackPointsOnCelestialSphere btpocs = new BaseTrackPointsOnCelestialSphere(latitudeDec,longitudeDec,equatorialCoordinates);
                TrackOnCelestialSphere tocs = new TrackOnCelestialSphere(btpocs);
                Double azimuth = null;
                try {
                    azimuth = tocs.computeCurrentAzimuth(cto,btpocs);
                } catch (OverTheHorizonException e) {}
                Double altitude = null;
                if(azimuth!=null)
                    altitude = tocs.computeCurrentAltitude(azimuth);
                infoList.add(jupiter);
                infoList.add(equatorialCoordinates);
                infoList.add(btpocs);
                infoList.add(azimuth);
                infoList.add(altitude);
            }
            break;

            case ("Saturn"):{
                Earth earth = new Earth();
                Saturn saturn = new Saturn();
                earth.computeOrbitalCoordinates(dfsd.getDaysfrom());
                saturn.computeOrbitalCoordinates(dfsd.getDaysfrom());
                saturn.computeGeocentricCoordinates(earth.getOrbitalCoordinates());
                double[] equatorialCoordinates = CoordinateSystemsConvertion.fromEclipticGeocentricToEquatorial(saturn.getGeocentricCoordinates()[0], saturn.getGeocentricCoordinates()[1]);
                BaseTrackPointsOnCelestialSphere btpocs = new BaseTrackPointsOnCelestialSphere(latitudeDec,longitudeDec,equatorialCoordinates);
                TrackOnCelestialSphere tocs = new TrackOnCelestialSphere(btpocs);
                Double azimuth = null;
                try {
                    azimuth = tocs.computeCurrentAzimuth(cto,btpocs);
                } catch (OverTheHorizonException e) {}
                Double altitude = null;
                if(azimuth!=null)
                    altitude = tocs.computeCurrentAltitude(azimuth);
                infoList.add(saturn);
                infoList.add(equatorialCoordinates);
                infoList.add(btpocs);
                infoList.add(azimuth);
                infoList.add(altitude);
            }
            break;

            case ("Uranus"):{
                Earth earth = new Earth();
                Uranus uranus = new Uranus();
                earth.computeOrbitalCoordinates(dfsd.getDaysfrom());
                uranus.computeOrbitalCoordinates(dfsd.getDaysfrom());
                uranus.computeGeocentricCoordinates(earth.getOrbitalCoordinates());
                double[] equatorialCoordinates = CoordinateSystemsConvertion.fromEclipticGeocentricToEquatorial(uranus.getGeocentricCoordinates()[0], uranus.getGeocentricCoordinates()[1]);
                BaseTrackPointsOnCelestialSphere btpocs = new BaseTrackPointsOnCelestialSphere(latitudeDec,longitudeDec,equatorialCoordinates);
                TrackOnCelestialSphere tocs = new TrackOnCelestialSphere(btpocs);
                Double azimuth = null;
                try {
                    azimuth = tocs.computeCurrentAzimuth(cto,btpocs);
                } catch (OverTheHorizonException e) {}
                Double altitude = null;
                if(azimuth!=null)
                    altitude = tocs.computeCurrentAltitude(azimuth);
                infoList.add(uranus);
                infoList.add(equatorialCoordinates);
                infoList.add(btpocs);
                infoList.add(azimuth);
                infoList.add(altitude);
            }
            break;

            case ("Neptune"):{
                Earth earth = new Earth();
                Neptune neptune = new Neptune();
                earth.computeOrbitalCoordinates(dfsd.getDaysfrom());
                neptune.computeOrbitalCoordinates(dfsd.getDaysfrom());
                neptune.computeGeocentricCoordinates(earth.getOrbitalCoordinates());
                double[] equatorialCoordinates = CoordinateSystemsConvertion.fromEclipticGeocentricToEquatorial(neptune.getGeocentricCoordinates()[0], neptune.getGeocentricCoordinates()[1]);
                BaseTrackPointsOnCelestialSphere btpocs = new BaseTrackPointsOnCelestialSphere(latitudeDec,longitudeDec,equatorialCoordinates);
                TrackOnCelestialSphere tocs = new TrackOnCelestialSphere(btpocs);
                Double azimuth = null;
                try {
                    azimuth = tocs.computeCurrentAzimuth(cto,btpocs);
                } catch (OverTheHorizonException e) {}
                Double altitude = null;
                if(azimuth!=null)
                    altitude = tocs.computeCurrentAltitude(azimuth);
                infoList.add(neptune);
                infoList.add(equatorialCoordinates);
                infoList.add(btpocs);
                infoList.add(azimuth);
                infoList.add(altitude);
            }
            break;
        }


        PlanetInfo pI = new PlanetInfo(infoList);
        planetInfo = pI;








    }
}
