package example.lenovo.qqmusic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import example.lenovo.qqmusic.Final;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.inject.component.DaggerRemoteFragmentComponent;
import example.lenovo.qqmusic.inject.module.RemoteFragmentPersenterModule;
import example.lenovo.qqmusic.model.NewMusicBean;
import example.lenovo.qqmusic.presenter.RemoteFragmentContact;
import example.lenovo.qqmusic.presenter.impl.RemoteFragmentPersenter;
import example.lenovo.qqmusic.ui.activity.MainActivity;
import example.lenovo.qqmusic.view.ImageMode;

/**
 * Created by Lenovo on 2017/7/14.
 */

public class RemoteFragment extends BaseFragment implements RemoteFragmentContact.view {

    @BindView(R.id.remote_toolbar)
    Toolbar remoteToolbar;
    @BindView(R.id.remote_newmusic)
    LinearLayout remoteNewmusic;
    @BindView(R.id.remote_hotmusic)
    LinearLayout remoteHotmusic;
    @BindView(R.id.remote_chinamusic)
    LinearLayout remoteChinamusic;
    @BindView(R.id.remote_ktvmusic)
    LinearLayout remoteKtvmusic;
    Unbinder unbinder;

    @Inject
    RemoteFragmentPersenter remoteFragmentPersenter;

    @BindView(R.id.remote_newmusic_item1)
    TextView newMusicItem1;
    @BindView(R.id.remote_newmusic_item2)
    TextView newMusicItem2;
    @BindView(R.id.remote_newmusic_item3)
    TextView newMusicItem3;
    @BindView(R.id.remote_hotmusic_item1)
    TextView hotMusicItem1;
    @BindView(R.id.remote_hotmusic_item2)
    TextView hotMusicItem2;
    @BindView(R.id.remote_hotmusic_item3)
    TextView hotMusicItem3;
    @BindView(R.id.remote_chinamusic_item1)
    TextView chinaMusicItem1;
    @BindView(R.id.remote_chinamusic_item2)
    TextView chinaMusicItem2;
    @BindView(R.id.remote_chinamusic_item3)
    TextView chinaMusicItem3;
    @BindView(R.id.remote_ktvmusic_item1)
    TextView ktvMusicItem1;
    @BindView(R.id.remote_ktvmusic_item2)
    TextView ktvMusicItem2;
    @BindView(R.id.remote_ktvmusic_item3)
    TextView ktvMusicItem3;
    @BindView(R.id.remote_newmusic_image)
    ImageView newMusicImage;
    @BindView(R.id.remote_hotmusic_image)
    ImageView hotMusicImage;
    @BindView(R.id.remote_chinamusic_image)
    ImageView chinaMusicImage;
    @BindView(R.id.remote_ktvmusic_image)
    ImageView ktvMusicImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_remote, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerRemoteFragmentComponent
                .builder()
                .remoteFragmentPersenterModule(new RemoteFragmentPersenterModule(this))
                .build()
                .inject(this);

        remoteFragmentPersenter.getSongList();

        remoteToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).onBack();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.remote_newmusic, R.id.remote_hotmusic, R.id.remote_chinamusic, R.id.remote_ktvmusic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.remote_newmusic:
                ((MainActivity)getActivity()).intentToRemoteList(Final.API_NEW_MUSIC);
                break;
            case R.id.remote_hotmusic:
                ((MainActivity)getActivity()).intentToRemoteList(Final.API_HOT_MUSIC);
                break;
            case R.id.remote_chinamusic:
                ((MainActivity)getActivity()).intentToRemoteList(Final.API_CHINA_MUSIC);
                break;
            case R.id.remote_ktvmusic:
                ((MainActivity)getActivity()).intentToRemoteList(Final.API_KTV_MUSIC);
                break;
        }
    }

    /**
     * 得到网络内容的方法
     *
     * @param newMusicBean
     */
    @Override
    public void onNewMusic(NewMusicBean newMusicBean) {

        ImageMode.setImage(this, newMusicBean.getBillboard().getPic_s260(), newMusicImage);

        newMusicItem1.setText(newMusicBean.getSong_list().get(0).getTitle());
        newMusicItem2.setText(newMusicBean.getSong_list().get(1).getTitle());
        newMusicItem3.setText(newMusicBean.getSong_list().get(2).getTitle());
    }

    /**
     * 得到网络内容的方法
     *
     * @param newMusicBean
     */
    @Override
    public void onHotMusic(NewMusicBean newMusicBean) {
        ImageMode.setImage(this, newMusicBean.getBillboard().getPic_s260(), hotMusicImage);

        hotMusicItem1.setText(newMusicBean.getSong_list().get(0).getTitle());
        hotMusicItem2.setText(newMusicBean.getSong_list().get(1).getTitle());
        hotMusicItem3.setText(newMusicBean.getSong_list().get(2).getTitle());
    }

    /**
     * 得到网络内容的方法
     *
     * @param newMusicBean
     */
    @Override
    public void onChinaMusic(NewMusicBean newMusicBean) {
        ImageMode.setImage(this, newMusicBean.getBillboard().getPic_s260(), chinaMusicImage);

        chinaMusicItem1.setText(newMusicBean.getSong_list().get(0).getTitle());
        chinaMusicItem2.setText(newMusicBean.getSong_list().get(1).getTitle());
        chinaMusicItem3.setText(newMusicBean.getSong_list().get(2).getTitle());
    }

    /**
     * 得到网络内容的方法
     *
     * @param newMusicBean
     */
    @Override
    public void onKtvMusic(NewMusicBean newMusicBean) {
        ImageMode.setImage(this, newMusicBean.getBillboard().getPic_s260(), ktvMusicImage);

        ktvMusicItem1.setText(newMusicBean.getSong_list().get(0).getTitle());
        ktvMusicItem2.setText(newMusicBean.getSong_list().get(1).getTitle());
        ktvMusicItem3.setText(newMusicBean.getSong_list().get(2).getTitle());
    }
}
