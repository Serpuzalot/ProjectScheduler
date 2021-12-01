package com.company.classes;

public class Core {
    private boolean isFree;
    private int coreID;
    private int processID;

    Core(int coreID){
        this.coreID = coreID;
        isFree = true;
    }


    public boolean isFree() {
        return isFree;
    }

    public void isFree(boolean free) {
        isFree = free;
    }

    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }
}
