/*
 PureMVC Java port by Frederic Saunier <frederic.saunier@puremvc.org>
 
 Adapted from sources of thoses different authors :
 	Donald Stinchfield <donald.stinchfield@puremvc.org>, et all
 	Ima OpenSource <opensource@ima.eu>
 	Anthony Quinault <anthony.quinault@puremvc.org>
 
 PureMVC - Copyright(c) 2006-10 Futurescale, Inc., Some rights reserved. 
 Your reuse is governed by the Creative Commons Attribution 3.0 License
*/
package com.lego.mvc.patterns.command;


import com.lego.mvc.Command;
import com.lego.mvc.Notification;
import com.lego.mvc.patterns.observer.LegoNotifier;

/**
 * A base <code>Command</code> implementation.
 * <p/>
 * <p/>
 * Your subclass should override the <code>execute</code> method where your
 * business logic will handle the <code>LegoNotification</code>.
 * </P>
 */
public class LegoCommand extends LegoNotifier implements Command {

    /**
     * Fulfill the use-case initiated by the given <code>LegoNotification</code>.
     * <p/>
     * <p/>
     * In the Command Pattern, an application use-case typically begins with
     * some user action, which results in an <code>LegoNotification</code> being
     * broadcast, which is handled by business logic in the <code>execute</code>
     * method of an <code>Command</code>.
     * </P>
     *
     * @param notification the <code>LegoNotification</code> to handle.
     */
    public void execute(Notification notification) {
    }

}
