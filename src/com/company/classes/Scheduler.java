package com.company.classes;

public class Scheduler {
    Queue queue;
    Queue rejectQueue;
    CPU cpu;
    MemoryScheduler memoryScheduler;

    public Scheduler(final int cpuCoresNumber,int memoryVolume) {
        this.queue = new Queue();
        this.rejectQueue = new Queue();
        this.cpu = new CPU(cpuCoresNumber);
        this.memoryScheduler = new MemoryScheduler();
        Configuration.memoryVolume = memoryVolume;
        start();
    }

    private void start(){
        MemoryScheduler.initial();
        queue.add("OS",1);
        generateProcess(7);
    }

    public void generateProcess(final int N){
        for (int i =0 ;i<N;i++){
            Process process = new Process();
            MemoryBlock mb = MemoryScheduler.findFreeBlock(process.getMemory());
            if(mb.getStart() == 0 ){
                rejectQueue.add(process);
            }else{
                queue.add(process,mb);
            }
        }
    }

    @Override
    public String toString() {
        return "Scheduler\n{\n" +
                "" + queue.toString("queue") +
                "" + rejectQueue.toString("reject queue") +
                "" + cpu +
                "" + memoryScheduler +
                '}';
    }
}
