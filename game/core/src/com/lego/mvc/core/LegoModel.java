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
import com.lego.mvc.Model;
import com.lego.mvc.Proxy;

/**
 * A Singleton <code>Model</code> implementation.
 * <p/>
 * <P>
 * In PureMVC, the <code>LegoModel</code> class provides
 * access to model objects (Proxies) by named lookup.
 * <p/>
 * <P>
 * The <code>LegoModel</code> assumes these responsibilities:</P>
 * <p/>
 * <UL>
 * <LI>Maintain a cache of <code>LegoProxy</code> instances.</LI>
 * <LI>Provide methods for registering, retrieving, and removing
 * <code>LegoProxy</code> instances.</LI>
 * </UL>
 * <p/>
 * <P>
 * Your application must register <code>LegoProxy</code> instances
 * with the <code>LegoModel</code>. Typically, you use an
 * <code>Command</code> to create and register <code>LegoProxy</code>
 * instances once the <code>LegoFacade</code> has initialized the Core
 * actors.</p>
 */
public class LegoModel implements Model {

    /**
     * Mapping of proxyNames to LegoProxy instances
     */
    protected ObjectMap<String, Proxy> proxyMap;

    /**
     * Constructor.
     * <p/>
     * <p/>
     * This <code>Model</code> implementation is a Multiton,
     * so you should not call the constructor
     * directly, but instead call the static Multiton
     * Factory method <code>LegoModel.getInstance( multitonKey )</code>
     *
     * @throws Error Error if instance for this Multiton key instance has already been constructed
     */
    public LegoModel() {
        proxyMap = new ObjectMap<>();
    }

    /**
     * Register an <code>LegoProxy</code> with the <code>LegoModel</code>.
     *
     * @param proxy an <code>LegoProxy</code> to be held by the <code>LegoModel</code>.
     */
    public void registerProxy(Proxy proxy) {
        this.proxyMap.put(proxy.getProxyName(), proxy);
        proxy.onRegister();
    }

    /**
     * Remove an <code>LegoProxy</code> from the <code>LegoModel</code>.
     *
     * @param proxyName Name of the <code>LegoProxy</code> instance to be removed.
     * @return The <code>LegoProxy</code> that was removed from the <code>LegoModel</code>
     */
    public Proxy removeProxy(String proxyName) {
        Proxy proxy = this.proxyMap.get(proxyName);

        if (proxy != null) {
            this.proxyMap.remove(proxyName);
            proxy.onRemove();
        }

        return proxy;
    }

    /**
     * Retrieve an <code>LegoProxy</code> from the <code>LegoModel</code>.
     *
     * @param proxy
     * @return the <code>LegoProxy</code> instance previously registered with the
     * given <code>proxyName</code>.
     */
    public Proxy retrieveProxy(String proxy) {
        return this.proxyMap.get(proxy);
    }

    /**
     * Check if a LegoProxy is registered
     *
     * @param proxyName Name of the <code>LegoProxy</code> object to check for existance.
     * @return Whether a LegoProxy is currently registered with the given <code>proxyName</code>.
     */
    public boolean hasProxy(String proxyName) {
        return proxyMap.containsKey(proxyName);
    }
}
