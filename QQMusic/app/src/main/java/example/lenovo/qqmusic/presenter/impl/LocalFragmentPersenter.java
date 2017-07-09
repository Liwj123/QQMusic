package example.lenovo.qqmusic.presenter.impl;

import android.content.Context;

import javax.inject.Inject;

import example.lenovo.qqmusic.presenter.LocalFragmentContact;
import example.lenovo.qqmusic.ui.fragment.LocalFragment;
import example.lenovo.qqmusic.view.FileUtil;

/**
 * Created by Lenovo on 2017/7/6.
 */

public class LocalFragmentPersenter implements LocalFragmentContact.presenter {

    LocalFragment localFragment;
    Context context;

    @Inject
    public LocalFragmentPersenter(LocalFragment localFragment, Context context) {
        this.localFragment = localFragment;
        this.context = context;
    }

    @Override
    public void getLocalMusic() {
        localFragment.setLocalMusic(FileUtil.getMusicFiles(context));
    }
}
