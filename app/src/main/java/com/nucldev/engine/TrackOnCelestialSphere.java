package com.nucldev.engine;

import com.nucldev.engine.exceptions.OverTheHorizonException;
import com.nucldev.engine.utils.CurrentTimeObject;

public class TrackOnCelestialSphere {
    private double aXCoord; //координата x центра окружности
    private double bYCoord; // координата y центра окружности
    private double radius; // радиус окружности
    private double velocity; // скорость вращения небесной сферы в град/ч


    public TrackOnCelestialSphere(BaseTrackPointsOnCelestialSphere basePoints) {
        solveCircleEquation(basePoints);
        computeVelocity(basePoints);
    }

    public double computeCurrentAltitude(double currentAzimuth){
        return Math.sqrt(Math.pow(this.radius,2)-Math.pow(currentAzimuth-this.aXCoord,2))+this.bYCoord;
    }

    public double computeCurrentAzimuth(CurrentTimeObject cto, BaseTrackPointsOnCelestialSphere basePoints) throws OverTheHorizonException {
        if(basePoints.getSettingTime()<basePoints.getRisingTime())
            if(cto.getCurrentTime()>basePoints.getSettingTime()&cto.getCurrentTime()<basePoints.getRisingTime())
                throw new OverTheHorizonException("PlanetOverTheHorizonException");
        if(basePoints.getSettingTime()>basePoints.getRisingTime())
            if(!(cto.getCurrentTime()<basePoints.getSettingTime()&cto.getCurrentTime()>basePoints.getRisingTime()))
                throw new OverTheHorizonException("PlanetOverTheHorizonException");

        double deltaTime;
        if (basePoints.getRisingTime()<basePoints.getSettingTime())
            deltaTime = cto.getCurrentTime()-basePoints.getRisingTime();
        else
            if(cto.getCurrentTime()<basePoints.getSettingTime())
                deltaTime =24+cto.getCurrentTime()-basePoints.getRisingTime();
            else
                deltaTime = cto.getCurrentTime()-basePoints.getRisingTime();
        double currentAzimuth = basePoints.getRisingAzimuth()+deltaTime*this.velocity;
        if (currentAzimuth>360)
            while (currentAzimuth>360)
                currentAzimuth-=360;
            return currentAzimuth;
    }

    private void  computeVelocity(BaseTrackPointsOnCelestialSphere basePoints){
        double deltaTime=0, deltaAzimuth=0;
        if (basePoints.getRisingAzimuth()<basePoints.getSettingAzimuth())
            deltaAzimuth = basePoints.getSettingAzimuth()-basePoints.getRisingAzimuth();
        else
            deltaAzimuth = basePoints.getSettingAzimuth()+360-basePoints.getRisingAzimuth();
        if(basePoints.getRisingTime()<basePoints.getSettingTime())
            deltaTime = basePoints.getSettingTime()-basePoints.getRisingTime();
        else
            deltaTime = basePoints.getSettingTime()+24-basePoints.getRisingTime();
        this.velocity = deltaAzimuth/deltaTime;
    }

    private double computeCulminationAzimuth(BaseTrackPointsOnCelestialSphere basePoints){
        double deltaAzimuth=0, culminationAzimuth=0;
        if (basePoints.getRisingAzimuth()<basePoints.getSettingAzimuth()){
        deltaAzimuth = (basePoints.getSettingAzimuth()-basePoints.getRisingAzimuth())/2;
        culminationAzimuth = basePoints.getRisingAzimuth()+deltaAzimuth;
        }
        else {
            deltaAzimuth = (basePoints.getSettingAzimuth()+360-basePoints.getRisingAzimuth())/2;
            culminationAzimuth = basePoints.getRisingAzimuth()+deltaAzimuth;
            if (culminationAzimuth>360)
                while (culminationAzimuth>360)
                    culminationAzimuth-=360;
        }
        return culminationAzimuth;
    }

    private void solveCircleEquation(BaseTrackPointsOnCelestialSphere basePoints){
        // P1 (risingAz, 0)
        // P2 (culminAz, culminHeight)
        // P3 (settingAz, 0)
        double[] p1 = {basePoints.getRisingAzimuth(), 0};
        double[] p2 = {computeCulminationAzimuth(basePoints), basePoints.getCulminationHeight()};
        double[] p3 = {basePoints.getSettingAzimuth(), 0};
        if(basePoints.getRisingAzimuth()>basePoints.getSettingAzimuth())
            p3[0]=basePoints.getSettingAzimuth()+360;

        double b1 = (Math.pow(p3[0],2)+Math.pow(p3[1],2)-Math.pow(p2[0],2)-Math.pow(p2[1],2))*(p2[0]-p1[0]);
        double b2 = (Math.pow(p2[0],2)+Math.pow(p2[1],2)-Math.pow(p1[0],2)-Math.pow(p1[1],2))*(p3[0]-p2[0]);
        double b3 = 2*(p3[1]-p2[1])*(p2[0]-p1[0])-2*(p2[1]-p1[1])*(p3[0]-p2[0]);
        double b = (b1-b2)/b3;
        double a1 = (Math.pow(p3[0],2)+Math.pow(p3[1],2)-Math.pow(p2[0],2)-Math.pow(p2[1],2))-2*b*(p3[1]-p2[1]);
        double a = a1/2/(p3[0]-p2[0]);
        double r = Math.sqrt(Math.pow(p1[0]-a,2)+Math.pow(p1[1]-b,2));
        this.aXCoord = a;
        this.bYCoord = b;
        this.radius = r;
    }
}
