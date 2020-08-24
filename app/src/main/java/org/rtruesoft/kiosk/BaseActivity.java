package org.rtruesoft.kiosk;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import androidx.appcompat.app.AppCompatActivity;



/**
 * Created by derohimat on 19/08/2016.
 */
public class BaseActivity extends AppCompatActivity {

    private final String tag = "키오스크 모드 에러";
    protected Context mContext = this;
    protected View mDecorView;
    protected DevicePolicyManager mDpm;

    protected void setUpAdmin() {
        if (!KioskModeApp.isInLockMode()) {
            ComponentName deviceAdmin = new ComponentName(this, AdminReceiver.class);
            mDpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
            if (!mDpm.isAdminActive(deviceAdmin)) {
                Log.e(tag, getString(R.string.not_device_admin));
            }
            if (mDpm.isDeviceOwnerApp(getPackageName())) {
                mDpm.setLockTaskPackages(deviceAdmin, new String[]{getPackageName()});
            } else {
                Log.e(tag, getString(R.string.not_device_owner));
            }
            enableKioskMode(true);          //앱 실행시 자동으로 키오스크 모드 활성화
            //TODO : for clear device Owner
//        } else {
//            mDpm = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
//            mDpm.clearDeviceOwnerApp(getPackageName());
        }
        mDecorView = getWindow().getDecorView();
        hideSystemUI();
    }

    protected void enableKioskMode(boolean enabled) {       //키오스크 모드
        try {
            if (enabled) {
                if (mDpm.isLockTaskPermitted(this.getPackageName())) {
                    KioskModeApp.setIsInLockMode(true);
                    startLockTask();
                } else {
                    KioskModeApp.setIsInLockMode(false);
                    Log.e(tag, getString(R.string.kiosk_not_permitted));
                }
            } else {
                KioskModeApp.setIsInLockMode(false);
                stopLockTask();
            }
        } catch (Exception e) {
            KioskModeApp.setIsInLockMode(false);
            // TODO: Log and handle appropriately
            Log.e(tag, e.getMessage());
        }
    }

    protected void hideSystemUI() {         //기본적으로 제공되는 UI 숨키기
        mDecorView = getWindow().getDecorView();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            getWindow().setDecorFitsSystemWindows(false);
            WindowInsetsController controller = mDecorView.getWindowInsetsController();
            if(controller != null) {
                controller.hide(WindowInsets.Type.statusBars() | WindowInsets.Type.navigationBars());
                controller.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        }else {
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN);
        }

    }


}