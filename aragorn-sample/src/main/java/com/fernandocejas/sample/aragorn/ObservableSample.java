package com.fernandocejas.sample.aragorn;

import com.fernandocejas.aragorn.annotation.RxLogObservable;
import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;

class ObservableSample extends Sample {
    @Override
    String getSampleName() {
        return "Observable Sample";
    }

    @Override
    public void executeSample() {
        dummyClassList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.immediate())
                .subscribe(new Action1<List<MyDummyClass>>() {
                    @Override
                    public void call(List<ObservableSample.MyDummyClass> myDummyClasses) {
                        //empty
                    }
                });

        dummyClassListError()
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.immediate())
                .subscribe(new Action1<List<MyDummyClass>>() {
                    @Override
                    public void call(List<ObservableSample.MyDummyClass> myDummyClasses) {
                        //empty
                    }
                });
    }

    @RxLogObservable
    private Observable<List<MyDummyClass>> dummyClassList() {
        return Observable.just(Arrays.asList(new MyDummyClass("Tony Stark"), new MyDummyClass("Fernando Cejas")));
    }

    @RxLogObservable
    private Observable<List<MyDummyClass>> dummyClassListError() {
        return Observable.error(new Exception("This is my custom error!"));
    }

    public static final class MyDummyClass {
        private final String name;

        MyDummyClass(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Name: " + name;
        }
    }
}
