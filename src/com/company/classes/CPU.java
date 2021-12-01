package com.company.classes;

import java.util.concurrent.locks.ReentrantLock;

public class CPU implements Runnable  {
   static Core[] cores;
   public Process process;

    public CPU(Process process) {
        this.process = process;
    }

    public static void initial(){
        for (int i=0;i<cores.length;i++){
            cores[i] = new Core(i);
        }
    }

    public static Core[] getCores() {
        return cores;
    }

    @Override
    public String toString() {
        String result = "CPU:\n{\n";
        for (var core:cores) {
            result += core.isFree()+"\n";
        }
        result += "}\n";
        return result;
    }

    @Override
    public void run() {
        if(process.getId() != 0){
            for (Core core:cores) {
                if(core.isFree() == true){
                    core.isFree(false);
                    core.setProcessID(process.getId());
                    break;
                }
            }

            for (int i =0;i<process.getBurstTime();i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            MemoryScheduler.releaseMemoryBlock(process.getId());
            ProcessQueue.changeState(process.getId(),State.Finished);

            for (Core core :cores){
                if(core.getProcessID() == process.getId()){
                    core.isFree(true);
                }
            }
        }
    }
}
