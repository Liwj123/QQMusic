package example.lenovo.qqmusic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.inject.component.DaggerMainComponent;
import example.lenovo.qqmusic.inject.component.DaggerMusicFragmentAdapterComponent;
import example.lenovo.qqmusic.inject.module.MainModule;
import example.lenovo.qqmusic.inject.module.MusicFragmentAdapterModule;
import example.lenovo.qqmusic.ui.adapter.SongListAdapter;
import example.lenovo.qqmusic.view.ScrollRecyclerLayoutManager;

/**
 * Created by Lenovo on 2017/7/4.
 */

public class MainMusicFragment extends BaseFragment {

    @BindView(R.id.music_local_ll)
    LinearLayout musicLocalLl;
    @BindView(R.id.music_internet_ll)
    LinearLayout musicInternetLl;
    @BindView(R.id.music_recently_ll)
    LinearLayout musicRecentlyLl;
    @BindView(R.id.music_like_ll)
    LinearLayout musicLikeLl;
    @BindView(R.id.music_download_ll)
    LinearLayout musicDownloadLl;
    @BindView(R.id.music_push_ll)
    LinearLayout musicPushLl;
    @BindView(R.id.music_recycler)
    RecyclerView musicRecycler;
    Unbinder unbinder;

    @Inject
    SongListAdapter songListAdapter;

    ArrayList<String> songList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_music, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        songList = getSongList();

        DaggerMusicFragmentAdapterComponent
                .builder()
                .musicFragmentAdapterModule(new MusicFragmentAdapterModule(getActivity(), songList))
                .build()
                .inject(this);

        setListView();
    }

    private void setListView() {
        musicRecycler.setAdapter(songListAdapter);
        musicRecycler.setLayoutManager(new ScrollRecyclerLayoutManager(getActivity()));
        musicRecycler.setNestedScrollingEnabled(false);
    }

    private ArrayList<String> getSongList() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add("歌单" + i);
        }

        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.music_local_ll, R.id.music_internet_ll, R.id.music_recently_ll, R.id.music_like_ll, R.id.music_download_ll, R.id.music_push_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.music_local_ll:
                break;
            case R.id.music_internet_ll:
                break;
            case R.id.music_recently_ll:
                break;
            case R.id.music_like_ll:
                break;
            case R.id.music_download_ll:
                break;
            case R.id.music_push_ll:
                break;
        }
    }
}
