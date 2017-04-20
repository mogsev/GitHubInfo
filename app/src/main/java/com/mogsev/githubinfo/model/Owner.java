package com.mogsev.githubinfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class Owner implements Parcelable {

    public static final String BUNDLE_NAME = Owner.class.getSimpleName();

    @Expose
    @SerializedName("login")
    private String login;

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("avatar_url")
    private String avatarUrl;

    protected Owner(final Parcel in) {
        login = in.readString();
        id = in.readInt();
        avatarUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeString(login);
        parcel.writeInt(id);
        parcel.writeString(avatarUrl);
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public static final Parcelable.Creator<Owner> CREATOR = new Parcelable.Creator<Owner>() {
        @Override
        public Owner createFromParcel(Parcel in) {
            return new Owner(in);
        }

        @Override
        public Owner[] newArray(int size) {
            return new Owner[size];
        }
    };

    @Override
    public String toString() {
        return "Owner{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
