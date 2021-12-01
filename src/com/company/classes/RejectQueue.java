package com.company.classes;

import com.company.interfaces.Queue;

import java.util.ArrayList;

public class RejectQueue implements Queue {

    private int lastProcessId;
    private  ArrayList<Process> queue;
    private int queueLastSize;

    public RejectQueue() {
        this.queue = new ArrayList<>();
        this.lastProcessId = 1 ;
        this.queueLastSize = queue.size();

    }

    @Override
    public int getLastProcessId() {
        return lastProcessId;
    }

    @Override
    public void add(Process process){
        Process fullProcess = new Process(getLastProcessId(),process.getMemory(),process.getPriority(),process.getTimeIn(),process.getTime(),process.getBurstTime(),process.getState());
        queue.add(fullProcess);
        lastProcessId++;
    }

    public boolean isNeadDefragmentation(){
        if(queue.size() - queueLastSize > 15){
            queueLastSize = queue.size();
            return  true;
        }

        return false;
    }

    @Override
    public void add(Process process, MemoryBlock mb) {

    }

    @Override
    public void add(String name, int id) {

    }

    @Override
    public Process getHighPriorityProcess() {
        return null;
    }

    @Override
    public String toString() {
        return "RejectQueue{" +
                "lastProcessId=" + lastProcessId +
                ", queue=" + queue +
                '}';
    }
}
