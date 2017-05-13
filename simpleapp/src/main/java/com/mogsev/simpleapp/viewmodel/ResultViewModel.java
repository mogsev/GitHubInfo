package com.mogsev.simpleapp.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mogsev.simpleapp.BR;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class ResultViewModel extends BaseObservable {
    private static final String TAG = ResultViewModel.class.getSimpleName();

    private int result;

    public ResultViewModel() {

    }

    @Bindable
    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
    }

    public void onPlus() {
        setResult(result + 1);
    }

    public void onMinus() {
        setResult(result -1);
    }
}
