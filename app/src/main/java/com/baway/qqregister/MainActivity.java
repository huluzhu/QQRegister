package com.baway.qqregister;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    UMShareAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App app = (App) getApplication();
        api = app.getUMShareAPI();
    }

    @Override
    public void onClick(View v) {
        if (api.isInstall(this, SHARE_MEDIA.QQ)) {
            Toast.makeText(getApplicationContext(), "已经安装QQ", Toast.LENGTH_SHORT).show();
            api.doOauthVerify(this, SHARE_MEDIA.QQ, new UMAuthListener() {
                @Override
                public void onStart(SHARE_MEDIA share_media) {

                }

                @Override
                public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                    Toast.makeText(getApplicationContext(), "QQ登录成功", Toast.LENGTH_SHORT).show();
                    for (String key : map.keySet()) {

                    }
                    api.getPlatformInfo(MainActivity.this, SHARE_MEDIA.QQ, mUMAuthListener);
                }

                @Override
                public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                    Toast.makeText(getApplicationContext(), "QQ登陆错误", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onCancel(SHARE_MEDIA share_media, int i) {
                    Toast.makeText(getApplicationContext(), "用户取消登陆", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "没有安装QQ", Toast.LENGTH_SHORT).show();
        }
    }

    private UMAuthListener mUMAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
            Toast.makeText(getApplicationContext(), "获取信息成功", Toast.LENGTH_SHORT).show();
            for (String key : map.keySet()) {
                Log.e("onComplete", "onComplete:" + key + "---" + map.get(key));
            }
        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.onActivityResult(requestCode, resultCode, data);
    }
}
