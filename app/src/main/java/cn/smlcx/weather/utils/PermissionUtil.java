package cn.smlcx.weather.utils;

import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

/**
 * Created by lcx on 2017/5/15.
 */

public class PermissionUtil {

    public interface PermissionListener{
        void onRequestPermissionSuccess();
        void onRequestPermissionFail();
    }

    public static void requestPermission(RxPermissions rxPermissions,String permission,final PermissionListener permissionListener){
        Boolean isPermissionsGranted = rxPermissions.isGranted(permission);
        if(isPermissionsGranted){
            permissionListener.onRequestPermissionSuccess();
        }else{
            rxPermissions.
                    request(permission)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean aBoolean) {
                            if(aBoolean){
                                permissionListener.onRequestPermissionSuccess();
                            }else{
                                permissionListener.onRequestPermissionFail();
                            }
                        }
                    });
        }
    }

}
