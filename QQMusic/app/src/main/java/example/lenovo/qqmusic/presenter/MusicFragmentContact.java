package example.lenovo.qqmusic.presenter;

/**
 * Created by Lenovo on 2017/7/5.
 */

public interface MusicFragmentContact {

    interface view{
        void intentToActivity(int id);

        void intentSongListActivity();
    }

    interface persenter{
        void setIntent(int id);

        void setSongListIntent();
    }

}
