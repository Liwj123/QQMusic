package example.lenovo.qqmusic.http;

import java.util.Map;

import example.lenovo.qqmusic.http.service.RemoteMusicItemService;
import example.lenovo.qqmusic.http.service.RemoteMusicService;
import example.lenovo.qqmusic.model.NewMusicBean;
import example.lenovo.qqmusic.model.RemoteMusicBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 2017/7/14.
 */

public class HttpUtil {

    private static HttpUtil httpUtil;
    private RemoteMusicService remoteMusicService;
    private RemoteMusicItemService remoteMusicItemService;

    public static synchronized HttpUtil getInstance(){
        if(httpUtil == null){
            httpUtil = new HttpUtil();
        }
        return httpUtil;
    }

    /**
     * 访问网络榜单
     * @return
     */
    private RemoteMusicService getService(){
        if (remoteMusicService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return remoteMusicService = retrofit.create(RemoteMusicService.class);
        }else{
            return remoteMusicService;
        }
    }

    public void getRestservice(Callback<NewMusicBean> callback,Map<String, String> map){
        getService();
        Call<NewMusicBean> call = remoteMusicService.getRestService(map);

        call.enqueue(callback);
    }

    /**
     * 访问网络榜单中的音乐
     * @return
     */
    private RemoteMusicItemService getItemService(){
        if (remoteMusicItemService == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API.ROOT_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return remoteMusicItemService = retrofit.create(RemoteMusicItemService.class);
        }else{
            return remoteMusicItemService;
        }
    }

    public void getItemRestservice(Callback<RemoteMusicBean> callback, Map<String, String> map){
        getItemService();
        Call<RemoteMusicBean> call = remoteMusicItemService.getRestService(map);
        call.enqueue(callback);
    }

}
