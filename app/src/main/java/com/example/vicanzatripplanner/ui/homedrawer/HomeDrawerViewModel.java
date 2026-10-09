package com.example.vicanzatripplanner.ui.homedrawer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeDrawerViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomeDrawerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is homedrawer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}