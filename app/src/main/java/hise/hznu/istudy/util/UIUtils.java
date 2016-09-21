package hise.hznu.istudy.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

import hise.hznu.istudy.app.AppConfig;

/**
 * @author Jacky
 */
public class UIUtils {

    private static final Handler mainHandler = new Handler(Looper.getMainLooper());

    public static synchronized void sendBroadcast(String action) {
        sendBroadcast(new Intent(action));
    }

    public static synchronized void sendBroadcast(Intent intent) {
        AppConfig.getContext().sendBroadcast(intent);
    }

    /**
     * 通过此方法得到的ProgressDialog需要在activity中显示的dismiss掉，比方说在onStop里面。
     */
    public static ProgressDialog showWaiting(final Activity activity, final String message) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }

        if (onUiThread()) {
            return createWaitingAndShow(activity, message);
        } else {
            return (ProgressDialog) showDialogFromBackgroundThread(activity,
                    new Callable<Dialog>() {
                        @Override
                        public Dialog call() throws Exception {
                            return createWaitingAndShow(activity, message);
                        }
                    });
        }
    }

    private static ProgressDialog createWaitingAndShow(Activity activity, String message) {
        ProgressDialog pd = new ProgressDialog(activity);
        pd.setCancelable(false);
        pd.setMessage(message);
        pd.show();
        return pd;
    }

    /**
     * 通过此方法得到的AlertDialog需要在activity中显示的dismiss掉，比方说在onStop里面。
     */
    public static AlertDialog showDialog(final Activity activity, final String title,
                                         final String message) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }

        if (onUiThread()) {
            return createDialogAndShow(activity, title, message);
        } else {
            return (AlertDialog) showDialogFromBackgroundThread(activity, new Callable<Dialog>() {
                @Override
                public Dialog call() throws Exception {
                    return createDialogAndShow(activity, title, message);
                }
            });
        }
    }

    private static AlertDialog createDialogAndShow(Activity activity, String title,
                                                   String message) {
        AlertDialog.Builder builder = new Builder(activity);
        builder.setTitle(title).setMessage(message);
        builder.setPositiveButton("确定", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    /**
     * 通过此方法得到的AlertDialog需要在activity中显示的dismiss掉，比方说在onStop里面。
     */
    public static AlertDialog showConfirm(final Activity activity, final String message,
                                          final OnClickListener okListener, final OnClickListener cancelListener) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }

        if (onUiThread()) {
            return createConfirmAndShow(activity, message, okListener, cancelListener);
        } else {
            return (AlertDialog) showDialogFromBackgroundThread(activity, new Callable<Dialog>() {
                @Override
                public Dialog call() throws Exception {
                    return createConfirmAndShow(activity, message, okListener, cancelListener);
                }
            });
        }
    }

    private static AlertDialog createConfirmAndShow(Activity activity, String message,
                                                    OnClickListener okListener, OnClickListener cancelListener) {
        AlertDialog.Builder builder = new Builder(activity);
        builder.setTitle("确认").setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("确定", okListener);
        builder.setNegativeButton("取消", cancelListener);
        AlertDialog dialog = builder.create();
        dialog.show();
        return dialog;
    }

    /**
     * 通过异步转同步的方法显示对话框，并且返回对话框的引用，以方便关闭。
     */
    private static Dialog showDialogFromBackgroundThread(final Activity activity,
                                                         final Callable<Dialog> callable) {
        final Dialog[] dialog = new Dialog[1];
        final CountDownLatch latch = new CountDownLatch(1);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    dialog[0] = callable.call();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dialog[0];
    }

    public static boolean onUiThread() {
        return Thread.currentThread().equals(Looper.getMainLooper().getThread());
    }

    public static void showToast(String message) {
        showToast(AppConfig.getContext(), message);
    }

    public static void showToast(final Context context, final String message) {
        mainHandler.post(new Runnable() {

            public void run() {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
