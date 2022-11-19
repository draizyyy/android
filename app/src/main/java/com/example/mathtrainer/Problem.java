package com.example.mathtrainer;

import java.util.Random;

public class Problem {
    private int lastValue = 0;
    private boolean isProblemSolved = false;
    private int result;
    private final Random random = new Random();

    int getRandom(int min, int max) {
        int value;
        do {
            value = (int)(Math.random() * (max - min)) + min;
            System.out.println(value);
        } while(value == 0 || value == lastValue);
        lastValue = value;
        return value;
    }

    public int getResult() {
        return result;
    }
    public boolean isProblemSolved() {return isProblemSolved;}
    public void problemIsSolved() {
        isProblemSolved = true;
    }
    public String getProblem() {
        isProblemSolved = false;
        int a = getRandom(-50, 50);
        int b = getRandom(0, 50);
        String sign = getRandomSign();
        if (sign.equals("+")) result = a + b;
        else if (sign.equals("-")) result = a - b;
        else if (sign.equals("*")) {
            a = getRandom(-10, 10);
            b = getRandom(0, 10);
            result = a * b;
        }
        else {
            b = getRandom(0, 15);
            int c = getRandom(0, 10);
            a = b * c;
            result = c;
        }
        return a + " " + sign + " " + b + " = ?";
    }

    public int getNoiseResult() {
        return result + getRandom(-4, 4);
    }

    private String getRandomSign() {
        int value = getRandom(1, 5);
        String sign = "";
        switch (value) {
            case 1:
                sign =  "+";
                break;
            case 2:
                sign = "-";
                break;
            case 3:
                sign = "*";
                break;
            case 4:
                sign = "/";
                break;
        }
        return sign;
    }
}