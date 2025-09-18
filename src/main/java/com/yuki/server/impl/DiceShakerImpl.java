package main.java.com.yuki.server.impl;

import main.java.com.yuki.server.DiceShaker;

public class DiceShakerImpl implements DiceShaker {
    @Override
    public int shake(int minValue, int maxValue) {
        return (int)(Math.random() * (maxValue - minValue + 1)) + minValue;
    }
}
