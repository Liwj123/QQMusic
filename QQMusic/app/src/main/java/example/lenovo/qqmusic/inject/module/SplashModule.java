package example.lenovo.qqmusic.inject.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.lenovo.qqmusic.presenter.SplashContact;
import example.lenovo.qqmusic.presenter.impl.SplashPersenter;

/**
 * Created by Lenovo on 2017/7/4.
 */

@Module
public class SplashModule {

    SplashContact.Presenter splashPresenter;

    public SplashModule(SplashContact.View activity) {
        this.splashPresenter = new SplashPersenter(activity);
    }

    @Provides
    SplashContact.Presenter providesMainPresenter() {
        return splashPresenter;
    }

}
