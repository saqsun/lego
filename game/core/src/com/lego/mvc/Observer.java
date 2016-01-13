/*
 PureMVC Java port by Frederic Saunier <frederic.saunier@puremvc.org>
 
 Adapted from sources of thoses different authors :
 	Donald Stinchfield <donald.stinchfield@puremvc.org>, et all
 	Ima OpenSource <opensource@ima.eu>
 	Anthony Quinault <anthony.quinault@puremvc.org>
 
 PureMVC - Copyright(c) 2006-10 Futurescale, Inc., Some rights reserved. 
 Your reuse is governed by the Creative Commons Attribution 3.0 License
*/
package com.lego.mvc;

/**
 * The interface definition for a PureMVC LegoObserver.
 * <p/>
 * <p/>
 * In PureMVC, <code>LegoObserver</code> implementors assume these
 * responsibilities:
 * <UL>
 * <LI>Encapsulate the notification (callback) method of the interested object.</LI>
 * <LI>Encapsulate the notification context (this) of the interested object.</LI>
 * <LI>Provide methods for setting the interested object' notification method
 * and context.</LI>
 * <LI>Provide a method for notifying the interested object.</LI>
 * </UL>
 * <p/>
 * <p/>
 * PureMVC does not rely upon underlying event models such as the one provided
 * with Flash, and ActionScript 3 does not have an inherent event model.
 * </P>
 * <p/>
 * <p/>
 * The LegoObserver Pattern as implemented within PureMVC exists to support event
 * driven communication between the application and the actors of the MVC triad.
 * </P>
 * <p/>
 * <p/>
 * An LegoObserver is an object that encapsulates information about an interested
 * object with a notification method that should be called when an </code>LegoNotification</code>
 * is broadcast. The LegoObserver then acts as a proxy for notifying the interested
 * object.
 * <p/>
 * <p/>
 * Observers can receive <code>LegoNotification</code>s by having their <code>notifyObserver</code>
 * method invoked, passing in an object implementing the <code>LegoNotification</code>
 * interface, such as a subclass of <code>LegoNotification</code>.
 * </P>
 *
 * @see View View
 * @see Notification LegoNotification
 */
public interface Observer {

    /**
     * Set the notification method.
     * <p/>
     * <p/>
     * The notification method should take one parameter of type
     * <code>LegoNotification</code>
     * </P>
     *
     * @param notifyMethod the notification (callback) method of the interested object
     */
    void setNotifyMethod(Runnable notifyMethod);

    /**
     * Set the notification context.
     *
     * @param notifyContext the notification context (this) of the interested object
     */
    void setNotifyContext(Object notifyContext);

    /**
     * Notify the interested object.
     *
     * @param notification the <code>LegoNotification</code> to pass to the interested
     *                     object's notification method
     */
    void notifyObserver(Notification notification);

    /**
     * Compare the given object to the notificaiton context object.
     *
     * @param object the object to compare.
     * @return boolean indicating if the notification context and the object are
     * the same.
     */
    boolean compareNotifyContext(Object object);
}
