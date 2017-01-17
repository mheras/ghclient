package com.ghclient.app.presentation.presenter.user.home;

import android.os.AsyncTask;

import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.view.user.home.IHomeView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {

    @Inject
    public HomePresenter(IHomeView view) {

        super(view);

        AsyncTask<Void, Integer, Void> asyncTask = new AsyncTask<Void, Integer, Void>() {

            @Override
            protected void onPreExecute() {
                getView().setCounter(0);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 1; i < 1000000; i++) {
                    publishProgress(i);
                    sleepSecond();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                getView().setCounter(values[0]);
            }

            @Override
            protected void onPostExecute(Void aVoid) {
            }

            private void sleepSecond() {
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException ignore) {}
            }
        };

        asyncTask.execute();
    }
}
