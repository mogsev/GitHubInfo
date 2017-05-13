package com.mogsev.appdatabinding.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.mogsev.appdatabinding.BR;
import com.mogsev.appdatabinding.model.Owner;
import com.mogsev.appdatabinding.model.Repository;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class RepositoryViewModel extends BaseObservable {

    private int id;
    private String name;
    private OwnerViewModel owner;
    private String description;
    private int forksCount;
    private String subscribersUrl;

    public RepositoryViewModel() {

    }

    public RepositoryViewModel(Repository repository) {

    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public OwnerViewModel getOwner() {
        return owner;
    }

    public void setOwner(OwnerViewModel owner) {
        this.owner = owner;
        notifyPropertyChanged(BR.owner);
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        notifyPropertyChanged(BR.description);
    }

    @Bindable
    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
        notifyPropertyChanged(BR.forksCount);
    }

    @Bindable
    public String getSubscribersUrl() {
        return subscribersUrl;
    }

    public void setSubscribersUrl(String subscribersUrl) {
        this.subscribersUrl = subscribersUrl;
        notifyPropertyChanged(BR.subscribersUrl);
    }
}
