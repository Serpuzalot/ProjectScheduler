package com.company;

import com.company.classes.Process;
import com.company.classes.Queue;
import com.company.classes.Scheduler;

import java.util.Scanner;

public class Main {

    //hpf, 2 метод планирования памяти,наличие вытеснения ,не упорядоченный список
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cores number:");
        int cpuCoresNumber = scanner.nextInt();
        System.out.println("Enter memory volume:");
        int memoryVolume = scanner.nextInt();
        Scheduler scheduler = new Scheduler(cpuCoresNumber,memoryVolume);
        System.out.println(scheduler.toString());
    }
}
