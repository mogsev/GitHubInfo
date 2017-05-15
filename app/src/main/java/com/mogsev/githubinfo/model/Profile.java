package com.mogsev.githubinfo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class Profile implements Parcelable {

    public static final String TAG = Profile.class.getSimpleName();

    private String firstName;

    private String lastName;

    private String email;

    public Profile() {

    }

    public Profile(final Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        email = in.readString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(email);
    }

    public static final Parcelable.Creator<Repository> CREATOR = new Parcelable.Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
}
