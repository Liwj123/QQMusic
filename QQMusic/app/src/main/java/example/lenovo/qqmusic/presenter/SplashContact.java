package example.lenovo.qqmusic.presenter;

/**
 * Created by Lenovo on 2017/7/4.
 */

public interface SplashContact {
    interface View {
        void intentToMain();


    }

    interface Presenter {
        void timer();

        void startMain();
    }
}
