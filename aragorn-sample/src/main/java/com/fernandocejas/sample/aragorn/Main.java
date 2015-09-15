package com.fernandocejas.sample.aragorn;

public class Main {
    public static void main(String[] args) {
        executeSample(args[0]);
    }

    private static void executeSample(String sampleName) {
        switch (sampleName) {
            case "Single":
                System.out.print("Hello Single");
                break;
            case "Observable":
                System.out.print("Hello Observable");
                break;
            case "Subscriber":
                System.out.print("Hello Subscriber");
                break;
            case "Observer":
                System.out.print("Hello Observer");
                break;
            default:
                throw new IllegalArgumentException("Wrong parameter name for Sample");
        }
    }
}
