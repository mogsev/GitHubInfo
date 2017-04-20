package com.mogsev.githubinfo.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com)
 */

public class Repository implements Parcelable {

    public static final String BUNDLE_NAME = Repository.class.getSimpleName();

    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("owner")
    private Owner owner;

    @Expose
    @SerializedName("description")
    private String description;

    @Expose
    @SerializedName("forks_count")
    private int forksCount;

    @Expose
    @SerializedName("subscribers_url")
    private String subscribersUrl;

    public Repository() {

    }

    protected Repository(final Parcel in) {
        id = in.readInt();
        name = in.readString();
        owner = in.readParcelable(getClass().getClassLoader());
        description = in.readString();
        forksCount = in.readInt();
        subscribersUrl = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeParcelable(owner, flags);
        parcel.writeString(description);
        parcel.writeInt(forksCount);
        parcel.writeString(subscribersUrl);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Owner getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
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

    @Override
    public String toString() {
        return "Repository{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", description='" + description + '\'' +
                ", forksCount=" + forksCount +
                ", subscribersUrl='" + subscribersUrl + '\'' +
                '}';
    }
}
