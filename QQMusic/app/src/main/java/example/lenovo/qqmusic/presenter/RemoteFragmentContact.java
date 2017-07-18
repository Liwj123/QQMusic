package example.lenovo.qqmusic.presenter;

import example.lenovo.qqmusic.model.NewMusicBean;

/**
 * Created by Lenovo on 2017/7/14.
 */

public interface RemoteFragmentContact {
    interface view{
        void onNewMusic(NewMusicBean newMusicBean);
        void onHotMusic(NewMusicBean newMusicBean);
        void onChinaMusic(NewMusicBean newMusicBean);
        void onKtvMusic(NewMusicBean newMusicBean);
    }

    interface presenter{
        void getSongList();
    }
}
