package com.company.classes;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class MemoryScheduler {
    private static ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();
    public static void initial(){
        MemoryBlock block = new MemoryBlock(0,Configuration.osMemoryVolume,1);
        MemoryBlock freeSpace = new MemoryBlock(Configuration.osMemoryVolume,Configuration.memoryVolume,0);
        memoryBlocks.add(block);
        memoryBlocks.add(freeSpace);

    }

    public static boolean findMostSuitableAvailableMemoryBlock(int memoryVolume,int processID,MemoryBlock nededBlock){
        memoryBlocks.sort(MemoryBlock.byEnd);
        for(var block : memoryBlocks){
            synchronized (memoryBlocks){
                int blockMemory = block.getEnd() - block.getStart();
                int nededBlockMemory = nededBlock.getEnd() - nededBlock.getStart();
                if(nededBlockMemory > blockMemory && blockMemory >= memoryVolume && block.getProcessId() <=0 ){
                    nededBlock = block;
                }
            }
        }
        ocupyMemoryBlock(nededBlock,memoryVolume,processID);
        return true;
    }

    public static MemoryBlock findFreeBlock(int memoryVolume){
        synchronized (memoryBlocks){
            for (var mb : memoryBlocks){
                if(mb.getEnd() - mb.getStart() >= memoryVolume && mb.getProcessId() <=0){
                    return mb;
                }

            }
        }
        return new MemoryBlock(0,0,Integer.MIN_VALUE);
    }

    private static void ocupyMemoryBlock(MemoryBlock block , int memoryVolume , int processID){
        for(var el : memoryBlocks){
            if(block.equals(el)){
                if(block.getStart()+memoryVolume == block.getEnd()){
                    block.setProcessId(processID);
                    break;
                }else if(block.getStart() + memoryVolume < block.getEnd()){
                    MemoryBlock mb = new MemoryBlock(block.getStart(), memoryVolume + block.getStart(),processID);
                    memoryBlocks.add(mb);
                    block.setStart(mb.getEnd());
                    break;
                }
            }
        }
    }

    public static void releaseMemoryBlock(int id){
        for (var block : memoryBlocks){
            synchronized (memoryBlocks){
                if(block.getProcessId() == id){
                    block.setProcessId(-1);
                    break;
                }
            }

        }
    }

    public static void defragmentationStart(){
        System.out.println("Start Defragmentation");
        memoryBlocks.sort(MemoryBlock.byEnd);
        System.out.println(new MemoryScheduler().toString());
        ArrayList<MemoryBlock> newMemoryBlocks = new ArrayList<>();
        synchronized (memoryBlocks){
            int blockSiz=0;
            for (int i=0;i<memoryBlocks.size();i++){
                if(i == 0){
                    if(memoryBlocks.get(i).getProcessId() > 0){
                        blockSiz = memoryBlocks.get(i).getEnd() - memoryBlocks.get(i).getStart();
                        newMemoryBlocks.add(new MemoryBlock(0,blockSiz,memoryBlocks.get(i).getProcessId()));

                    }
                    if(memoryBlocks.get(i+1).getProcessId()>0){
                        blockSiz = memoryBlocks.get(i+1).getEnd() - memoryBlocks.get(i+1).getStart();
                        newMemoryBlocks.add(new MemoryBlock(newMemoryBlocks.get(0).getEnd(), newMemoryBlocks.get(0).getEnd() + blockSiz,memoryBlocks.get(i+1).getProcessId()));
                        i++;
                    }
                    continue;
                }
                if(memoryBlocks.get(i).getProcessId() > 0){
                    blockSiz = memoryBlocks.get(i).getEnd() - memoryBlocks.get(i).getStart();
                    newMemoryBlocks.add(new MemoryBlock(newMemoryBlocks.get(newMemoryBlocks.size()-1).getEnd(), newMemoryBlocks.get(newMemoryBlocks.size()-1).getEnd() + blockSiz,memoryBlocks.get(i).getProcessId()));
                }
            }
            newMemoryBlocks.add(new MemoryBlock(newMemoryBlocks.get(newMemoryBlocks.size()-1).getEnd(),Configuration.memoryVolume,0));
            memoryBlocks = newMemoryBlocks;
            memoryBlocks.sort(MemoryBlock.byEnd);
        }

        System.out.println("End defragmentation");
        System.out.println(new MemoryScheduler().toString());
    }

    @Override
    public String toString(){
        String result = "Memory Blocks:\n{\n";
        for (var el : memoryBlocks){
            result += el.toString() + "\n";
        }
        result += "}\n";
        return result;

    }

}
