package example.lenovo.qqmusic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.inject.component.DaggerLocalFragmentComponent;
import example.lenovo.qqmusic.inject.module.LocalFragmentPersenterModule;
import example.lenovo.qqmusic.model.MusicBean;
import example.lenovo.qqmusic.presenter.LocalFragmentContact;
import example.lenovo.qqmusic.presenter.impl.LocalFragmentPersenter;
import example.lenovo.qqmusic.ui.adapter.MusicListAdapter;

/**
 * Created by Lenovo on 2017/7/6.
 */

public class LocalFragment extends BaseFragment implements LocalFragmentContact.view {

    @BindView(R.id.local_toolbar)
    Toolbar localToolbar;
    @BindView(R.id.local_recycler)
    RecyclerView localRecycler;
    Unbinder unbinder;

    ArrayList<MusicBean> list = new ArrayList<>();

    @Inject
    LocalFragmentPersenter localFragmentPersenter;
    MusicListAdapter musicListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DaggerLocalFragmentComponent
                .builder()
                .localFragmentPersenterModule(new LocalFragmentPersenterModule(getContext(), this))
                .build()
                .inject(this);

        localFragmentPersenter.getLocalMusic();

    }

    private void setAdapter() {
        musicListAdapter = new MusicListAdapter(getContext(), list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        localRecycler.setLayoutManager(llm);
        localRecycler.setAdapter(musicListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void setLocalMusic(ArrayList<MusicBean> list) {
        setAdapter();
        this.list.clear();
        this.list.addAll(list);
        musicListAdapter.notifyDataSetChanged();
    }
}
