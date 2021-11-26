package com.company.classes;

import java.util.Arrays;

public class CPU {
    Core[] cores;

    public CPU(final int coresNumber) {

        this.cores = new Core[coresNumber];
        for (int i=0;i<cores.length;i++){
            cores[i] = new Core();
        }
    }

    public Core[] getCores() {
        return cores;
    }

    public void setCores(Core[] cores) {
        this.cores = cores;
    }

    @Override
    public String toString() {
        String result = "CPU:\n{\n";
        for (var core:cores) {
            result += core.getCoreState()+"\n";
        }
        result += "}\n";
        return result;
    }
}
