package example.lenovo.qqmusic.manager;

/**
 * Created by Lenovo on 2017/7/13.
 */

public class MusicNofityManager {

    private static MusicNofityManager musicNofityManager;
    private INofity iNofity;

    public static synchronized MusicNofityManager getInstance(){
        if (musicNofityManager == null){
            musicNofityManager = new MusicNofityManager();
        }
        return musicNofityManager;
    }

    public INofity getiNofity() {
        return iNofity;
    }

    public void setiNofity(INofity iNofity) {
        this.iNofity = iNofity;
    }
}
