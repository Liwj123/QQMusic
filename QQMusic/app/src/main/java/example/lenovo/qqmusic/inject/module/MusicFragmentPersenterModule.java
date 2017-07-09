package example.lenovo.qqmusic.inject.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import example.lenovo.qqmusic.presenter.impl.MusicFragmentPersenter;
import example.lenovo.qqmusic.ui.fragment.MainMusicFragment;

/**
 * Created by Lenovo on 2017/7/5.
 */

@Module
public class MusicFragmentPersenterModule {

    MainMusicFragment musicFragment;

    public MusicFragmentPersenterModule(MainMusicFragment musicFragment) {
        this.musicFragment = musicFragment;
    }

    @Provides
    MainMusicFragment providesMainMusicFragment(){
        return this.musicFragment;
    }
}
