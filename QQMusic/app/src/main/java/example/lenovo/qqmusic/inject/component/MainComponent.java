package example.lenovo.qqmusic.inject.component;

import dagger.Component;
import example.lenovo.qqmusic.inject.module.MainModule;
import example.lenovo.qqmusic.ui.activity.MainActivity;

/**
 * Created by Lenovo on 2017/7/4.
 */

@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
