package example.lenovo.qqmusic.inject.component;

import dagger.Component;
import example.lenovo.qqmusic.inject.module.SplashModule;
import example.lenovo.qqmusic.ui.activity.SplashActivity;

/**
 * Created by Lenovo on 2017/7/4.
 */

@Component(modules = SplashModule.class)
public interface SplashComponent {
    void inject(SplashActivity splashActivity);
}
