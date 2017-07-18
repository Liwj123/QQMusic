package example.lenovo.qqmusic.http;

import java.util.Map;

import example.lenovo.qqmusic.http.service.RemoteMusicService;
import example.lenovo.qqmusic.model.NewMusicBean;
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

    public static synchronized HttpUtil getInstance(){
        if(httpUtil == null){
            httpUtil = new HttpUtil();
        }
        return httpUtil;
    }
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
}
