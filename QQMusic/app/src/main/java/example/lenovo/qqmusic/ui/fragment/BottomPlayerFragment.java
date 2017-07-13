package example.lenovo.qqmusic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.model.MusicBean;
import example.lenovo.qqmusic.ui.activity.MainActivity;

/**
 * 音乐播放控制器
 * Created by Lenovo on 2017/7/7.
 */

public class BottomPlayerFragment extends BaseFragment {

    @BindView(R.id.main_bottom_player_play)
    ImageView mainBottomPlayerPlay;
    @BindView(R.id.main_bottom_player_next)
    ImageView mainBottomPlayerNext;
    @BindView(R.id.main_bottom_player)
    LinearLayout mainBottomPlayer;
    Unbinder unbinder;
    @BindView(R.id.main_bottom_player_name)
    TextView mainBottomPlayerName;
    @BindView(R.id.main_bottom_player_singer)
    TextView mainBottomPlayerSinger;
    private boolean isPlay = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_botton_player, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.main_bottom_player_play, R.id.main_bottom_player_next, R.id.main_bottom_player})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_bottom_player_play:
                if (isPlay){
                    ((MainActivity)getActivity()).pause();
                    mainBottomPlayerPlay.setImageResource(android.R.drawable.ic_media_play);
                }else{
                    ((MainActivity)getActivity()).start();
                    mainBottomPlayerPlay.setImageResource(android.R.drawable.ic_media_pause);
                }
                break;
            case R.id.main_bottom_player_next:
                ((MainActivity)getActivity()).next();
                break;
            case R.id.main_bottom_player:
                break;
        }
    }

    public void setMusicInfo(MusicBean musicInfo) {
        mainBottomPlayerName.setText(musicInfo.getMusic_name());
        mainBottomPlayerSinger.setText(musicInfo.getMusic_artist());
    }

    public void setPlsyStatus(boolean isPlay){
        this.isPlay = isPlay;
        if (!isPlay){
            mainBottomPlayerPlay.setImageResource(android.R.drawable.ic_media_play);
        }else{
            mainBottomPlayerPlay.setImageResource(android.R.drawable.ic_media_pause);
        }
    }
}
