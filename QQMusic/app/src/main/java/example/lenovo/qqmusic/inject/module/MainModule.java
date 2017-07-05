package example.lenovo.qqmusic.inject.module;

import dagger.Module;
import dagger.Provides;
import example.lenovo.qqmusic.ui.fragment.MainLiveFragment;
import example.lenovo.qqmusic.ui.fragment.MainMusicFragment;
import example.lenovo.qqmusic.ui.fragment.MainVideoFragment;

/**
 * Created by Lenovo on 2017/7/4.
 */

@Module
public class MainModule {

    @Provides
    MainMusicFragment providesMainMusicFragment(){
        return new MainMusicFragment();
    }
    @Provides
    MainVideoFragment providesMainVideoFragment(){
        return new MainVideoFragment();
    }
    @Provides
    MainLiveFragment providesMainLiveFragment(){
        return new MainLiveFragment();
    }
}
