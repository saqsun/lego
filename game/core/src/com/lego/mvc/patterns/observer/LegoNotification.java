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

import com.badlogic.gdx.utils.Pool;
import com.lego.mvc.Notification;

/**
 * A base <code>LegoNotification</code> implementation.
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
 * @see LegoObserver LegoObserver
 */
public class LegoNotification implements Notification, Pool.Poolable {

    // the NAME of the notification instance
    // the type of the notification instance
    protected String name;
    protected String type;

    // the body of the notification instance
    protected Object body;

    /**
     * Get the body of the <code>LegoNotification</code> instance.
     *
     * @return the body object.
     */
    public Object getBody() {
        return body;
    }

    /**
     * Set the body of the <code>LegoNotification</code> instance.
     *
     * @param body
     */
    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * Get the NAME of the <code>LegoNotification</code> instance.
     *
     * @return the NAME of the <code>LegoNotification</code> instance.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the type of the <code>LegoNotification</code> instance.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the type of the <code>LegoNotification</code> instance.
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Get the string representation of the <code>LegoNotification</code>
     * instance.
     *
     * @return the string representation of the <code>LegoNotification</code>
     * instance.
     */
    public String toString() {
        String result = "LegoNotification Name: " + getName() + " Body:";
        if (body != null)
            result += body.toString() + " Type:";
        else
            result += "null Type:";

        if (type != null)
            result += type;
        else
            result += "null ";

        return result;
    }

    @Override
    public void reset() {
        name = null;
        type = null;
        body = null;
    }
}
