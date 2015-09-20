package com.fernandocejas.sample.aragorn;

public class Main {
  public static void main(String[] args) {
    executeSample(args[0]);
  }

  private static void executeSample(String sampleName) {
    Sample sample;
    switch (sampleName) {
      case "Single":
        sample = new SingleSample();
        break;
      case "Observable":
        sample = new ObservableSample();
        break;
      case "Subscriber":
        sample = new SubscriberSample();
        break;
      case "Observer":
        sample = new ObserverSample();
        break;
      default:
        throw new IllegalArgumentException("Wrong parameter name for Sample");
    }
    sample.execute();
  }
}
