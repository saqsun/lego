/*
 PureMVC Java port by Frederic Saunier <frederic.saunier@puremvc.org>
 
 Adapted from sources of thoses different authors :
 	Donald Stinchfield <donald.stinchfield@puremvc.org>, et all
 	Ima OpenSource <opensource@ima.eu>
 	Anthony Quinault <anthony.quinault@puremvc.org>
 
 PureMVC - Copyright(c) 2006-10 Futurescale, Inc., Some rights reserved. 
 Your reuse is governed by the Creative Commons Attribution 3.0 License
*/
package com.lego.mvc.patterns.observer;

import com.google.inject.Inject;
import com.lego.mvc.Facade;
import com.lego.mvc.Notifier;

/**
 * A Base <code>LegoNotifier</code> implementation.
 * <p/>
 * <p/>
 * <code>LegoCommandContainer, Command, LegoMediator</code> and <code>LegoProxy</code> all
 * have a need to send <code>Notifications</code>.
 * <p/>
 * <p/>
 * The <code>LegoNotifier</code> interface provides a common method called
 * <code>sendNotification</code> that relieves implementation code of the
 * necessity to actually construct <code>Notifications</code>.
 * </P>
 * <p/>
 * <p/>
 * The <code>LegoNotifier</code> class, which all of the above mentioned classes
 * extend, provides an initialized reference to the <code>LegoFacade</code>
 * Singleton, which is required for the convienience method for sending
 * <code>Notifications</code>, but also eases implementation as these classes
 * have frequent <code>LegoFacade</code> interactions and usually require access
 * to the facade anyway.
 * </P>
 */
public class LegoNotifier implements Notifier {
    // The Multiton Key for this app
    /**
     * Local reference to the LegoFacade Singleton
     */
    @Inject
    public Facade facade;

    /**
     * Send an <code>LegoNotification</code>s.
     * <p/>
     * <p/>
     * Keeps us from having to construct new notification instances in our
     * implementation code.
     *
     * @param notificationName the NAME of the notiification to send
     * @param body             the body of the notification (optional)
     * @param type             the type of the notification (optional)
     */

    public void sendNotification(String notificationName, Object body, String type) {
        facade.sendNotification(notificationName, body, type);
    }

    /**
     * Send an <code>LegoNotification</code>s.
     * <p/>
     * <p/>
     * Keeps us from having to construct new notification instances in our
     * implementation code.
     *
     * @param notificationName the NAME of the notiification to send
     * @param body             the body of the notification (optional)
     */

    public void sendNotification(String notificationName, Object body) {
        sendNotification(notificationName, body, null);
    }

    /**
     * Send an <code>LegoNotification</code>s.
     * <p/>
     * <p/>
     * Keeps us from having to construct new notification instances in our
     * implementation code.
     *
     * @param notificationName the NAME of the notiification to send
     */

    public void sendNotification(String notificationName) {
        sendNotification(notificationName, null, null);
    }

}
