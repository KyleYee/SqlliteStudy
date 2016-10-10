package com.example.yunnnn.sqllitestudy;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.util.Log;

/**
 * Created by yunnnn on 2016/10/10.
 */

public class YeePermissiom extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        int result = ActivityCompat.checkSelfPermission(this, "android.permission.CAMERA");

        Log.e("LOG", result + "");

        Log.e("Log", ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA") + "");
        ActivityCompat.requestPermissions(this, new String[]{"android.permission.CAMERA"}, 1);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] == PermissionChecker.PERMISSION_DENIED_APP_OP) {
                Log.e("LOG", "用户成功授权");
            } else {
                Log.e("LOG", "用户授权失败");
            }
        }
    }
}
