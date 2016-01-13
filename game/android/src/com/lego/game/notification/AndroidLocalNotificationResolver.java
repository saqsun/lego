package com.lego.game.notification;

import android.app.*;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sargis on 12/24/15.
 */
public class AndroidLocalNotificationResolver implements LocalNotificationResolver {
    private final Context context;
    private final Class<? extends Activity> receiver;

    public AndroidLocalNotificationResolver(Context context, Class<? extends Activity> receiver) {
        this.context = context;
        this.receiver = receiver;
    }

    @Override
    public void cancelLocalNotification(String uid) {
        // Prepare the pending intent
        Intent intent = new Intent(context, NotificationAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, Integer.parseInt(uid), intent, PendingIntent.FLAG_UPDATE_CURRENT);
        pendingIntent.cancel();
        // Retrieve alarm manager from the system
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }

    @Override
    public void cancelLocalNotification(List<String> uidList) {
        for (String uid : uidList) {
            cancelLocalNotification(uid);
        }
    }

    @Override
    public void schedule(Date fireDate, String title, String body) {
        schedule(fireDate, title, body, null);
    }

    @Override
    public void schedule(Date fireDate, String title, String body, Map<String, String> userInfo) {

        // Retrieve alarm manager from the system
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        // Every scheduled intent needs a different ID, else it is just executed once
        int id = (int) System.currentTimeMillis();

        // Prepare the intent which should be launched at the date
        Intent intent = new Intent(context, NotificationAlarm.class);
        intent.putExtra("receiver", receiver);
        intent.putExtra("title", title);
        intent.putExtra("text", body);
        if (userInfo != null) {
            id = Integer.parseInt(userInfo.get("uid"));
        }
        intent.putExtra("uid", id);
        int icNotificationId = context.getResources().getIdentifier("ic_notification", "drawable", context.getPackageName());
        intent.putExtra("icNotificationId", icNotificationId);

        // Prepare the pending intent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Register the alert in the system. You have the option to define if the device has to wake up on the alert or not
        alarmManager.set(AlarmManager.RTC_WAKEUP, fireDate.getTime(), pendingIntent);
    }

    public static class NotificationAlarm extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent paramIntent) {
            Class<?> receiver = (Class<?>) paramIntent.getSerializableExtra("receiver");
            Intent intent = new Intent(context, receiver);
            int uid = paramIntent.getIntExtra("uid", 0);
            intent.putExtra("uid", uid);
            PendingIntent activityRunnerContentIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            //
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context);

            Notification notification = notificationBuilder
                    .setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(paramIntent.getIntExtra("icNotificationId", 0))
                    .setContentTitle(paramIntent.getStringExtra("title"))
                    .setContentText(paramIntent.getStringExtra("text"))
                    .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                    .setContentIntent(activityRunnerContentIntent)
                    .build();

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(uid, notification);
        }
    }
}
