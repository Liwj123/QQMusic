package example.lenovo.qqmusic.inject.component;

import dagger.Component;
import example.lenovo.qqmusic.inject.module.MusicFragmentAdapterModule;
import example.lenovo.qqmusic.inject.module.MusicFragmentPersenterModule;
import example.lenovo.qqmusic.ui.fragment.MainMusicFragment;

/**
 * Created by Lenovo on 2017/7/5.
 */

@Component(modules = {MusicFragmentAdapterModule.class,MusicFragmentPersenterModule.class})
public interface MusicFragmentComponent {
    void inject(MainMusicFragment mainMusicFragment);
}
