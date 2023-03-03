package com.example.lib_track;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author gaolei46@ztoky.cn
 * 2023/2/28 16:40
 */
public class KyPageLifecycleManager {

    private static class HOLDER{
        private static KyPageLifecycleManager instance = new KyPageLifecycleManager();
    }

    public static KyPageLifecycleManager getInstance(){
        return HOLDER.instance;
    }

    public void startPageListener(Context context) {

        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                KyCatManager.getInstance().enterPage(activity);
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                KyCatManager.getInstance().leavePage(activity);
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
            }
        });
    }
}
