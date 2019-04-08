package com.zbc.tinkerdemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.zbc.tinkerdemo.manager.TinkerManager;
import com.zbc.tinkerdemo.util.FileUtils;
import com.ztsc.commonutils.toast.ToastUtils;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    @Bind(R.id.tv_load_patch)
    TextView tvLoadPatch;
    @Bind(R.id.tv_next)
    TextView tvNext;
    private File mPatch;

    private final String FILE_END = ".apk";
    private final int RC_LOCATION_CONTACTS_PERM = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadFile();
        requestPermissions();
        findViewById(R.id.tv_next4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastShort("我增加测试按钮4，你竟然点击了");
            }
        });
        findViewById(R.id.im_im).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToastShort("点击了呀");
            }
        });
        ((ImageView) (findViewById(R.id.im_im))).setImageResource(R.mipmap.test_img);
    }

    private void loadFile() {
        mPatch = new File(FileUtils.getPatchDownPath() + "/ztsc_patch.apk");
    }

    @OnClick({R.id.tv_load_patch,R.id.tv_next, R.id.tv_next2, R.id.tv_next3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_load_patch:
                loadPatch();
                ToastUtils.showToastShort(mPatch.exists() ? "apk文件已更新请重启后APP" : "未找到安装包文件");
                break;
            case R.id.tv_next:
                TinkerManager.cleanPatch(this);
                ToastUtils.showToastShort("增量更新已清除");
                break;
            case R.id.tv_next2:
//                TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), mPatch.getAbsolutePath());
                ToastUtils.showToastShort(mPatch.exists() ? "安装文件存在可以安装" : "未找到安装包文件");
                break;
            case R.id.tv_next3:
                startActivity(new Intent(this, DemoTestActivity.class));
                break;

            default:
        }
    }

    public void loadPatch() {
        TinkerManager.loadPatch(mPatch.getAbsolutePath());
    }


    private void requestPermissions() {
        //acitivty中申请权限
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_LOCATION_CONTACTS_PERM);
    }


    //activity权限授权结果回调

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
