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
 * The interface definition for a PureMVC LegoController.
 * <p/>
 * <p/>
 * In PureMVC, an <code>Controller</code> implementor follows the 'Command
 * and LegoController' strategy, and assumes these responsibilities:
 * <UL>
 * <LI> Remembering which <code>Command</code>s are intended to handle which
 * <code>INotifications</code>.</LI>
 * <LI> Registering itself as an <code>LegoObserver</code> with the
 * <code>View</code> for each <code>LegoNotification</code> that it has an
 * <code>Command</code> mapping for.</LI>
 * <LI> Creating a new instance of the proper <code>Command</code> to handle
 * a given <code>LegoNotification</code> when notified by the <code>View</code>.</LI>
 * <LI> Calling the <code>Command</code>'s <code>execute</code> method,
 * passing in the <code>LegoNotification</code>.</LI>
 * </UL>
 *
 */
public interface Controller {
    /**
     * Register a particular <code>Command</code> class as the handler for a
     * particular <code>LegoNotification</code>.
     *
     * @param notificationName the NAME of the <code>LegoNotification</code>
     * @param command          the Class of the <code>Command</code>
     */
    void registerCommand(String notificationName, Command command);

    /**
     * Execute the <code>Command</code> previously registered as the handler
     * for <code>LegoNotification</code>s with the given notification NAME.
     *
     * @param notification the <code>LegoNotification</code> to execute the associated
     *                     <code>Command</code> for
     */
    void executeCommand(Notification notification);

    /**
     * Remove a previously registered <code>Command</code> to
     * <code>LegoNotification</code> mapping.
     *
     * @param notificationName the NAME of the <code>LegoNotification</code> to remove the
     *                         <code>Command</code> mapping for
     */
    void removeCommand(String notificationName);

    /**
     * Check if a Command is registered for a given LegoNotification
     *
     * @param notificationName
     * @return whether a Command is currently registered for the given <code>notificationName</code>.
     */
    boolean hasCommand(String notificationName);
}
