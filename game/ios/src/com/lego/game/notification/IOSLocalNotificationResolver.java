package com.lego.game.notification;

import com.lego.game.localization.Localizer;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UILocalNotification;
import org.robovm.apple.uikit.UIUserNotificationSettings;
import org.robovm.apple.uikit.UIUserNotificationType;
import org.robovm.objc.Selector;

import java.util.*;

/**
 * Created by sargis on 6/11/15.
 */
public class IOSLocalNotificationResolver implements LocalNotificationResolver {
    public static final UIUserNotificationType UI_USER_NOTIFICATION_TYPE = UIUserNotificationType.with(UIUserNotificationType.Alert,
            UIUserNotificationType.Badge, UIUserNotificationType.Sound);
    public Localizer Localizer;

    @Override
    public void cancelLocalNotification(final String uid) {
        cancelLocalNotification(new ArrayList<String>() {{
            add(0, uid);
        }});
    }

    @Override
    public void cancelLocalNotification(List<String> uidList) {
        UIApplication application = UIApplication.getSharedApplication();
        NSArray<UILocalNotification> scheduledLocalNotifications = application.getScheduledLocalNotifications();
        for (UILocalNotification scheduledLocalNotification : scheduledLocalNotifications) {
            NSDictionary<?, ?> userInfo = scheduledLocalNotification.getUserInfo();
            if (userInfo == null) {
                continue;
            }
            for (String uid : uidList) {
                NSObject nsUid = userInfo.get(new NSString("uid"));
                if (nsUid != null && nsUid.toString().equals(uid)) {
                    application.cancelLocalNotification(scheduledLocalNotification);
                    break;
                }
            }

        }
    }

    @Override
    public void schedule(Date fireDate, String title, String body) {
        schedule(fireDate, title, body, null);
    }

    @Override
    public void schedule(Date fireDate, String title, String body, Map<String, String> userInfo) {
        UIApplication application = UIApplication.getSharedApplication();
        if (application.respondsToSelector(Selector.register("registerUserNotificationSettings:"))) {
            application.registerUserNotificationSettings(
                    new UIUserNotificationSettings(UI_USER_NOTIFICATION_TYPE, null));
        }
        UILocalNotification notification = new UILocalNotification();
        notification.setFireDate(new NSDate(fireDate));
        notification.setAlertTitle(title);
        notification.setAlertBody(body);
        notification.setApplicationIconBadgeNumber(1);
        notification.setAlertAction(Localizer.get("notification.local.alterAction"));
        notification.setSoundName(UILocalNotification.getDefaultSoundName());
        if (userInfo != null) {
            Map<NSObject, NSString> dictionary = new HashMap<>();
            for (String key : userInfo.keySet()) {
                dictionary.put(new NSString(key), new NSString(userInfo.get(key)));
            }
            notification.setUserInfo(new NSDictionary(dictionary));
        }
        application.scheduleLocalNotification(notification);
    }
}
