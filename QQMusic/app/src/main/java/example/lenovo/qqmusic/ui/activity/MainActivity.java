package example.lenovo.qqmusic.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.ui.fragment.BaseFragment;
import example.lenovo.qqmusic.ui.fragment.BottomPlayerFragment;
import example.lenovo.qqmusic.ui.fragment.LocalFragment;
import example.lenovo.qqmusic.ui.fragment.MainFragment;

public class MainActivity extends BaseAvtivity {

    MainFragment mainFragment;
    BottomPlayerFragment bottomPlayerFragment;
    @BindView(R.id.activity_main)
    DrawerLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setFragment();
    }

    public void drawerState(boolean falg) {
        if (falg) {
            activityMain.openDrawer(Gravity.LEFT, true);
        } else {
            activityMain.closeDrawer(Gravity.LEFT, true);
        }
    }

    public void intnetTolocal() {
        addFragment(new LocalFragment());
    }

    private void addFragment(BaseFragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_pager, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

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

    @Override
    public void onBackPressed() {
        boolean isOpen = activityMain.isDrawerOpen(Gravity.LEFT);
        if (isOpen) {
            drawerState(false);
            return;
        }
        super.onBackPressed();
    }
}
