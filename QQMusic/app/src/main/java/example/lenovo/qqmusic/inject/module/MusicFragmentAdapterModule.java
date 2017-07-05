package example.lenovo.qqmusic.inject.module;

import android.content.Context;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Lenovo on 2017/7/4.
 */

@Module
public class MusicFragmentAdapterModule {
    Context context;
    ArrayList<String> list;

    public MusicFragmentAdapterModule(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Provides
    Context providesContext() {
        return context;
    }

    @Provides
    ArrayList<String> providesArrayList() {
        return list;
    }
}
