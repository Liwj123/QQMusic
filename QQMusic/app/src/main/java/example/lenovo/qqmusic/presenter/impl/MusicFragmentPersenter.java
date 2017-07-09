package example.lenovo.qqmusic.presenter.impl;

import javax.inject.Inject;

import example.lenovo.qqmusic.presenter.MusicFragmentContact;
import example.lenovo.qqmusic.ui.fragment.MainMusicFragment;

/**
 * Created by Lenovo on 2017/7/5.
 */

public class MusicFragmentPersenter implements MusicFragmentContact.persenter {

    MainMusicFragment musicFragment;

    @Inject
    public MusicFragmentPersenter(MainMusicFragment musicFragment) {
        this.musicFragment = musicFragment;
    }

    @Override
    public void setIntent(int id) {
        musicFragment.intentToActivity(id);
    }

    @Override
    public void setSongListIntent() {

    }
}
