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

import com.lego.mvc.Notification;
import com.lego.mvc.Observer;
import com.lego.mvc.Runnable;

/**
 * A base <code>LegoObserver</code> implementation.
 * <p/>
 * <p/>
 * An <code>LegoObserver</code> is an object that encapsulates information about
 * an interested object with a method that should be called when a particular
 * <code>LegoNotification</code> is broadcast.
 * </P>
 * <p/>
 * <p/>
 * In PureMVC, the <code>LegoObserver</code> class assumes these responsibilities:
 * <UL>
 * <LI>Encapsulate the notification (callback) method of the interested object.</LI>
 * <LI>Encapsulate the notification context (this) of the interested object.</LI>
 * <LI>Provide methods for setting the notification method and context.</LI>
 * <LI>Provide a method for notifying the interested object.</LI>
 * </UL>
 *
 * @see LegoNotification LegoNotification
 */
public class LegoObserver implements Observer {

    private Object context;

    private Runnable notify;

    /**
     * Constructor.
     * <p/>
     * <p/>
     * The notification method on the interested object should take one
     * parameter of type <code>LegoNotification</code>
     * </P>
     *
     * @param notify  the notification method of the interested object
     * @param context the notification context of the interested object
     */
    public LegoObserver(Runnable notify, Object context) {
        setNotifyContext(context);
        setNotifyMethod(notify);
    }

    /**
     * Compare an object to the notification context.
     *
     * @param object the object to compare
     * @return boolean indicating if the object and the notification context are
     * the same
     */
    public boolean compareNotifyContext(Object object) {
        return context == object;
    }


    /**
     * Notify the interested object.
     *
     * @param notification the <code>LegoNotification</code> to pass to the interested
     *                     object's notification method.
     */
    public void notifyObserver(Notification notification) {
        getNotifyMethod().run(notification);
    }

    /**
     * Get the notification method.
     *
     * @return the notification (callback) method of the interested object.
     */
    public Runnable getNotifyMethod() {
        return notify;
    }

    /**
     * Set the notification method.
     * <p/>
     * <p/>
     * The notification method should take one parameter of type
     * <code>LegoNotification</code>.
     * </P>
     *
     * @param notifyMethod the notification (callback) method of the interested object.
     */
    public void setNotifyMethod(Runnable notifyMethod) {
        notify = notifyMethod;
    }

    /**
     * Get the notification context.
     *
     * @return the notification context (<code>this</code>) of the
     * interested object.
     */
    public Object getNotifyContext() {
        return context;
    }

    /**
     * Set the notification context.
     *
     * @param notifyContext the notification context (this) of the interested object.
     */
    public void setNotifyContext(Object notifyContext) {
        context = notifyContext;
    }

}
