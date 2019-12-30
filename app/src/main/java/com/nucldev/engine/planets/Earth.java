package com.nucldev.engine.planets;

import com.nucldev.engine.interfaces.IComputePlanetOrbitalCoordinates;

public class Earth extends Planet implements IComputePlanetOrbitalCoordinates {
    private final String planetName = "Earth";
    private final double sidericPeriod = 1d; // сидерический период пларнеты в земных годах
    private final double eraLongitude = 98.83354; // средняя долгота планеты в опорную эпоху
    private final double perihelionLongitude = 102.93734808; // долгота перигелия планеты
    private final double eccentricity = 0.0167086342; // эксцентриситет орбиты планеты
    private final double semimajorAxis = 1d; // большая полуось орбиты планеты
    private final double ascendingNodeLongitude = 174.87317577; // долгота восходящего узла орбиты планеты
    private double[] orbitalCoordinates; // орбитальные координаты планеты


    public double[] getOrbitalCoordinates() {
        return orbitalCoordinates;
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
}
