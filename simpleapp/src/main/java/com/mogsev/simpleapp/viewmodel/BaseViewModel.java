package com.mogsev.simpleapp.viewmodel;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.os.Parcelable;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public abstract class BaseViewModel<T extends Parcelable> extends BaseObservable {

    public abstract void setModel(T model);

    public abstract T getModel();

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(getClass().getSimpleName(), getModel());
    }

    public void onRestoreInstanceState(Bundle bundle) {
        setModel(bundle.getParcelable(getClass().getSimpleName()));
    }

}
