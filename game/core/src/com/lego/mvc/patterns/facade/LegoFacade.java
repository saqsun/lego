/*
 PureMVC Java port by Frederic Saunier <frederic.saunier@puremvc.org>
 
 Adapted from sources of thoses different authors :
 	Donald Stinchfield <donald.stinchfield@puremvc.org>, et all
 	Ima OpenSource <opensource@ima.eu>
 	Anthony Quinault <anthony.quinault@puremvc.org>
 
 PureMVC - Copyright(c) 2006-10 Futurescale, Inc., Some rights reserved. 
 Your reuse is governed by the Creative Commons Attribution 3.0 License
*/
package com.lego.mvc.patterns.facade;


import com.badlogic.gdx.utils.Pools;
import com.google.inject.Inject;
import com.lego.mvc.*;
import com.lego.mvc.patterns.observer.LegoNotification;

/**
 * A base Singleton <code>LegoFacade</code> implementation.
 *
 */
public class LegoFacade implements Facade {

    /**
     * Reference to the LegoController
     */
    protected Controller controller;

    /**
     * Reference to the LegoModel
     */
    protected Model model;

    /**
     * Reference to the View
     */
    protected View view;

    @Inject
    public LegoFacade(Controller controller, Model model, View view) {
        this.controller = controller;
        this.model = model;
        this.view = view;
    }

    /**
     * Register an <code>Command</code> with the <code>LegoController</code> by
     * LegoNotification NAME.
     *
     * @param noteName the NAME of the <code>LegoNotification</code> to associate the
     *                 <code>Command</code> with
     * @param command  an instance of the <code>Command</code>
     */
    public void registerCommand(String noteName, Command command) {
        controller.registerCommand(noteName, command);
    }

    /**
     * Remove a previously registered <code>Command</code> to <code>LegoNotification</code> mapping from the LegoController.
     *
     * @param notificationName the NAME of the <code>LegoNotification</code> to remove the <code>Command</code> mapping for
     */
    public void removeCommand(String notificationName) {
        this.controller.removeCommand(notificationName);
    }

    /**
     * Check if a Command is registered for a given LegoNotification
     *
     * @param notificationName
     * @return whether a Command is currently registered for the given <code>notificationName</code>.
     */
    public boolean hasCommand(String notificationName) {
        return controller.hasCommand(notificationName);
    }

    /**
     * Register a <code>LegoMediator</code> with the <code>View</code>.
     *
     * @param mediator the NAME to associate with this <code>LegoMediator</code>
     */
    public void registerMediator(Mediator mediator) {
        if (this.view != null) {
            this.view.registerMediator(mediator);
        }
    }

    /**
     * Register an <code>LegoProxy</code> with the <code>LegoModel</code> by NAME.
     *
     * @param proxy the NAME of the <code>LegoProxy</code> instance to be
     *              registered with the <code>LegoModel</code>.
     */
    public void registerProxy(Proxy proxy) {
        this.model.registerProxy(proxy);
    }

    /**
     * Remove an <code>LegoMediator</code> from the <code>View</code>.
     *
     * @param mediatorName NAME of the <code>LegoMediator</code> to be removed.
     * @return the <code>LegoMediator</code> that was removed from the <code>View</code>
     */
    public Mediator removeMediator(String mediatorName) {
        if (this.view != null) {
            return this.view.removeMediator(mediatorName);
        }
        return null;
    }

    /**
     * Remove an <code>LegoProxy</code> from the <code>LegoModel</code> by NAME.
     *
     * @param proxyName the <code>LegoProxy</code> to remove from the
     *                  <code>LegoModel</code>.
     * @return the <code>LegoProxy</code> that was removed from the <code>LegoModel</code>
     */
    public Proxy removeProxy(String proxyName) {
        if (this.model != null) {
            return this.model.removeProxy(proxyName);
        }
        return null;
    }

    /**
     * Check if a LegoProxy is registered
     *
     * @param proxyName
     * @return whether a LegoProxy is currently registered with the given <code>proxyName</code>.
     */
    public boolean hasProxy(String proxyName) {
        return model.hasProxy(proxyName);
    }


    /**
     * Check if a LegoMediator is registered or not
     *
     * @param mediatorName
     * @return whether a LegoMediator is registered with the given <code>mediatorName</code>.
     */
    public boolean hasMediator(String mediatorName) {
        return view.hasMediator(mediatorName);
    }

    /**
     * Retrieve an <code>LegoMediator</code> from the <code>View</code>.
     *
     * @param mediatorName
     * @return the <code>LegoMediator</code> previously registered with the given
     * <code>mediatorName</code>.
     */
    public Mediator retrieveMediator(String mediatorName) {
        return this.view.retrieveMediator(mediatorName);
    }

    /**
     * Retrieve an <code>LegoProxy</code> from the <code>LegoModel</code> by NAME.
     *
     * @param proxyName the NAME of the proxy to be retrieved.
     * @return the <code>LegoProxy</code> instance previously registered with the
     * given <code>proxyName</code>.
     */
    public Proxy retrieveProxy(String proxyName) {
        return this.model.retrieveProxy(proxyName);
    }

    /**
     * Create and send an <code>LegoNotification</code>.
     * <p/>
     * <p/>
     * Keeps us from having to construct new notification
     * instances in our implementation code.
     *
     * @param notificationName the NAME of the notification to send
     * @param body             the body of the notification (optional)
     * @param type             the type of the notification (optional)
     */
    public void sendNotification(String notificationName, Object body, String type) {
        LegoNotification notification = Pools.obtain(LegoNotification.class);
        notification.setName(notificationName);
        notification.setBody(body);
        notification.setType(type);
        notifyObservers(notification);
        Pools.free(notification);
    }

    /**
     * Create and send an <code>LegoNotification</code>.
     * <p/>
     * <p/>
     * Keeps us from having to construct new notification
     * instances in our implementation code.
     *
     * @param notificationName the NAME of the notification to send
     * @param body             the body of the notification (optional)
     */
    public void sendNotification(String notificationName, Object body) {
        sendNotification(notificationName, body, null);
    }

    /**
     * Create and send an <code>LegoNotification</code>.
     * <p/>
     * <p/>
     * Keeps us from having to construct new notification
     * instances in our implementation code.
     *
     * @param notificationName the NAME of the notification to send
     */
    public void sendNotification(String notificationName) {
        sendNotification(notificationName, null, null);
    }

    /**
     * Notify <code>LegoObserver</code>s of an <code>LegoNotification</code>.
     *
     * @param note the <code>LegoNotification</code> to have the <code>View</code>
     *             notify observers of.
     */
    public void notifyObservers(Notification note) {
        if (view != null)
            view.notifyObservers(note);
    }
}
