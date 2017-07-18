package example.lenovo.qqmusic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.inject.component.DaggerMainFragmentComponent;
import example.lenovo.qqmusic.inject.module.MainModule;
import example.lenovo.qqmusic.ui.activity.MainActivity;

/**
 * 主页面fragment
 * Created by Lenovo on 2017/7/5.
 */

public class MainFragment extends BaseFragment {

    @BindView(R.id.fragment_main_toolbar)
    Toolbar fragmentMainToolbar;
    @BindView(R.id.fragment_main_tab)
    TabLayout mainTab;
    @BindView(R.id.fragment_main_viewpager)
    ViewPager mainViewpager;
    Unbinder unbinder;

    @Inject
    MainMusicFragment musicFragment;
    @Inject
    MainVideoFragment vidoeFragment;
    @Inject
    MainLiveFragment liveFragment;
    @BindView(R.id.fragment_main_search)
    ImageView fragmentMainSearch;

    private ArrayList<BaseFragment> fragments = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerMainFragmentComponent
                .builder()
                .mainModule(new MainModule())
                .build()
                .inject(this);

        //添加fragment到集合中
        addFragment();

        // 设置viewpager
        setViewpager();

        //点击菜单图标实现打开侧滑布局
        openDrawer();
    }

    private void openDrawer() {
        fragmentMainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).drawerState(true);
            }
        });
    }

    private void setViewpager() {
        // 初始化 适配器
        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {
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

    private void addFragment() {
        fragments.add(musicFragment);
        fragments.add(vidoeFragment);
        fragments.add(liveFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fragment_main_search)
    public void onViewClicked() {
        ((MainActivity)getActivity()).intentToSearch();
    }
}
