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
 * The interface definition for a PureMVC View.
 * <p/>
 * <p/>
 * In PureMVC, <code>View</code> implementors assume these responsibilities:
 * </P>
 * <p/>
 * <p/>
 * In PureMVC, the <code>View</code> class assumes these responsibilities:
 * <UL>
 * <LI>Maintain a cache of <code>LegoMediator</code> instances.</LI>
 * <LI>Provide methods for registering, retrieving, and removing
 * <code>IMediators</code>.</LI>
 * <LI>Managing the observer lists for each <code>LegoNotification</code> in the
 * application.</LI>
 * <LI>Providing a method for attaching <code>IObservers</code> to an
 * <code>LegoNotification</code>'s observer list.</LI>
 * <LI>Providing a method for broadcasting an <code>LegoNotification</code>.</LI>
 * <LI>Notifying the <code>IObservers</code> of a given
 * <code>LegoNotification</code> when it broadcast.</LI>
 * </UL>
 *
 * @see Mediator LegoMediator
 * @see Observer LegoObserver
 * @see Notification LegoNotification
 */
public interface View {

    /**
     * Register an <code>LegoObserver</code> to be notified of
     * <code>INotifications</code> with a given NAME.
     *
     * @param noteName the NAME of the <code>INotifications</code> to notify this
     *                 <code>LegoObserver</code> of
     * @param observer the <code>LegoObserver</code> to register
     */
    void registerObserver(String noteName, Observer observer);

    /**
     * Notify the <code>IObservers</code> for a particular
     * <code>LegoNotification</code>.
     * <p/>
     * <p/>
     * All previously attached <code>IObservers</code> for this
     * <code>LegoNotification</code>'s list are notified and are passed a
     * reference to the <code>LegoNotification</code> in the order in which they
     * were registered.
     * </P>
     *
     * @param note the <code>LegoNotification</code> to notify
     *             <code>IObservers</code> of.
     */
    void notifyObservers(Notification note);

    /**
     * Register an <code>LegoMediator</code> instance with the <code>View</code>.
     * <p/>
     * <P>
     * Registers the <code>LegoMediator</code> so that it can be retrieved by
     * NAME, and further interrogates the <code>LegoMediator</code> for its
     * <code>LegoNotification</code> interests.
     * </P>
     * <P>
     * If the <code>LegoMediator</code> returns any <code>LegoNotification</code>
     * names to be notified about, an <code>LegoObserver</code> is created
     * encapsulating the <code>LegoMediator</code> instance's
     * <code>handleNotification</code> method and registering it as an
     * <code>LegoObserver</code> for all <code>INotifications</code> the
     * <code>LegoMediator</code> is interested in.
     * </p>
     *
     * @param mediator a reference to the <code>LegoMediator</code> instance
     */
    void registerMediator(Mediator mediator);

    /**
     * Retrieve an <code>LegoMediator</code> from the <code>View</code>.
     *
     * @param mediatorName the NAME of the <code>LegoMediator</code> instance to retrieve.
     * @return the <code>LegoMediator</code> instance previously registered with
     * the given <code>mediatorName</code>.
     */
    Mediator retrieveMediator(String mediatorName);

    /**
     * Remove an <code>LegoMediator</code> from the <code>View</code>.
     *
     * @param mediatorName NAME of the <code>LegoMediator</code> instance to be removed.
     */
    Mediator removeMediator(String mediatorName);

    /**
     * Check if a LegoMediator is registered or not
     *
     * @param mediatorName
     * @return whether a LegoMediator is registered with the given <code>mediatorName</code>.
     */
    boolean hasMediator(String mediatorName);

    /**
     * Remove the observer for a given notifyContext from an observer list for a given LegoNotification NAME.
     *
     * @param notificationName Which observer list to remove from
     * @param notifyContext    Remove the observer with this object as its notifyContext
     */
    void removeObserver(String notificationName, Object notifyContext);
}
