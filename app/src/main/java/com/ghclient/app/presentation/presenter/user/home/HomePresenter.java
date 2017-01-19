package com.ghclient.app.presentation.presenter.user.home;

import android.os.AsyncTask;
import android.util.Log;

import com.ghclient.app.presentation.presenter.common.base.BasePresenter;
import com.ghclient.app.presentation.view.user.home.IHomeView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<IHomeView> implements IHomePresenter {

    AsyncTask<Void, Integer, Void> asyncTask;

    @Inject
    public HomePresenter(IHomeView view) {

        super(view);

        asyncTask = new AsyncTask<Void, Integer, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < 1000000; i++) {
                    if (isCancelled()) {
                        break;
                    }
                    publishProgress(i);
                    sleepSecond();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                if (!isCancelled()) {
                    getView().setCounter(values[0]);
                }
            }

            private void sleepSecond() {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException ignore) {}
            }
        };

        asyncTask.execute();
    }

    @Override
    public void onDestroy() {
        Log.e("MATO", "Presenter.onDestroy");
        asyncTask.cancel(true);
        super.onDestroy();
    }
}
