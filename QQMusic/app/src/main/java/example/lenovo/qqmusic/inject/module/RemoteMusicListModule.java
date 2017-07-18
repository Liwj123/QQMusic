package example.lenovo.qqmusic.inject.module;

import dagger.Module;
import dagger.Provides;
import example.lenovo.qqmusic.presenter.RemoteMusicListContact;

/**
 * Created by Lenovo on 2017/7/17.
 */

@Module
public class RemoteMusicListModule {

    RemoteMusicListContact.view view;

    public RemoteMusicListModule(RemoteMusicListContact.view view) {
        this.view = view;
    }

    @Provides
    RemoteMusicListContact.view getView(){
        return view;
    }

}
