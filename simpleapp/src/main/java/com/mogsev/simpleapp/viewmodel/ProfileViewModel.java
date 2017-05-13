package com.mogsev.simpleapp.viewmodel;

import android.databinding.ObservableField;

import com.mogsev.simpleapp.model.Profile;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class ProfileViewModel extends BaseViewModel<Profile> {
    private static final String TAG = ProfileViewModel.class.getSimpleName();

    private ObservableField<String> firstName = new ObservableField<>();
    private ObservableField<String> lastName = new ObservableField<>();

    public ProfileViewModel() {

    }

    public ProfileViewModel(Profile profile) {
        setModel(profile);
    }

    public ObservableField<String> getFirstName() {
        return firstName;
    }

    public void setFirstName(ObservableField<String> firstName) {
        this.firstName = firstName;
    }

    public ObservableField<String> getLastName() {
        return lastName;
    }

    public void setLastName(ObservableField<String> lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setModel(Profile model) {
        firstName.set(model.getFirstName());
        lastName.set(model.getLastName());
    }

    @Override
    public Profile getModel() {
        Profile profile = new Profile();
        profile.setFirstName(firstName.get());
        profile.setLastName(lastName.get());
        return profile;
    }


}
