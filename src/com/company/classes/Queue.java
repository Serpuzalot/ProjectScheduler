package com.company.classes;

import java.util.ArrayList;

public class Queue {
    private int lastProcessId;
    ArrayList<Process> queue;

    public Queue() {
        this.queue = new ArrayList<>();
        this.lastProcessId = 2 ;

    }

    public int getLastProcessId() {
        return lastProcessId;
    }

    public void add(Process process){
        Process fullProcess = new Process(getLastProcessId(),process.getMemory(),process.getPriority(),process.getTimeIn(),process.getTime(),process.getBurstTime(),process.getState());
        queue.add(fullProcess);
        lastProcessId++;
    }

    public void  add(Process process,MemoryBlock mb){
        Process fullProcess = new Process(getLastProcessId(),process.getMemory(),process.getPriority(),process.getTimeIn(),process.getTime(),process.getBurstTime(),process.getState());
        MemoryScheduler.findMostSuitableAvailableMemoryBlock(fullProcess.getMemory(),fullProcess.getId(),mb);
        queue.add(fullProcess);
        lastProcessId++;
    }

    public void add(String name,int id){

        Process process = new Process(name,id);
        this.queue.add(process);

    }

    public String toString(String whatsQueue) {
        String result = whatsQueue + ":\n{\n";

        for (int i =0;i<queue.size();i++){
            result += queue.get(i).toString() + "\n";
        }
        result += "}\n";
        return result;
    }
    //TODO Scheduling algorithm
}
