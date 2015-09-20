package com.fernandocejas.sample.aragorn;

import com.fernandocejas.aragorn.annotation.RxLogSingle;
import java.util.Arrays;
import java.util.List;
import rx.Single;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

class SingleSample extends Sample {
  @Override String getSampleName() {
    return "Single Sample";
  }

  @Override
  public void executeSample() {
    stringList()
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.immediate())
        .subscribe(new Action1<List<String>>() {
          @Override
          public void call(List<String> strings) {
            //empty
          }
        });

    stringListError()
        .subscribeOn(Schedulers.newThread())
        .observeOn(Schedulers.immediate())
        .subscribe(new Subscriber<List<String>>() {
          @Override
          public void onCompleted() {
            //empty
          }

          @Override
          public void onError(Throwable e) {
            //empty
          }

          @Override
          public void onNext(List<String> strings) {
            //empty
          }
        });
  }

  @RxLogSingle
  private Single<List<String>> stringList() {
    return Single.just(Arrays.asList("fernandocejas.com", "android10.org"));
  }

  @RxLogSingle
  private Single<List<String>> stringListError() {
    return Single.error(new Exception("This is my custom error!"));
  }
}
