package example.lenovo.qqmusic.inject.component;

import dagger.Component;
import example.lenovo.qqmusic.inject.module.MainModule;
import example.lenovo.qqmusic.ui.fragment.MainFragment;

/**
 * Created by Lenovo on 2017/7/5.
 */

@Component(modules = MainModule.class)
public interface MainFragmentComponent {
    void inject(MainFragment mainFragment);
}
