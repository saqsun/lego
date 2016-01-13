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

import com.badlogic.gdx.utils.Array;
import com.google.inject.Injector;
import com.lego.mvc.Command;
import com.lego.mvc.Notification;
import com.lego.mvc.patterns.observer.LegoNotifier;


/**
 * A base <code>Command</code> implementation that executes other
 * <code>Command</code>s.
 * <p/>
 * <p/>
 * A <code>LegoCommandContainer</code> maintains an list of <code>Command</code>
 * Class references called <i>SubCommands</i>.
 * </P>
 * <p/>
 * <p/>
 * When <code>execute</code> is called, the <code>LegoCommandContainer</code>
 * instantiates and calls <code>execute</code> on each of its <i>SubCommands</i>
 * turn. Each <i>SubCommand</i> will be passed a reference to the original
 * <code>LegoNotification</code> that was passed to the <code>LegoCommandContainer</code>'s
 * <code>execute</code> method.
 * </P>
 * <p/>
 * <p/>
 * Unlike <code>LegoCommand</code>, your subclass should not override
 * <code>execute</code>, but instead, should override the
 * <code>initializeMacroCommand</code> method, calling
 * <code>addSubCommand</code> once for each <i>SubCommand</i> to be executed.
 * </P>
 * <p/>
 * <p/>
 */
public class LegoCommandContainer extends LegoNotifier implements Command {

    protected Injector injector;
    private Array<Command> subCommands;

    /**
     * Constructor.
     * <p/>
     * <p/>
     * You should not need to define a constructor, instead, override the
     * <code>initializeMacroCommand</code> method.
     * </P>
     * <p/>
     * <p/>
     * If your subclass does define a constructor, be sure to call
     * <code>super()</code>.
     * </P>
     */
    public LegoCommandContainer(Injector injector) {
        this.injector = injector;
        this.subCommands = new Array<>();
        initializeMacroCommand();
    }

    /**
     * Initialize the <code>LegoCommandContainer</code>.
     * <p/>
     * <p/>
     * In your subclass, override this method to initialize the
     * <code>LegoCommandContainer</code>'s <i>SubCommand</i> list with
     * <code>Command</code> class references like this:
     * </P>
     * <p/>
     * <listing> // Initialize MyMacroCommand override protected function
     * initializeMacroCommand( ) : void { addSubCommand(
     * com.me.myapp.controller.FirstCommand ); addSubCommand(
     * com.me.myapp.controller.SecondCommand ); addSubCommand(
     * com.me.myapp.controller.ThirdCommand ); } </listing>
     * <p/>
     * <p/>
     * Note that <i>SubCommand</i>s may be any <code>Command</code>
     * implementor, <code>LegoCommandContainer</code>s or <code>SimpleCommands</code>
     * are both acceptable.
     */
    protected void initializeMacroCommand() {
    }

    /**
     * Add a <i>SubCommand</i>.
     * <p/>
     * <p/>
     * The <i>SubCommands</i> will be called in First In/First Out (FIFO)
     * order.
     * </P>
     *
     * @param commandClassRef a reference to the <code>Class</code> of the
     *                        <code>Command</code>.
     */
    protected void addSubCommand(Command commandClassRef) {
        this.subCommands.add(commandClassRef);
    }

    /**
     * Execute this <code>LegoCommandContainer</code>'s <i>SubCommands</i>.
     * <p/>
     * <p/>
     * The <i>SubCommands</i> will be called in First In/First Out (FIFO)
     * order.
     *
     * @param notification the <code>LegoNotification</code> object to be passsed to each
     *                     <i>SubCommand</i>.
     */
    public void execute(Notification notification) {
        for (Command command : subCommands)
            command.execute(notification);
    }
}
