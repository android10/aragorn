package com.fernandocejas.sample.aragorn;

import com.fernandocejas.aragorn.annotation.RxLogSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

class SubscriberSample extends Sample {
  @Override String getSampleName() {
    return "Subscriber Sample";
  }

  @Override
  public void executeSample() {
    numbersWithBackPressure()
        .onBackpressureDrop()
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.immediate())
        .subscribe(new MySubscriber());

    numbersError()
        .onBackpressureDrop()
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.immediate())
        .subscribe(new MySubscriber());
  }

  private Observable<Integer> numbersWithBackPressure() {
    return Observable.create(new Observable.OnSubscribe<Integer>() {
      @Override
      public void call(Subscriber<? super Integer> subscriber) {
        try {
          if (!subscriber.isUnsubscribed()) {
            for (int i = 1; i < 10000; i++) {
              subscriber.onNext(i);
            }
            subscriber.onCompleted();
          }
        } catch (Exception e) {
          subscriber.onError(e);
        }
      }
    });
  }

  private Observable<Integer> numbersError() {
    return Observable.error(new Exception("This is my custom error!"));
  }

  @RxLogSubscriber
  private static class MySubscriber extends Subscriber<Integer> {
    @Override
    public void onStart() {
      request(40);
    }

    @Override
    public void onNext(Integer value) {
      //empty
    }

    @Override
    public void onError(Throwable throwable) {
      //empty
    }

    @Override
    public void onCompleted() {
      if (!isUnsubscribed()) {
        unsubscribe();
      }
    }
  }
}
