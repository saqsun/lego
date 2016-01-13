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
 * The interface definition for a PureMVC Command.
 */
public interface Command extends Notifier {

    /**
     * Execute the <code>Command</code>'s logic to handle a given
     * <code>LegoNotification</code>.
     *
     * @param notification an <code>LegoNotification</code> to handle.
     */
    void execute(Notification notification);
}
