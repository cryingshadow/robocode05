package de.metro.robocode;

import robocode.*;
import robocode.Robot;

import java.awt.*;

public class NavidBot extends Robot {


    boolean init = false;
    double W,H;
    boolean targetFound = false;


    void doInit() {
        if(init) return;
        init = true;
        setColors(Color.red, Color.BLACK.white, Color.green);
        W = getBattleFieldWidth();
        H = getBattleFieldHeight();
    }

    @Override
    public void run() {

        doInit();

        double radius = 100.0;
        double angle = 90.0;

        while (true) {
            back(40);
            ahead(40);

            if(targetFound) {targetFound = false;} else { turnRadarLeft(45);}
            scan();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        targetFound = true;
        double targetBearing = e.getBearing();
        double absoluteBearing = this.getHeading() + targetBearing;
        //out.print("absolute bearing " + absoluteBearing);
        //out.println("target bearning " + targetBearing);
        double targetDist = e.getDistance();
        double targetHeading = e.getHeading();
        double targetVelocity = e.getVelocity();

        //out.println("gun heading: " + getGunHeading());
        myTurnGun(absoluteBearing - getGunHeading() );

        //out.print("distance to target " + targetDist);
        if( targetDist < 170 ) {
            fire(5);
        }
        else fire(3);
    }



    public void onHitByBullet(HitByBulletEvent e) {

    }


    void myTurnGun( double deg) {
        int intDeg = (int) Math.ceil(deg + 360);
        intDeg = intDeg % 360;
        //out.println( "turning degrees "+ intDeg );
        if(intDeg <180) {
            turnGunRight( intDeg );
        }else {
            turnGunLeft( 360 - intDeg );
        }
    }
}
