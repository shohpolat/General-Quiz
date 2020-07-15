package com.example.generalquizapp.other;

import android.graphics.Color;
import android.os.CountDownTimer;

import com.example.generalquizapp.Presenters.MainPresenter;
import com.example.generalquizapp.Views.MainActivity;

import java.util.Locale;

public class Timer  extends CountDownTimer {

    private static Timer instance;

    private Long timeLeftInMillis;

    private MainPresenter presenter;

    public Timer(long millisInFuture, long countDownInterval,MainPresenter presenter) {
        super(millisInFuture, countDownInterval);

        this.presenter = presenter;
    }

    @Override
    public void onTick(long l) {
       presenter.onTick(l);
    }

    @Override
    public void onFinish() {
       presenter.onFinish();
    }
}
