package example.lenovo.qqmusic.inject.component;

import dagger.Component;
import example.lenovo.qqmusic.inject.module.RemoteMusicListModule;
import example.lenovo.qqmusic.ui.fragment.RemoteMusicListFragment;

/**
 * Created by Lenovo on 2017/7/17.
 */

@Component(modules = RemoteMusicListModule.class)
public interface RemoteMusicListComponent {
    void inject(RemoteMusicListFragment fragment);
}
