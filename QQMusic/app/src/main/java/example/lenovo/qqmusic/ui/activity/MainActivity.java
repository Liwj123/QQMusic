package example.lenovo.qqmusic.ui.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.manager.INofity;
import example.lenovo.qqmusic.manager.MusicNofityManager;
import example.lenovo.qqmusic.model.MusicBean;
import example.lenovo.qqmusic.ui.Service.MusicService;
import example.lenovo.qqmusic.ui.fragment.BaseFragment;
import example.lenovo.qqmusic.ui.fragment.BottomPlayerFragment;
import example.lenovo.qqmusic.ui.fragment.LocalFragment;
import example.lenovo.qqmusic.ui.fragment.MainFragment;
import example.lenovo.qqmusic.view.FileUtil;

public class MainActivity extends BaseAvtivity implements INofity{

    MainFragment mainFragment;
    BottomPlayerFragment bottomPlayerFragment;
    @BindView(R.id.activity_main)
    DrawerLayout activityMain;
    MusicService musicService;
    private ArrayList<MusicBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list = FileUtil.getMusicFiles(this);

        //开启服务，设置通知栏
        initService();

        //填充fragment到activity
        setFragment();

        MusicNofityManager.getInstance().setiNofity(this);
    }

    /**
     * 开启服务，设置通知栏导航
     */
    private void initService() {
        Intent intent = new Intent(this, MusicService.class);
        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                musicService = ((MusicService.MyBind)iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
         };
        startService(intent);
        bindService(intent, conn, BIND_ABOVE_CLIENT);
    }

    /**
     * 操作侧滑页面的方法
     *
     * @param falg
     */
    public void drawerState(boolean falg) {
        if (falg) {
            activityMain.openDrawer(Gravity.LEFT, true);
        } else {
            activityMain.closeDrawer(Gravity.LEFT, true);
        }
    }

    /**
     * 跳转本地音乐页面的方法
     */
    public void intnetTolocal() {
        addFragment(new LocalFragment());
    }

    /**
     * 加载要跳转页面的fragment
     *
     * @param fragment
     */
    private void addFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_pager, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * 填充fragment
     */
    private void setFragment() {
        mainFragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_pager, mainFragment);
        transaction.commit();
        bottomPlayerFragment = new BottomPlayerFragment();
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();
        transaction1.add(R.id.main_player, bottomPlayerFragment);
        transaction1.commit();
    }

    /**
     * 设置返回键的操作
     */
    @Override
    public void onBackPressed() {
        boolean isOpen = activityMain.isDrawerOpen(Gravity.LEFT);
        if (isOpen) {
            drawerState(false);
            return;
        }
        super.onBackPressed();
    }

    public void play(ArrayList<MusicBean> list, int position){
        musicService.play(list,position);
        bottomPlayerFragment.setPlsyStatus(true);
    }

    public void pause(){
        musicService.pause();
    }

    public void start(){
        musicService.start();
    }

    public void next(){
        musicService.next();
    }

    @Override
    public void playIndex(int position) {
        bottomPlayerFragment.setMusicInfo(list.get(position));
    }

    @Override
    public void playStatus(boolean isPlay) {
        bottomPlayerFragment.setPlsyStatus(isPlay);
    }
}
