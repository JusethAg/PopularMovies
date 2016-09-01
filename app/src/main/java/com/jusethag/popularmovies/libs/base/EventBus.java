package com.jusethag.popularmovies.libs.base;

/**
 * Created by JusethAg on 9/1/2016.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
