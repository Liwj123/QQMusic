package example.lenovo.qqmusic.inject.module;

import dagger.Module;
import dagger.Provides;
import example.lenovo.qqmusic.presenter.RemoteFragmentContact;

/**
 * Created by Lenovo on 2017/7/14.
 */

@Module
public class RemoteFragmentPersenterModule {

    RemoteFragmentContact.view view;

    public RemoteFragmentPersenterModule(RemoteFragmentContact.view view) {
        this.view = view;
    }

    @Provides
    public RemoteFragmentContact.view getView(){
        return view;
    }
}
