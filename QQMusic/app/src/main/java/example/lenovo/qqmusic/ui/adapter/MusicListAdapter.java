package example.lenovo.qqmusic.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.model.MusicBean;
import example.lenovo.qqmusic.view.ILocalFragmentClick;

/**
 * Created by Lenovo on 2017/7/6.
 */

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.MyViewHolder> {

    Context context;
    ArrayList<MusicBean> list;
    ILocalFragmentClick iLocalFragmentClick;

    public void setiLocalFragmentClick(ILocalFragmentClick iLocalFragmentClick) {
        this.iLocalFragmentClick = iLocalFragmentClick;
    }

    @Inject
    public MusicListAdapter(Context context, ArrayList<MusicBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_music_list_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.musicListName.setText(list.get(position).getMusic_name());
        holder.musicListSinger.setText(list.get(position).getMusic_artist());
        holder.musicListLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iLocalFragmentClick.onLocalListClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.music_list_name)
        TextView musicListName;
        @BindView(R.id.music_list_singer)
        TextView musicListSinger;
        @BindView(R.id.music_list_menu)
        ImageView musicListMenu;
        @BindView(R.id.music_list_ll)
        LinearLayout musicListLl;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
