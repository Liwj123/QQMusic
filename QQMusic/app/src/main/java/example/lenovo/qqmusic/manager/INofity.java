package example.lenovo.qqmusic.manager;

import example.lenovo.qqmusic.model.BaseMusicBean;

/**
 * Created by Lenovo on 2017/7/13.
 */

public interface INofity {
    void playIndex(BaseMusicBean musicBean);
    void playStatus(boolean isPlay);
}
