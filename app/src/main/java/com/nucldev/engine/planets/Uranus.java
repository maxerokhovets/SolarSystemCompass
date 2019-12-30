package com.nucldev.engine.planets;

import com.nucldev.engine.interfaces.IComputePlanetGeocentricCoordinates;
import com.nucldev.engine.interfaces.IComputePlanetOrbitalCoordinates;

public class Uranus extends Planet implements IComputePlanetOrbitalCoordinates, IComputePlanetGeocentricCoordinates {
    private final String planetName = "Uranus";
    private final double sidericPeriod = 84.01247; // сидерический период пларнеты в земных годах
    private final double eraLongitude = 228.0708551; // средняя долгота планеты в опорную эпоху
    private final double perihelionLongitude =  173.00529106; // долгота перигелия планеты
    private final double eccentricity = 0.0463812221; // эксцентриситет орбиты планеты
    private final double semimajorAxis = 19.2184460618; // большая полуось орбиты планеты
    private final double inclination = 0.77319689; // наклонение отбиты к эклиптике
    private final double ascendingNodeLongitude = 74.00595701; // долгота восходящего узла орбиты планеты
    private double[] orbitalCoordinates; // орбитальные координаты планеты
    private double[] geocentricCoordinates; // геоцентрические координаты планеты
    private final boolean status = false; //  true у внутренних планет, false у внешних

    public double[] getOrbitalCoordinates() {
        return orbitalCoordinates;
    }

    public double[] getGeocentricCoordinates() {
        return geocentricCoordinates;
    }

    public String getPlanetName() {
        return planetName;
    }

    @Override
    public void computeOrbitalCoordinates(double days) {
        double n = (360/365.2564)*(days/this.sidericPeriod);
        if (n>360)
            while (n>360)
                n-=360;
        double m = n+this.eraLongitude-this.perihelionLongitude;
        double l = n+(360/Math.PI)*this.eccentricity*Math.sin(Math.toRadians(m))+this.eraLongitude;
        if (l>360)
            while (l>360)
                l-=360;
        double v = l-this.perihelionLongitude;
        double r = (this.semimajorAxis*(1-Math.pow(this.eccentricity,2)))/(1+this.eccentricity*Math.cos(Math.toRadians(v)));
        double[] orbitalCoordinates = {l,r};
        this.orbitalCoordinates = orbitalCoordinates;
    }
    @Override
    public void computeGeocentricCoordinates(double[] earthOrbitalCoordinates){
        double heliocentricPlanetLatitude = Math.toDegrees(Math.asin(Math.sin(Math.toRadians(this.orbitalCoordinates[0]-this.ascendingNodeLongitude))*Math.sin(Math.toRadians(this.inclination))));
        double y = Math.sin(Math.toRadians(this.orbitalCoordinates[0]-this.ascendingNodeLongitude))*Math.cos(Math.toRadians(this.inclination));
        double x = Math.cos(Math.toRadians(this.orbitalCoordinates[0]-this.ascendingNodeLongitude));
        double arctgXY = Math.toDegrees(Math.atan(y/x));
        if (y>0 & x>0)
            while (!(arctgXY<90 & arctgXY>0)){
                arctgXY+=180;
                if(arctgXY>360)
                    arctgXY-=360;
            }
        if (y>0 & x<0)
            while (!(arctgXY<180 & arctgXY>90)){
                arctgXY+=180;
                if(arctgXY>360)
                    arctgXY-=360;
            }
        if (y<0 & x<0)
            while (!(arctgXY<270 & arctgXY>180)){
                arctgXY+=180;
                if(arctgXY>360)
                    arctgXY-=360;
            }
        if (y<0 & x>0)
            while (!(arctgXY<360 & arctgXY>270)){
                arctgXY+=180;
                if(arctgXY>360)
                    arctgXY-=360;
            }

        double ll = arctgXY+this.ascendingNodeLongitude;
        double rr = this.orbitalCoordinates[1]*Math.cos(Math.toRadians(heliocentricPlanetLatitude));
        double lambda=0;
        if (this.status){
            double deltaL = earthOrbitalCoordinates[0]-ll;
            double sinDeltaL = Math.sin(Math.toRadians(deltaL));
            double cosDeltaL = Math.cos(Math.toRadians(deltaL));
            double a  = Math.toDegrees(Math.atan((rr*sinDeltaL)/(earthOrbitalCoordinates[1]-rr*cosDeltaL)));
            lambda = 180+a+earthOrbitalCoordinates[0];
            if(lambda<0)
                lambda+=360;
            if (lambda>360)
                lambda-=360;
        }else {
            double deltaL = ll-earthOrbitalCoordinates[0];
            double sinDeltaL = Math.sin(Math.toRadians(deltaL));
            double cosDeltaL = Math.cos(Math.toRadians(deltaL));
            lambda = Math.toDegrees(Math.atan(earthOrbitalCoordinates[1]*sinDeltaL/(rr-earthOrbitalCoordinates[1]*cosDeltaL)))+ll;
            if(lambda<0)
                lambda+=360;
            if (lambda>360)
                lambda-=360;
        }

        double beta = Math.toDegrees(Math.atan((rr*Math.tan(Math.toRadians(heliocentricPlanetLatitude)*Math.sin(Math.toRadians(lambda-ll))))/(earthOrbitalCoordinates[1]*Math.sin(Math.toRadians(ll-earthOrbitalCoordinates[0])))));
        double[] geocentricCoordinates = {lambda,beta};
        this.geocentricCoordinates = geocentricCoordinates;
    }
}
