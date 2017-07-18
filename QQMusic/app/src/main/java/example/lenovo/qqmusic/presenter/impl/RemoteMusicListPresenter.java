package example.lenovo.qqmusic.presenter.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import example.lenovo.qqmusic.http.HttpUtil;
import example.lenovo.qqmusic.model.NewMusicBean;
import example.lenovo.qqmusic.presenter.RemoteMusicListContact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lenovo on 2017/7/17.
 */

public class RemoteMusicListPresenter implements RemoteMusicListContact.presenter {

    RemoteMusicListContact.view view;

    @Inject
    public RemoteMusicListPresenter(RemoteMusicListContact.view view) {
        this.view = view;
    }

    @Override
    public void getMusicList(int type, int offset, int size) {

        Map<String, String> map = new HashMap<>();
        map.put("from", "qianqian");
        map.put("version", "2.1.0");
        map.put("method", "baidu.ting.billboard.billList");
        map.put("format", "json");
        map.put("type", type + "");
        map.put("offset", offset + "");
        map.put("size", size + "");
        HttpUtil.getInstance()
                .getRestservice(new Callback<NewMusicBean>() {
                    @Override
                    public void onResponse(Call<NewMusicBean> call, Response<NewMusicBean> response) {
                        view.setRecyclerData(response.body());
                    }

                    @Override
                    public void onFailure(Call<NewMusicBean> call, Throwable t) {

                    }
                }, map);
    }
}
