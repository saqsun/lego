/*
 PureMVC Java port by Frederic Saunier <frederic.saunier@puremvc.org>
 
 Adapted from sources of thoses different authors :
 	Donald Stinchfield <donald.stinchfield@puremvc.org>, et all
 	Ima OpenSource <opensource@ima.eu>
 	Anthony Quinault <anthony.quinault@puremvc.org>
 
 PureMVC - Copyright(c) 2006-10 Futurescale, Inc., Some rights reserved. 
 Your reuse is governed by the Creative Commons Attribution 3.0 License
*/
package com.lego.mvc.patterns.proxy;


import com.lego.mvc.Proxy;
import com.lego.mvc.patterns.observer.LegoNotifier;

/**
 * A base <code>LegoProxy</code> implementation.
 * <p/>
 * <p/>
 * In PureMVC, <code>LegoProxy</code> classes are used to manage parts of the
 * application's data model.
 * </P>
 * <p/>
 * <p/>
 * A <code>LegoProxy</code> might simply manage a reference to a local data
 * object, in which case interacting with it might involve setting and getting
 * of its data in synchronous fashion.
 * </P>
 * <p/>
 * <p/>
 * <code>LegoProxy</code> classes are also used to encapsulate the application's
 * interaction with remote services to save or retrieve data, in which case, we
 * adopt an asyncronous idiom; setting data (or calling a method) on the
 * <code>LegoProxy</code> and listening for a <code>LegoNotification</code> to be
 * sent when the <code>LegoProxy</code> has retrieved the data from the service.
 * </P>
 *
 */
public class LegoProxy<T> extends LegoNotifier implements Proxy<T> {

    // the proxy NAME
    protected String proxyName;

    // the data object
    protected T data;

    /**
     * Constructor
     *
     * @param proxyName
     * @param data
     */
    public LegoProxy(String proxyName, T data) {
        if (proxyName != null) {
            this.proxyName = proxyName;
        }
        if (data != null) {
            this.data = data;
        }
    }

    /**
     * Constructor
     *
     * @param proxyName Name of the <code>LegoProxy</code>
     */
    public LegoProxy(String proxyName) {
        if (proxyName != null)
            this.proxyName = proxyName;

    }

    /**
     * Get the proxy NAME
     *
     * @return the proxy NAME
     */
    public String getProxyName() {
        return this.proxyName;
    }

    /**
     * Get the data object
     *
     * @return the data object
     */
    public T getData() {
        return this.data;
    }

    /**
     * Set the data object
     *
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Called by the LegoModel when the LegoProxy is registered
     */
    public void onRegister() {
    }

    /**
     * Called by the LegoModel when the LegoProxy is removed
     */
    public void onRemove() {
    }
}
