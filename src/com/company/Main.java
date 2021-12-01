package com.company;

import com.company.classes.Scheduler;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    //hpf, 2 метод планирования памяти,наличие вытеснения ,не упорядоченный список
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cores number:");
        int cpuCoresNumber = scanner.nextInt();
        System.out.println("Enter memory volume:");
        int memoryVolume = scanner.nextInt();
        Scheduler scheduler ;
        new Thread(scheduler = new Scheduler(cpuCoresNumber,memoryVolume)).start();
        scanner.next();
        scheduler.shutDown();
    }
}
