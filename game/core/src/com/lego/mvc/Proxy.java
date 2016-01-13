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
 * The interface definition for a PureMVC LegoProxy.
 * <p/>
 * <p/>
 * In PureMVC, <code>LegoProxy</code> implementors assume these responsibilities:
 * </P>
 * <UL>
 * <LI>Implement a common method which returns the NAME of the LegoProxy.</LI>
 * </UL>
 * <p/>
 * Additionally, <code>LegoProxy</code>s typically:
 * </P>
 * <UL>
 * <LI>Maintain references to one or more pieces of model data.</LI>
 * <LI>Provide methods for manipulating that data.</LI>
 * <LI>Generate <code>INotifications</code> when their model data changes.</LI>
 * <LI>Expose their NAME as a <code>public static const</code> called <code>NAME</code>, if they are not instantiated multiple times.</LI>
 * <LI>Encapsulate interaction with local or remote services used to fetch and
 * persist model data.</LI>
 * </UL>
 */
public interface Proxy<T> extends Notifier {

    /**
     * Get the LegoProxy NAME
     *
     * @return the LegoProxy instance NAME
     */
    String getProxyName();

    /**
     * Get the data object
     *
     * @return the data as type Object
     */
    T getData();

    /**
     * Set the data object
     *
     * @param data the data object
     */
    void setData(T data);

    /**
     * Called by the LegoModel when the LegoProxy is registered
     */
    void onRegister();

    /**
     * Called by the LegoModel when the LegoProxy is removed
     */
    void onRemove();

}
