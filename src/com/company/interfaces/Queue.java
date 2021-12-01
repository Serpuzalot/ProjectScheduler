package com.company.interfaces;

import com.company.classes.MemoryBlock;
import com.company.classes.Process;
import com.company.classes.State;

public interface Queue {

    public int getLastProcessId();

    public void add(Process process);

    public void  add(Process process, MemoryBlock mb);

    public void add(String name,int id);

    public Process getHighPriorityProcess();

}
