package com.nucldev.engine.utils;

import com.nucldev.engine.Constants;

public class CoordinateSystemsConvertion {
    // Класс реализует методы перехода из одних систем координат в другие
    // Из геоцентрических в экваториальные, из экваториальных в горизонтальные и т.д.

    public static double[] fromEclipticGeocentricToEquatorial(double lambda, double beta){
        double sinusSigma = Math.sin(Math.toRadians(beta))*Math.cos(Math.toRadians(Constants.ecliptiEquatorDecimalAngle))+Math.cos(Math.toRadians(beta))*Math.sin(Math.toRadians(Constants.ecliptiEquatorDecimalAngle))*Math.sin(Math.toRadians(lambda));
        double sigma = Math.toDegrees(Math.asin(sinusSigma));
        double y = Math.sin(Math.toRadians(lambda))*Math.cos(Math.toRadians(Constants.ecliptiEquatorDecimalAngle))-Math.tan(Math.toRadians(beta))*Math.sin(Math.toRadians(Constants.ecliptiEquatorDecimalAngle));
        double x = Math.cos(Math.toRadians(lambda));
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
        double alpha = arctgXY;
        double[] equatorialCoordinates = {alpha, sigma};
        return  equatorialCoordinates;
    }
      // Метод принимает в качестве аргументов экваториальные координаты объекта и географическую широту местности
    public static double[] fromEquatorialToHorizontal(double alpha, double sigma, double phi){
        double sinh = Math.sin(Math.toRadians(sigma))*Math.sin(Math.toRadians(phi))+Math.cos(Math.toRadians(sigma))*Math.cos(Math.toRadians(phi))*Math.cos(Math.toRadians(alpha));
        double h = Math.toDegrees(Math.asin(sinh));
        double x = Math.sin(Math.toRadians(sigma))-Math.sin(Math.toRadians(phi))*Math.sin(Math.toRadians(h));
        double y = Math.cos(Math.toRadians(phi))*Math.cos(Math.toRadians(h));
        double cosAzimuth = x/y;
        double azimuth = Math.toDegrees(Math.acos(cosAzimuth));
        if(Math.sin(Math.toRadians(alpha))>0)
            azimuth = 360 - azimuth;
        double[] horizontalCoordinates = {h, azimuth};
        return horizontalCoordinates;
    }

}
