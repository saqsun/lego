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
 * The interface definition for a PureMVC LegoModel.
 * <p/>
 * <p/>
 * In PureMVC, <code>Model</code> implementors provide access to
 * <code>LegoProxy</code> objects by named lookup.
 * </P>
 * <p/>
 * <p/>
 * An <code>Model</code> assumes these responsibilities:
 * </P>
 * <p/>
 * <UL>
 * <LI>Maintain a cache of <code>LegoProxy</code> instances</LI>
 * <LI>Provide methods for registering, retrieving, and removing
 * <code>LegoProxy</code> instances</LI>
 * </UL>
 */
public interface Model {

    /**
     * Register an <code>LegoProxy</code> instance with the <code>LegoModel</code>.
     *
     * @param proxy an object reference to be held by the <code>LegoModel</code>.
     */
    void registerProxy(Proxy proxy);

    /**
     * Retrieve an <code>LegoProxy</code> instance from the LegoModel.
     *
     * @param proxy
     * @return the <code>LegoProxy</code> instance previously registered with the
     * given <code>proxyName</code>.
     */
    Proxy retrieveProxy(String proxy);

    /**
     * Remove an <code>LegoProxy</code> instance from the LegoModel.
     *
     * @param proxy NAME of the <code>LegoProxy</code> instance to be removed.
     */
    Proxy removeProxy(String proxy);

    /**
     * Check if a LegoProxy is registered
     *
     * @param proxyName
     * @return whether a LegoProxy is currently registered with the given <code>proxyName</code>.
     */
    boolean hasProxy(String proxyName);
}
