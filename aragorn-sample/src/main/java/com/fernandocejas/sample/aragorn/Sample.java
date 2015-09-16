package com.fernandocejas.sample.aragorn;

abstract class Sample {
    private static final String LINE_SEPARATOR = "-------------------------------------";

    abstract String getSampleName();
    abstract void executeSample();

    void execute() {
        printSampleHeader();
        executeSample();
    }

    private void printSampleHeader() {
        System.out.println(LINE_SEPARATOR);
        System.out.println(getSampleName());
        System.out.println(LINE_SEPARATOR);
    }
}
