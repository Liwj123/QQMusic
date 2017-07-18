package example.lenovo.qqmusic.view;

import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import example.lenovo.qqmusic.ui.activity.BaseAvtivity;
import example.lenovo.qqmusic.ui.fragment.BaseFragment;

/**
 * Created by Lenovo on 2017/7/14.
 */

public class ImageMode {

    public static void setImage(BaseFragment fragment, String path, ImageView view){
        Glide.with(fragment)
                .load(path)
                .into(view);
    }

    public static void setImage(BaseAvtivity activity, String path, ImageView view){
        Glide.with(activity)
                .load(path)
                .into(view);
    }
}
