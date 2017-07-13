package example.lenovo.qqmusic.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import example.lenovo.qqmusic.R;
import example.lenovo.qqmusic.inject.component.DaggerSplashComponent;
import example.lenovo.qqmusic.inject.module.SplashModule;
import example.lenovo.qqmusic.presenter.SplashContact;
import example.lenovo.qqmusic.presenter.impl.SplashPersenter;

/**
 * 启动页
 * Created by Lenovo on 2017/6/28.
 */

public class SplashActivity extends BaseAvtivity implements SplashContact.View{

    @Inject
    SplashContact.Presenter iSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏通知栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        DaggerSplashComponent
                .builder()
                .splashModule(new SplashModule(this))
                .build()
                .inject(this);

        //开始计时
        iSplashPresenter.timer();
    }

    @OnClick(R.id.splash_iv)
    public void onViewClicked() {
        iSplashPresenter.startMain();
    }

    /**
     * 跳转方法
     */
    @Override
    public void intentToMain() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
