package example.lenovo.qqmusic.inject.component;

import dagger.Component;
import example.lenovo.qqmusic.inject.module.RemoteFragmentPersenterModule;
import example.lenovo.qqmusic.ui.fragment.RemoteFragment;

/**
 * Created by Lenovo on 2017/7/14.
 */

@Component(modules = RemoteFragmentPersenterModule.class)
public interface RemoteFragmentComponent {
    void inject(RemoteFragment remoteFragment);
}
