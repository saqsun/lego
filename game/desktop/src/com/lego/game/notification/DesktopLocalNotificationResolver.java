package com.lego.game.notification;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by sargis on 6/11/15.
 */
public class DesktopLocalNotificationResolver implements LocalNotificationResolver {
    @Override
    public void cancelLocalNotification(String uid) {

    }

    @Override
    public void cancelLocalNotification(List<String> uidList) {

    }

    @Override
    public void schedule(Date fireDate, String title, String body) {

    }

    @Override
    public void schedule(Date fireDate, String title, String body, Map<String, String> userInfo) {

    }
}
