package com.example.daye5.base;

import java.lang.ref.WeakReference;

public class BasePresenter<V> {

    public V mView;
    private WeakReference<V> weakReference;

    public void attach(V view){
        weakReference = new WeakReference<>(view);
        mView = weakReference.get();
    }

    public void detachView(){
        if (weakReference != null){
            weakReference.clear();
        }
    }
}
