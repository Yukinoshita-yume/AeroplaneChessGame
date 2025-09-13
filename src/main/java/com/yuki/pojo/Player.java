package main.java.com.yuki.pojo;


public class Player {
    private int id;
    private final int home;
    private int locationTaxiway;//玩家在外围的位置
    private int locationRunway;//玩家达到结束点后，在结束边的位置
    private int runwayPosition;//玩家进入结束点的位置
    private boolean getRunway;//玩家是否到达结束点
    private boolean isEnd;//玩家是否到达终点

    public Player(int id, int home, int runwayPosition) {
        this.id = id;
        this.home = home;
        this.locationTaxiway = home;
        this.runwayPosition = runwayPosition;
        this.locationRunway = 0;
        this.getRunway = false;
        this.isEnd = false;
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
    public int getHome() {
        return home;
    }
    public int getLocationTaxiway() {
        return locationTaxiway;
    }
    public void setLocationTaxiway(int locationTaxiway) {
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
    public boolean isEnd() {
        return isEnd;
    }
    public void setEnd(boolean end) {
        isEnd = end;
    }
}

