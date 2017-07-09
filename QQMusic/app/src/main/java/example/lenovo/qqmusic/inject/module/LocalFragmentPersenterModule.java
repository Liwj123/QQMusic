package example.lenovo.qqmusic.inject.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import example.lenovo.qqmusic.ui.fragment.LocalFragment;

/**
 * Created by Lenovo on 2017/7/6.
 */

@Module
public class LocalFragmentPersenterModule {

    Context context;
    LocalFragment localFragment;

    public LocalFragmentPersenterModule(Context context, LocalFragment localFragment) {
        this.context = context;
        this.localFragment = localFragment;
    }

    @Provides
    LocalFragment providesLocalFragment() {
        return localFragment;
    }

    @Provides
    Context providescontext() {
        return context;
    }
}
