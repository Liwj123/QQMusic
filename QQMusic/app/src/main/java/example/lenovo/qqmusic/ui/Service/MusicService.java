package example.lenovo.qqmusic.ui.Service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.ArrayList;

import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.manager.MusicNofityManager;
import example.lenovo.qqmusic.model.BaseMusicBean;
import example.lenovo.qqmusic.model.MusicBean;
import example.lenovo.qqmusic.model.RemoteMusicBean;
import example.lenovo.qqmusic.ui.activity.MainActivity;

public class MusicService extends Service {

    private static final String PLAY = "play";
    private static final String NEXT = "next";
    private static final String BACK = "back";
    private static final String ACTION = "com.music.MyReceiver";
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private int musicTime;
    private ArrayList<BaseMusicBean> list = new ArrayList<>();
    private RemoteViews remoteViews;
    private int position;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBind();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        //动态注册广播接收者
        initReceiver();

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                next();
            }
        });

        //设置前台服务的内容
        setRemoteViews();
    }

    /**
     * 动态注册广播接收者
     */
    private void initReceiver() {
        MusicReceiver musicReceiver = new MusicReceiver();
        IntentFilter intentFilter = new IntentFilter(ACTION);
        registerReceiver(musicReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 设置前台服务的内容
     */
    private void setRemoteViews() {
        remoteViews = new RemoteViews(getPackageName(), R.layout.notification);

        //设置点击播放/暂停的意图
        Intent playIntent = new Intent();
        playIntent.setAction(ACTION);
        playIntent.putExtra("menu", PLAY);
        PendingIntent playPendingIntent = PendingIntent.getBroadcast(this, 101, playIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.notification_play, playPendingIntent);

        //设置点击下一曲的意图
        Intent nextIntent = new Intent();
        nextIntent.setAction(ACTION);
        nextIntent.putExtra("menu", NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getBroadcast(this, 102, nextIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.notification_next, nextPendingIntent);

        //设置点击退出的意图
        Intent backIntent = new Intent();
        backIntent.setAction(ACTION);
        backIntent.putExtra("menu", BACK);
        PendingIntent backPendingIntent = PendingIntent.getBroadcast(this, 103, backIntent, 0);
        remoteViews.setOnClickPendingIntent(R.id.notification_back, backPendingIntent);

        setNotification();
    }

    /**
     * 将设置到的内容添加到前台服务中
     */
    private void setNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notification = new Notification.Builder(this)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(remoteViews)
                .build();
        startForeground(1, notification);

        MusicNofityManager
                .getInstance()
                .getiNofity()
                .playStatus(mediaPlayer.isPlaying());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayer.isPlaying()) {
            MusicNofityManager
                    .getInstance()
                    .getiNofity()
                    .playIndex(list.get(position));
            MusicNofityManager
                    .getInstance()
                    .getiNofity()
                    .playStatus(true);

        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    /**
     * 内部类，返回一个服务的对象
     */
    public class MyBind extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }

    /**
     * 播放方法
     *
     * @param list
     * @param position
     */
    public void play(ArrayList<BaseMusicBean> list, int position) {
        this.list = list;
        this.position = position;
        BaseMusicBean musicBean = list.get(position);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.reset();
        }
        mediaPlayer.reset();

        if(musicBean instanceof MusicBean){
            try {
                mediaPlayer.setDataSource(((MusicBean)musicBean).getMusic_file_path());
                remoteViews.setTextViewText(R.id.notification_name, ((MusicBean)musicBean).getMusic_name());
                remoteViews.setTextViewText(R.id.notification_singer, ((MusicBean)musicBean).getMusic_artist());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if (musicBean instanceof RemoteMusicBean){
            try {
                mediaPlayer.setDataSource(((RemoteMusicBean)musicBean).getBitrate().getFile_link());
                remoteViews.setTextViewText(R.id.notification_name, ((RemoteMusicBean)musicBean).getSonginfo().getTitle());
                remoteViews.setTextViewText(R.id.notification_singer, ((RemoteMusicBean)musicBean).getSonginfo().getAuthor());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
                MusicNofityManager
                        .getInstance()
                        .getiNofity()
                        .playStatus(true);
            }
        });

        remoteViews.setImageViewResource(R.id.notification_play, android.R.drawable.ic_media_pause);
        setNotification();

        MusicNofityManager
                .getInstance()
                .getiNofity()
                .playIndex(list.get(position));

    }

    /**
     * 暂停方法
     *
     * @return
     */
    public void pause() {
        mediaPlayer.pause();
        musicTime = mediaPlayer.getCurrentPosition();
        remoteViews.setImageViewResource(R.id.notification_play, android.R.drawable.ic_media_play);
        setNotification();
    }

    /**
     * 暂停后，继续播放
     *
     * @return
     */
    public void start() {
        mediaPlayer.seekTo(musicTime);
        mediaPlayer.start();
        remoteViews.setImageViewResource(R.id.notification_play, android.R.drawable.ic_media_pause);
        setNotification();
    }

    /**
     * 停止播放
     */
    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.seekTo(0);
        }
    }

    /**
     * 播放下一首
     */
    public void next() {
        if (position == list.size() - 1) {
            play(list, 0);
        } else {
            play(list, position + 1);
        }
    }

    /**
     * 服务中的内部广播接收者
     */
    public class MusicReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getStringExtra("menu")) {
                case PLAY:
                    if (mediaPlayer.isPlaying()) {
                        pause();
                    } else {
                        start();
                    }
                    break;
                case NEXT:
                    next();
                    break;
                case BACK:
                    stop();//停止播放
                    stopForeground(false);//取消前台服务 （调用此方法后通知还在）
                    stopSelf();//结束服务
                    //结束进程
                    android.os
                            .Process
                            .killProcess(android.os.Process.myPid());
                    break;
            }
        }
    }
}
