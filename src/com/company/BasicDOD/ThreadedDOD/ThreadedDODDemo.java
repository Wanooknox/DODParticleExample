package com.company.BasicDOD.ThreadedDOD;

public class ThreadedDODDemo {

    private static final int count = 1_048_576;

    public static void main(String[] args) {
        new Simulator(count).run();
    }

}
