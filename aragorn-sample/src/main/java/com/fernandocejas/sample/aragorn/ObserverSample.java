package com.fernandocejas.sample.aragorn;

import com.fernandocejas.aragorn.annotation.RxLogObserver;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

class ObserverSample extends Sample {
  @Override String getSampleName() {
    return "Observer Sample";
  }

  @Override
  public void executeSample() {
    strings()
        .onBackpressureDrop()
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.immediate())
        .subscribe(new MyObserver());

    stringsError()
        .onBackpressureDrop()
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.immediate())
        .subscribe(new MyObserver());
  }

  private Observable<String> strings() {
    return Observable.just("Hello", "My", "Name", "Is", "Fernando");
  }

  private Observable<String> stringsError() {
    return Observable.error(new Exception("This is my custom error!"));
  }

  @RxLogObserver
  private static class MyObserver implements Observer<String> {
    @Override
    public void onNext(String value) {
      //empty
    }

    @Override
    public void onError(Throwable throwable) {
      //empty
    }

    @Override
    public void onCompleted() {
      //empty
    }
  }
}
