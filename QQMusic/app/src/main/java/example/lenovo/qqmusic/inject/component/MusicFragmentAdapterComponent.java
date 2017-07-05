package example.lenovo.qqmusic.inject.component;

import dagger.Component;
import example.lenovo.qqmusic.inject.module.MusicFragmentAdapterModule;
import example.lenovo.qqmusic.ui.fragment.MainMusicFragment;

/**
 * Created by Lenovo on 2017/7/4.
 */

@Component(modules = MusicFragmentAdapterModule.class)
public interface MusicFragmentAdapterComponent {
    void inject(MainMusicFragment mainMusicFragment);
}
