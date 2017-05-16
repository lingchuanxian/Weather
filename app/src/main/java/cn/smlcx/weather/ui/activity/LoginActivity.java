package cn.smlcx.weather.ui.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.OnClick;
import cn.smlcx.weather.Base.BaseActivity;
import cn.smlcx.weather.R;
import cn.smlcx.weather.utils.ToastUtil;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Administrator on 2017/5/13.
 */

@RuntimePermissions
public class LoginActivity extends BaseActivity {
    protected final String TAG = this.getClass().getSimpleName();
    @BindView(R.id.userName)
    EditText mUserName;
    @BindView(R.id.passWord)
    EditText mPassWord;
    @BindView(R.id.login)
    Button mLogin;
    @BindView(R.id.msg)
    TextView mMsg;
    ProgressDialog pd;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @OnClick(R.id.login)
    void click(View view) {
        if (mUserName.getText().toString().equals("")) {
            ToastUtil.show(mContext, "请输入用户名");
            return;
        } else if (mPassWord.getText().toString().equals("")) {
            ToastUtil.show(mContext, "请输入密码");
            return;
        } else {
            pd = ProgressDialog.show(this, "温馨提示", "正在登录中...",
                    false, true);
            EMClient.getInstance().login(mUserName.getText().toString(), mPassWord.getText().toString(), new EMCallBack() {//回调
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    pd.dismiss();
                    finish();
                }

                @Override
                public void onProgress(int progress, String status) {
                }

                @Override
                public void onError(int code, String message) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            mMsg.setText("登录聊天服务器失败！");
                            pd.dismiss();
                        }
                    });
                }
            });
        }
    }

    @Override
    protected void initViews() {
        LoginActivityPermissionsDispatcher.needPermissionWithCheck(this);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initInjector() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LoginActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    void needPermission() {
        Log.i(TAG, "showRationale: need");
    }

    @OnShowRationale(Manifest.permission.ACCESS_FINE_LOCATION)
    void showRationale(final PermissionRequest request) {
        ToastUtil.show(this,"先试试");
        Log.i(TAG, "showRationale: 显示");
        /*new AlertDialog.Builder(this)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       *//* request.proceed();*//*
                    }
                })
                .setNegativeButton("不给", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("获取天气信息需要获取定位权限，应用将要申请定位权限")
                .show();*/
    }

    @OnPermissionDenied(Manifest.permission.ACCESS_FINE_LOCATION)
    void permissionDenied() {
        ToastUtil.show(this,"拒绝了权限的申请");
        Log.e(TAG, "permissionDenied: 拒绝了权限的申请" );
    }

    @OnNeverAskAgain(Manifest.permission.ACCESS_FINE_LOCATION)
    void neverAskAgagin() {
        new AlertDialog.Builder(this)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage("您已经禁止了定位权限,是否现在去开启")
                .show();
    }
}
