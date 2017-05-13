package com.mogsev.simpleapp.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class Result implements Parcelable {

    private static final String BUNDLE_NAME = Result.class.getSimpleName();

    private int result;

    public Result() {

    }

    protected Result(final Parcel in) {
        in.writeInt(result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        result = parcel.readInt();
    }

    public static final Creator<Result> CREATOR = new Creator<Result>() {
        @Override
        public Result createFromParcel(final Parcel in) {
            return new Result(in);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };

}
