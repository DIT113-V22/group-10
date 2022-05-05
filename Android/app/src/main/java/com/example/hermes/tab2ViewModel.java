package com.example.hermes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class tab2ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public tab2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tab1 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
