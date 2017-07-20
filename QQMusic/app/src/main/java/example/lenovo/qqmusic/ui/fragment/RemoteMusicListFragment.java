package example.lenovo.qqmusic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.lenovo.qqmusic.Final;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.inject.component.DaggerRemoteMusicListComponent;
import example.lenovo.qqmusic.inject.module.RemoteMusicListModule;
import example.lenovo.qqmusic.model.BaseMusicBean;
import example.lenovo.qqmusic.model.MusicBean;
import example.lenovo.qqmusic.model.NewMusicBean;
import example.lenovo.qqmusic.model.RemoteMusicBean;
import example.lenovo.qqmusic.presenter.RemoteMusicListContact;
import example.lenovo.qqmusic.presenter.impl.RemoteMusicListPresenter;
import example.lenovo.qqmusic.ui.activity.MainActivity;
import example.lenovo.qqmusic.ui.adapter.MusicListAdapter;
import example.lenovo.qqmusic.ui.adapter.RemoteMusicAdapter;
import example.lenovo.qqmusic.view.ILocalFragmentClick;

/**
 * Created by Lenovo on 2017/7/17.
 */

public class RemoteMusicListFragment extends BaseFragment implements RemoteMusicListContact.view {

    @BindView(R.id.remote_music_list_title)
    TextView title;
    @BindView(R.id.remote_toolbar)
    Toolbar toolbar;
    @BindView(R.id.remote_music_list_recycler)
    RecyclerView recycler;
    Unbinder unbinder;

    RemoteMusicAdapter musicListAdapter;
    ArrayList<NewMusicBean.SongListBean> list = new ArrayList<>();
    ArrayList<RemoteMusicBean> musicList = new ArrayList<>();
    private static final int SIZE = 50;
    private int offset = 0;
    ArrayList<BaseMusicBean> arrayList = new ArrayList<>();

    @Inject
    RemoteMusicListPresenter persenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote_music_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).onBack();
            }
        });

        DaggerRemoteMusicListComponent
                .builder()
                .remoteMusicListModule(new RemoteMusicListModule(this))
                .build()
                .inject(this);

        int type = getArguments().getInt(Final.API_MUSIC_TYPE);

        switch (type) {
            case Final.API_NEW_MUSIC:
                title.setText("新歌榜");
                break;
            case Final.API_HOT_MUSIC:
                title.setText("热歌榜");
                break;
            case Final.API_CHINA_MUSIC:
                title.setText("中文榜");
                break;
            case Final.API_KTV_MUSIC:
                title.setText("KTV热歌榜");
                break;
        }
        persenter.getMusicList(type, offset, SIZE);

        setRecycler();
    }

    private void initData() {
        for (int i = 0; i < list.size(); i++) {
            persenter.getMusicInfo(list.get(i));
        }
    }

    private void setRecycler() {
        musicListAdapter = new RemoteMusicAdapter(getActivity(), musicList);
        recycler.setAdapter(musicListAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recycler.setLayoutManager(llm);
        musicListAdapter.setiLocalFragmentClick(new ILocalFragmentClick() {
            @Override
            public void onLocalListClick(int position) {
                ((MainActivity) getActivity()).play(arrayList,position);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setRecyclerData(NewMusicBean musicBean) {
        list.clear();
        list.addAll(musicBean.getSong_list());
        initData();
        Log.e("list", musicList.toString());
    }

    @Override
    public void setMusicPlay(RemoteMusicBean musicPlay) {
        arrayList.add(musicPlay);
        musicList.add(musicPlay);
        musicListAdapter.notifyDataSetChanged();
    }
}
