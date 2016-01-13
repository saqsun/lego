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
 * The interface definition for a PureMVC LegoFacade.
 * <p/>
 * <p/>
 * The LegoFacade Pattern suggests providing a single class to act as a central
 * point of communication for a subsystem.
 * </P>
 * <p/>
 * <p/>
 * In PureMVC, the LegoFacade acts as an interface between the core MVC actors
 * (LegoModel, View, LegoController) and the rest of your application.
 * </P>
 *
 * @see Model Model
 * @see View View
 * @see Controller Controller
 * @see Command Command
 * @see Notification LegoNotification
 */
public interface Facade extends Notifier {

    /**
     * Notify <code>LegoObserver</code>s of an <code>LegoNotification</code>.
     *
     * @param note the <code>LegoNotification</code> to have the <code>View</code>
     *             notify observers of.
     */
    void notifyObservers(Notification note);

    /**
     * Register an <code>LegoProxy</code> with the <code>LegoModel</code> by NAME.
     *
     * @param proxy the <code>LegoProxy</code> to be registered with the
     *              <code>LegoModel</code>.
     */
    void registerProxy(Proxy proxy);

    /**
     * Retrieve a <code>LegoProxy</code> from the <code>LegoModel</code> by NAME.
     *
     * @param proxyName the NAME of the <code>LegoProxy</code> instance to be
     *                  retrieved.
     * @return the <code>LegoProxy</code> previously regisetered by
     * <code>proxyName</code> with the <code>LegoModel</code>.
     */
    <T extends Proxy> T retrieveProxy(String proxyName);

    /**
     * Remove an <code>LegoProxy</code> instance from the <code>LegoModel</code> by
     * NAME.
     *
     * @param proxyName the <code>LegoProxy</code> to remove from the
     *                  <code>LegoModel</code>.
     */
    Proxy removeProxy(String proxyName);

    /**
     * Check if a LegoProxy is registered
     *
     * @param proxyName
     * @return whether a LegoProxy is currently registered with the given <code>proxyName</code>.
     */
    boolean hasProxy(String proxyName);

    /**
     * Register an <code>Command</code> with the <code>LegoController</code>.
     *
     * @param noteName        the NAME of the <code>LegoNotification</code> to associate the
     *                        <code>Command</code> with.
     * @param commandClassRef a reference to the <code>Class</code> of the
     *                        <code>Command</code>.
     */
    void registerCommand(String noteName, Command commandClassRef);

    /**
     * Remove a previously registered <code>Command</code> to <code>LegoNotification</code> mapping from the LegoController.
     *
     * @param notificationName the NAME of the <code>LegoNotification</code> to remove the <code>Command</code> mapping for
     */
    void removeCommand(String notificationName);

    /**
     * Check if a Command is registered for a given LegoNotification
     *
     * @param notificationName
     * @return whether a Command is currently registered for the given <code>notificationName</code>.
     */
    boolean hasCommand(String notificationName);

    /**
     * Register an <code>LegoMediator</code> instance with the <code>View</code>.
     *
     * @param mediator a reference to the <code>LegoMediator</code> instance
     */
    void registerMediator(Mediator mediator);

    /**
     * Retrieve an <code>LegoMediator</code> instance from the <code>View</code>.
     *
     * @param mediatorName the NAME of the <code>LegoMediator</code> instance to retrievve
     * @return the <code>LegoMediator</code> previously registered with the given
     * <code>mediatorName</code>.
     */
    Mediator retrieveMediator(String mediatorName);

    /**
     * Check if a LegoMediator is registered or not
     *
     * @param mediatorName
     * @return whether a LegoMediator is registered with the given <code>mediatorName</code>.
     */
    boolean hasMediator(String mediatorName);

    /**
     * Remove a <code>LegoMediator</code> instance from the <code>View</code>.
     *
     * @param mediatorName NAME of the <code>LegoMediator</code> instance to be removed.
     */
    Mediator removeMediator(String mediatorName);
}
