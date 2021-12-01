package com.company.classes;

public class Process {

    private int id;
    private String name;
    private int priority;
    private int time;
    private int memory;
    private int timeIn;
    private int burstTime;
    State state;

    public Process(){
        this.memory = Utils.getRandomInteger(10,Configuration.memoryVolume/2);
        this.priority = Utils.getRandomInteger(1,Configuration.maxPriority);
        this.timeIn = ClockGenerator.getTime();
        this.time = Utils.getRandomInteger(10,100);
        this.burstTime = Utils.getRandomInteger(10,30);
        this.state = State.Ready;
    }

    public Process(int id,int memory,int priority,int timeIn,int time,int burstTime,State state){
        this.id = id;
        this.name = "P"+this.id;
        this.memory = memory;
        this.priority = priority;
        this.timeIn = timeIn;
        this.time = time;
        this.burstTime = burstTime;
        this.state = state;
    }
    public Process(int id) {
        this.id = id;
        this.name = "P"+this.id;
        this.memory = Utils.getRandomInteger(10,Math.round(Configuration.memoryVolume/3));
        this.priority = Utils.getRandomInteger(1,Configuration.maxPriority);
        this.timeIn = ClockGenerator.getTime();
        this.time = Utils.getRandomInteger(10,100);
        this.burstTime = Utils.getRandomInteger(10,30);
        this.state = State.Ready;
    }

    public Process(String name,int id){
        this.id = id;
        this.name = "OS";
        this.memory = Configuration.osMemoryVolume;
        this.priority = 0;
        this.timeIn = ClockGenerator.getTime();
        this.time = 0;
        this.burstTime = 0;
        this.state = State.Ready;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getTime() {
        return time;
    }

    public int getMemory() {
        return memory;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return  id +
                " { name='" + name + '\'' +
                ", priority=" + priority +
                ", time=" + time +
                ", memory=" + memory +
                ", timeIn=" + timeIn +
                ", burstTime=" + burstTime +
                ", state=" + state +
                '}';
    }
}
