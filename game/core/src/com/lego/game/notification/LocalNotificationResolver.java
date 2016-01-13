package com.lego.game.notification;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sargis on 6/11/15.
 */
public interface LocalNotificationResolver {
    void cancelLocalNotification(String uid);

    void cancelLocalNotification(List<String> uidList);

    void schedule(Date fireDate, String title, String body);

    void schedule(Date fireDate, String title, String body, Map<String, String> userInfo);
}
