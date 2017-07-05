package example.lenovo.qqmusic.presenter.impl;


import android.os.Handler;
import android.os.Message;

import example.lenovo.qqmusic.presenter.SplashContact;

/**
 * Created by Lenovo on 2017/6/28.
 */

public class SplashPersenter implements SplashContact.Presenter {

    private SplashContact.View iSplashActivity;
    private static final int HANDLER_KEY = 101;
    private static final int HANDLER_TIME = 3000;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            iSplashActivity.intentToMain();
        }
    };

    public SplashPersenter(SplashContact.View iSplashActivity) {
        this.iSplashActivity = iSplashActivity;
    }

    @Override
    public void timer() {
        handler.sendEmptyMessageDelayed(HANDLER_KEY, HANDLER_TIME);
    }

    @Override
    public void startMain() {
        handler.removeMessages(HANDLER_KEY);
        iSplashActivity.intentToMain();
    }
}
