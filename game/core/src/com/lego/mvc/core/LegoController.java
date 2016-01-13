/*
 PureMVC Java port by Frederic Saunier <frederic.saunier@puremvc.org>
 
 Adapted from sources of thoses different authors :
 	Donald Stinchfield <donald.stinchfield@puremvc.org>, et all
 	Ima OpenSource <opensource@ima.eu>
 	Anthony Quinault <anthony.quinault@puremvc.org>
 
 PureMVC - Copyright(c) 2006-10 Futurescale, Inc., Some rights reserved. 
 Your reuse is governed by the Creative Commons Attribution 3.0 License
*/
package com.lego.mvc.core;


import com.badlogic.gdx.utils.ObjectMap;
import com.google.inject.Inject;
import com.lego.mvc.*;
import com.lego.mvc.Runnable;
import com.lego.mvc.patterns.observer.LegoObserver;

/**
 * A Singleton <code>Controller</code> implementation.
 * <p/>
 * <p/>
 * In PureMVC, the <code>LegoController</code> class follows the
 * 'Command and LegoController' strategy, and assumes these
 * responsibilities:
 * <UL>
 * <LI> Remembering which <code>Command</code>s
 * are intended to handle which <code>INotifications</code>.</LI>
 * <LI> Registering itself as an <code>LegoObserver</code> with
 * the <code>View</code> for each <code>LegoNotification</code>
 * that it has an <code>Command</code> mapping for.</LI>
 * <LI> Creating a new instance of the proper <code>Command</code>
 * to handle a given <code>LegoNotification</code> when notified by the <code>View</code>.</LI>
 * <LI> Calling the <code>Command</code>'s <code>execute</code>
 * method, passing in the <code>LegoNotification</code>.</LI>
 * </UL>
 * <p/>
 * <p/>
 * Your application must register <code>ICommands</code> with the
 * LegoController.
 * <p/>
 * The simplest way is to subclass </code>LegoFacade</code>,
 * and use its <code>initialize</code> method to add your
 * registrations.
 */
public class LegoController implements Controller {

    /**
     * Mapping of LegoNotification names to Command Class references
     */
    protected ObjectMap<String, Command> commandMap;

    /**
     * Local reference to View
     */
    protected View view;

    /**
     * Constructor.
     * <p/>
     * <p/>
     * This <code>Controller</code> implementation is a Singleton, so you
     * should not call the constructor directly, but instead call the static
     * Singleton Factory method <code>LegoController.getInstance()</code>
     */
    @Inject
    public LegoController(View view) {
        this.view = view;
        commandMap = new ObjectMap<>();
    }

    /**
     * If an <code>Command</code> has previously been registered to handle a
     * the given <code>LegoNotification</code>, then it is executed.
     *
     * @param note The notification to send associated with the command to call.
     */
    public void executeCommand(Notification note) {
        //No reflexion in GWT
        //Command commandInstance = (Command) commandClassRef.newInstance();
        Command commandInstance = this.commandMap.get(note.getName());
        if (commandInstance != null) {
            commandInstance.execute(note);
        }
    }

    /**
     * Register a particular <code>Command</code> class as the handler for a
     * particular <code>LegoNotification</code>.
     * <p/>
     * <p/>
     * If an <code>Command</code> has already been registered to handle
     * <code>LegoNotification</code>s with this NAME, it is no longer used, the
     * new <code>Command</code> is used instead.
     * </P>
     * <p/>
     * The LegoObserver for the new Command is only created if this the
     * first time an Command has been regisered for this LegoNotification NAME.
     *
     * @param notificationName the NAME of the <code>LegoNotification</code>
     * @param command          an instance of <code>Command</code>
     */
    public void registerCommand(String notificationName, Command command) {
        if (null != this.commandMap.put(notificationName, command))
            return;

        view.registerObserver
                (
                        notificationName,
                        new LegoObserver
                                (
                                        new Runnable() {
                                            public void run(Notification notification) {
                                                executeCommand(notification);
                                            }
                                        },
                                        this
                                )
                );
    }

    /**
     * Remove a previously registered <code>Command</code> to
     * <code>LegoNotification</code> mapping.
     *
     * @param notificationName the NAME of the <code>LegoNotification</code> to remove the
     *                         <code>Command</code> mapping for
     */
    public void removeCommand(String notificationName) {
        // if the Command is registered...
        if (hasCommand(notificationName)) {
            // remove the observer
            view.removeObserver(notificationName, this);
            commandMap.remove(notificationName);
        }
    }

    /**
     * Check if a Command is registered for a given LegoNotification
     *
     * @param notificationName The NAME of the command to check for existance.
     * @return whether a Command is currently registered for the given <code>notificationName</code>.
     */
    public boolean hasCommand(String notificationName) {
        return commandMap.containsKey(notificationName);
    }
}
