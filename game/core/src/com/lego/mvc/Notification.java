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
 * The interface definition for a PureMVC LegoNotification.
 * <p/>
 * <p/>
 * PureMVC does not rely upon underlying event models such as the one provided
 * with Flash, and ActionScript 3 does not have an inherent event model.
 * </P>
 * <p/>
 * <p/>
 * The LegoObserver Pattern as implemented within PureMVC exists to support
 * event-driven communication between the application and the actors of the MVC
 * triad.
 * </P>
 * <p/>
 * <p/>
 * Notifications are not meant to be a replacement for Events in
 * Flex/Flash/Apollo. Generally, <code>LegoMediator</code> implementors place
 * event listeners on their view components, which they then handle in the usual
 * way. This may lead to the broadcast of <code>LegoNotification</code>s to
 * trigger <code>Command</code>s or to communicate with other
 * <code>IMediators</code>. <code>LegoProxy</code> and <code>Command</code>
 * instances communicate with each other and <code>LegoMediator</code>s by
 * broadcasting <code>LegoNotification</code>s.
 * </P>
 * <p/>
 * <p/>
 * A key difference between Flash <code>Event</code>s and PureMVC
 * <code>LegoNotification</code>s is that <code>Event</code>s follow the
 * 'Chain of Responsibility' pattern, 'bubbling' up the display hierarchy until
 * some parent component handles the <code>Event</code>, while PureMVC
 * <code>LegoNotification</code>s follow a 'Publish/Subscribe' pattern. PureMVC
 * classes need not be related to each other in a parent/child relationship in
 * order to communicate with one another using <code>LegoNotification</code>s.
 *
 * @see View View
 * @see Observer LegoObserver
 */
public interface Notification {

    /**
     * Get the NAME of the <code>LegoNotification</code> instance. No setter,
     * should be set by constructor only
     *
     * @return the NAME
     */
    String getName();

    /**
     * Get the body of the <code>LegoNotification</code> instance
     *
     * @return the body
     */
    Object getBody();

    /**
     * Set the body of the <code>LegoNotification</code> instance
     *
     * @param body
     */
    void setBody(Object body);

    /**
     * Get the type of the <code>LegoNotification</code> instance
     *
     * @return the type
     */
    String getType();

    /**
     * Set the type of the <code>LegoNotification</code> instance
     *
     * @param type the type
     */
    void setType(String type);

    /**
     * Get the string representation of the <code>LegoNotification</code>
     * instance
     *
     * @return the string representation of the <code>LegoNotification</code>
     * instance
     */
    String toString();
}
