package example.lenovo.qqmusic.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.inject.component.DaggerMainComponent;
import example.lenovo.qqmusic.inject.module.MainModule;
import example.lenovo.qqmusic.ui.fragment.BaseFragment;
import example.lenovo.qqmusic.ui.fragment.MainLiveFragment;
import example.lenovo.qqmusic.ui.fragment.MainMusicFragment;
import example.lenovo.qqmusic.ui.fragment.MainVideoFragment;

public class MainActivity extends BaseAvtivity {


    @BindView(R.id.main_tab)
    TabLayout mainTab;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewpager;
    @BindView(R.id.main_navigation)
    NavigationView mainNavigation;

    @Inject
    MainMusicFragment musicFragment;
    @Inject
    MainVideoFragment vidoeFragment;
    @Inject
    MainLiveFragment liveFragment;

    private ArrayList<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerMainComponent
                .builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);

        addFragment();

        setViewpager();

    }

    private void setViewpager() {
        // 初始化 适配器
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            //设置pager标题
            @Override
            public CharSequence getPageTitle(int position) {
                // 获取 string.xml中的 StringArray的值
                return getResources().getStringArray(R.array.fragment_list)[position];
            }
        };
        // 给viewpager设置适配器
        mainViewpager.setAdapter(fragmentPagerAdapter);
        // 设置 tablayout和viewpager联动
        mainTab.setupWithViewPager(mainViewpager);
    }

    private void addFragment(){
        fragments.add(musicFragment);
        fragments.add(vidoeFragment);
        fragments.add(liveFragment);
    }
}
