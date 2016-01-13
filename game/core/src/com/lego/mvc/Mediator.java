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
 * The interface definition for a PureMVC LegoMediator.
 * <p/>
 * <p/>
 * In PureMVC, <code>LegoMediator</code> implementors assume these
 * responsibilities:
 * </P>
 * <UL>
 * <LI>Implement a common method which returns a list of all
 * <code>LegoNotification</code>s the <code>LegoMediator</code> has interest in.</LI>
 * <LI>Implement a common notification (callback) method.</LI>
 * </UL>
 * <p/>
 * Additionally, <code>LegoMediator</code>s typically:
 * <UL>
 * <LI>Act as an intermediary between one or more view components such as text
 * boxes or list controls, maintaining references and coordinating their
 * behavior.</LI>
 * <LI>In Flash-based apps, this is often the place where event listeners are
 * added to view components, and their handlers implemented.</LI>
 * <LI>Respond to and generate <code>INotifications</code>, interacting with
 * of the rest of the PureMVC app.
 * </UL>
 * </P>
 * <p/>
 * When an <code>LegoMediator</code> is registered with the <code>View</code>,
 * the <code>View</code> will call the <code>LegoMediator</code>'s
 * <code>listNotificationInterests</code> method. The <code>LegoMediator</code>
 * will return an <code>Array</code> of <code>LegoNotification</code> names
 * which it wishes to be notified about.
 * </P>
 * <p/>
 * <p/>
 * The <code>View</code> will then create an <code>LegoObserver</code> object
 * encapsulating that <code>LegoMediator</code>'s (<code>handleNotification</code>)
 * method and register it as an LegoObserver for each <code>LegoNotification</code>
 * NAME returned by <code>listNotificationInterests</code>.
 * </P>
 * <p/>
 * <p/>
 * A concrete LegoMediator implementor usually looks something like this:
 * </P>
 * <p/>
 * <listing> import org.puremvc.patterns.mediator.~~; import
 * org.puremvc.patterns.observer.~~; import org.puremvc.core.view.~~;
 * <p/>
 * import com.me.myapp.model.~~; import com.me.myapp.view.~~; import
 * com.me.myapp.controller.~~;
 * <p/>
 * import mx.controls.ComboBox; import mx.events.ListEvent;
 * <p/>
 * class MyMediator extends LegoMediator implements LegoMediator {
 * <p/>
 * function MyComboMediator( viewComponent:Object ) { super(
 * viewComponent ); combo.addEventListener( Event.CHANGE, onChange ); }
 * <p/>
 * function listNotificationInterests():Array { return [
 * MyFacade.SET_SELECTION, MyFacade.SET_DATAPROVIDER ]; }
 * <p/>
 * function handleNotification( notification:LegoNotification ):void {
 * switch ( notification.getName() ) { case MyFacade.SET_SELECTION:
 * setSelection(notification); break; case MyFacade.SET_DATAPROVIDER:
 * setDataProvider(notification); break; } } // Set the data provider of the
 * combo box private function setDataProvider( notification:LegoNotification ):void {
 * combo.dataProvider = notification.getBody() as Array; } // Invoked when the
 * combo box dispatches a change event, we send a // notification with the
 * private function onChange(event:ListEvent):void { sendNotification(
 * MyFacade.MYCOMBO_CHANGED, this ); } // A private getter for accessing the
 * view object by class private function get combo():ComboBox { return view as
 * ComboBox; } } </listing>
 *
 * @see Notification LegoNotification
 */
public interface Mediator<T> extends Notifier {

    /**
     * Get the <code>LegoMediator</code> instance NAME
     *
     * @return the <code>LegoMediator</code> instance NAME
     */
    String getMediatorName();

    /**
     * Get the <code>LegoMediator</code>'s view component.
     *
     * @return Object the view component
     */
    T getViewComponent();

    /**
     * Set the <code>LegoMediator</code>'s view component.
     *
     * @param viewComponent The view component
     */
    void setViewComponent(T viewComponent);

    /**
     * List <code>LegoNotification</code> interests.
     *
     * @return an <code>Array</code> of the <code>LegoNotification</code> names
     * this <code>LegoMediator</code> has an interest in.
     */
    String[] listNotificationInterests();

    /**
     * Handle an <code>LegoNotification</code>.
     *
     * @param notification the <code>LegoNotification</code> to be handled
     */
    void handleNotification(Notification notification);

    /**
     * Called by the View when the LegoMediator is registered
     */
    void onRegister();

    /**
     * Called by the View when the LegoMediator is removed
     */
    void onRemove();
}
