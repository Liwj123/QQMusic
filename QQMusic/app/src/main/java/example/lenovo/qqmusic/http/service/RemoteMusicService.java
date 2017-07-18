package example.lenovo.qqmusic.http.service;

import java.util.Map;

import example.lenovo.qqmusic.http.API;
import example.lenovo.qqmusic.model.NewMusicBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * Created by Lenovo on 2017/7/14.
 */

public interface RemoteMusicService {

    @Headers("User-Agent:Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:0.9.4)")
    @GET(API.ACTION_TING)
    Call<NewMusicBean> getRestService(@QueryMap Map<String, String> map);
}
