package example.lenovo.qqmusic.presenter;

import java.util.ArrayList;

import example.lenovo.qqmusic.model.NewMusicBean;

/**
 * Created by Lenovo on 2017/7/17.
 */

public interface RemoteMusicListContact {

    interface view{
        void setRecyclerData(NewMusicBean musicBean);
    }

    interface presenter{
        void getMusicList(int type,int offset,int size);
    }
}
