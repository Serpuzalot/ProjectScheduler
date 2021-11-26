package com.company.classes;

public class ClockGenerator {
    private static int time;

    public static void incTime(int tact){
        time += tact;
    }

    public static void incTime(){
        time++;
    }

    public static int getTime() {
        return time;
    }
}
