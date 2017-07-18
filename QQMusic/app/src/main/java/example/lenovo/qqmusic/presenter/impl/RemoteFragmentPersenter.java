package example.lenovo.qqmusic.presenter.impl;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import example.lenovo.qqmusic.http.HttpUtil;
import example.lenovo.qqmusic.model.NewMusicBean;
import example.lenovo.qqmusic.presenter.RemoteFragmentContact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lenovo on 2017/7/14.
 */

public class RemoteFragmentPersenter implements RemoteFragmentContact.presenter {

    RemoteFragmentContact.view view;

    @Inject
    public RemoteFragmentPersenter(RemoteFragmentContact.view view) {
        this.view = view;
    }

    @Override
    public void getSongList() {
        Map<String, String> map = new HashMap<>();
        map.put("from", "qianqian");
        map.put("version", "2.1.0");
        map.put("method", "baidu.ting.billboard.billList");
        map.put("format", "json");
        map.put("type", "1");
        map.put("offset", "0");
        map.put("size", "3");

        //新歌榜的网络访问
        HttpUtil.getInstance().getRestservice(
                new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        view.onNewMusic(response.body());
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {

                    }
                }
                , map);

        map.put("type", "2");
        map.put("offset", "0");
        map.put("size", "3");

        //热歌榜的网络访问
        HttpUtil.getInstance().getRestservice(
                new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        view.onHotMusic(response.body());
                        Log.e("haha",response.body().toString());
                }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {

                    }
                }
                , map);

        map.put("type", "18");
        map.put("offset", "0");
        map.put("size", "3");

        //中文榜的网络访问
        HttpUtil.getInstance().getRestservice(
                new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        view.onChinaMusic(response.body());
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {

                    }
                }
                , map);

        map.put("type", "6");
        map.put("offset", "0");
        map.put("size", "3");

        //KTV热搜榜的网络访问
        HttpUtil.getInstance().getRestservice(
                new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        view.onKtvMusic(response.body());
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {

                    }
                }
                , map);
    }
}
