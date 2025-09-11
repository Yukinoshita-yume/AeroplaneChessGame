package main.java.com.yuki.pojo;
//骰子类
public class Dice {
    private final int maxValue;//骰子最大值
    private final int minValue;//骰子最小值
    public Dice(int maxValue, int minValue) {
        this.maxValue = maxValue;
        this.minValue = minValue;
    }
    public int roll(){
        return (int)(Math.random()*(maxValue-minValue+1))+minValue;
    }
}
