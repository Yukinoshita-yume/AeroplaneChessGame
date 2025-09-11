package main.java.com.yuki.pojo;


public class Player {
    private int id;
    private int locationTaxiway;//玩家在外围的位置
    private int locationRunway;//玩家达到结束点后，在结束边的位置
    private int runwayPosition;//玩家进入结束点的位置
    private boolean getRunway;//玩家是否到达结束点

    public Player(int id, int locationTaxiway, int runwayPosition) {
        this.id = id;
        this.locationTaxiway = locationTaxiway;
        this.runwayPosition = runwayPosition;
        this.locationRunway = 0;
        this.getRunway = false;
    }
    public boolean isGetRunway() {
        return getRunway;
    }
    public void setGetRunway(boolean getRunway) {
        this.getRunway = getRunway;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public int getLocationSide() {
        return locationTaxiway;
    }
    public void setLocationSide(int locationTaxiway) {
        this.locationTaxiway = locationTaxiway;
    }
    public int getLocationRunway() {
        return locationRunway;
    }
    public void setLocationRunway(int locationRunway) {
        this.locationRunway = locationRunway;
    }
    public int getRunwayPosition() {
        return runwayPosition;
    }
    public void setRunwayPosition(int runwayPosition) {
        this.runwayPosition = runwayPosition;
    }
}

