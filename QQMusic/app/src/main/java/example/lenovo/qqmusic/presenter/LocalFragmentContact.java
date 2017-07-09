package example.lenovo.qqmusic.presenter;

import java.util.ArrayList;

import example.lenovo.qqmusic.model.MusicBean;

/**
 * Created by Lenovo on 2017/7/6.
 */

public interface LocalFragmentContact {

    interface view{
        void setLocalMusic(ArrayList<MusicBean> list);
    }

    interface presenter{
        void getLocalMusic();
    }

}
