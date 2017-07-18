package example.lenovo.qqmusic.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.ui.activity.MainActivity;

/**
 * Created by Lenovo on 2017/7/13.
 */

public class SearchFragment extends BaseFragment {

    @BindView(R.id.search_back)
    ImageView searchBack;
    @BindView(R.id.search_et)
    EditText searchEt;
    @BindView(R.id.search_yes)
    ImageView searchYes;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBack.setOnClickListener(new View.OnClickListener() {
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

    @OnClick({R.id.search_back, R.id.search_yes})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_back:
                break;
            case R.id.search_yes:
                break;
        }
    }
}
