package example.lenovo.qqmusic.view;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;

import example.lenovo.qqmusic.model.MusicBean;


/**
 * 歌曲文件工具类
 */

public class FileUtil {
    //获取音频文件
    public static ArrayList<MusicBean> getMusicFiles(Context context) {
        Cursor cursor = null;
        //通过内容提供者 查询 音频文件
        try {
            cursor = context.getContentResolver()
                    .query(
                            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                            , null, null, null,
                            MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //装载 查询结果 的数据
        ArrayList<MusicBean> lists = new ArrayList<MusicBean>();
        if (cursor == null)
            return lists;
        //遍历 查询结果的cursor
        while (cursor.moveToNext()) {

            int minTime = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));

            if (minTime > 60 * 1000) {

                //将 cursor里的数据放到实体类中
                MusicBean musicBean = new MusicBean();

                musicBean.setMusic_id(cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID)));
                musicBean.setMusic_name(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)));
                musicBean.setMusic_album(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)));
                musicBean.setMusic_artist(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)));
                musicBean.setMusic_file_path(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA)));
                musicBean.setMusic_duration(minTime);
                musicBean.setMusic_size(cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE)));

                //将 实体类 添加到集合中
                lists.add(musicBean);
            }
        }
        //关闭cursor
        cursor.close();
        return lists;
    }


}
