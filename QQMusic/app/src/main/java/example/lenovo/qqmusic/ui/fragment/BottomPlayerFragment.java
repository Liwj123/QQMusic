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
import example.lenovo.qqmusic.model.BaseMusicBean;
import example.lenovo.qqmusic.model.MusicBean;
import example.lenovo.qqmusic.model.RemoteMusicBean;
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

    /**
     * 给底部导航栏设置播放的音乐信息
     * @param
     */
    public void setMusicInfo(BaseMusicBean bean) {
        if(bean instanceof MusicBean){
            mainBottomPlayerName.setText(((MusicBean)bean).getMusic_name());
            mainBottomPlayerSinger.setText(((MusicBean)bean).getMusic_artist());
        }else if(bean instanceof RemoteMusicBean){


        mainBottomPlayerName.setText(((RemoteMusicBean)bean).getSonginfo().getTitle());
        mainBottomPlayerSinger.setText(((RemoteMusicBean)bean).getSonginfo().getAuthor());
        }
    }

    /**
     * 判断当前的音乐状态，设置开始/暂停按钮
     * @param isPlay
     */
    public void setPlsyStatus(boolean isPlay){
        this.isPlay = isPlay;
        if (!isPlay){
            mainBottomPlayerPlay.setImageResource(android.R.drawable.ic_media_play);
        }else{
            mainBottomPlayerPlay.setImageResource(android.R.drawable.ic_media_pause);
        }
    }
}
