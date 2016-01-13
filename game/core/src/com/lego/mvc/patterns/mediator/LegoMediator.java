/*
 PureMVC Java port by Frederic Saunier <frederic.saunier@puremvc.org>
 
 Adapted from sources of thoses different authors :
 	Donald Stinchfield <donald.stinchfield@puremvc.org>, et all
 	Ima OpenSource <opensource@ima.eu>
 	Anthony Quinault <anthony.quinault@puremvc.org>
 
 PureMVC - Copyright(c) 2006-10 Futurescale, Inc., Some rights reserved. 
 Your reuse is governed by the Creative Commons Attribution 3.0 License
*/
package com.lego.mvc.patterns.mediator;

import com.lego.mvc.Mediator;
import com.lego.mvc.Notification;
import com.lego.mvc.Notifier;
import com.lego.mvc.patterns.observer.LegoNotifier;

/**
 * A base <code>LegoMediator</code> implementation.
 */
public abstract class LegoMediator<T> extends LegoNotifier implements Mediator<T>, Notifier {


    /**
     * The NAME of the <code>LegoMediator</code>.
     */
    protected String mediatorName;

    /**
     * The view component
     */
    protected T viewComponent;

    /**
     * Constructor.
     *
     * @param mediatorName
     * @param viewComponent
     */
    public LegoMediator(String mediatorName, T viewComponent) {
        this.mediatorName = mediatorName;
        this.viewComponent = viewComponent;
    }

    /**
     * Get the NAME of the <code>LegoMediator</code>.
     *
     * @return the NAME
     */
    public final String getMediatorName() {
        return mediatorName;
    }

    /**
     * Get the <code>LegoMediator</code>'s view component.
     * <p/>
     * <p/>
     * Additionally, an implicit getter will usually be defined in the subclass
     * that casts the view object to a type, like this:
     * </P>
     * <p/>
     * <listing> private function get comboBox : mx.controls.ComboBox { return
     * viewComponent as mx.controls.ComboBox; } </listing>
     *
     * @return the view component
     */
    public T getViewComponent() {
        return viewComponent;
    }

    /**
     * Set the <code>LegoMediator</code>'s view component.
     *
     * @param viewComponent The view component
     */
    public void setViewComponent(T viewComponent) {
        this.viewComponent = viewComponent;
    }

    /**
     * Handle <code>LegoNotification</code>s.
     * <p/>
     * <p/>
     * Typically this will be handled in a switch statement, with one 'case'
     * entry per <code>LegoNotification</code> the <code>LegoMediator</code> is
     * interested in.
     *
     * @param notification
     */
    public void handleNotification(Notification notification) {
    }

    /**
     * List the <code>LegoNotification</code> names this <code>LegoMediator</code>
     * is interested in being notified of.
     *
     * @return String[] the list of <code>LegoNotification</code> names
     */
    public String[] listNotificationInterests() {
        return new String[]{};
    }

    /**
     * Called by the View when the LegoMediator is registered
     */
    public void onRegister() {
    }

    /**
     * Called by the View when the LegoMediator is removed
     */
    public void onRemove() {
    }
}
