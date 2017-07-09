package example.lenovo.qqmusic.inject.component;

import dagger.Component;
import example.lenovo.qqmusic.inject.module.LocalFragmentPersenterModule;
import example.lenovo.qqmusic.ui.fragment.LocalFragment;

/**
 * Created by Lenovo on 2017/7/6.
 */

@Component(modules = LocalFragmentPersenterModule.class)
public interface LocalFragmentComponent {
    void inject(LocalFragment localFragment);
}
