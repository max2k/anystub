package org.anystub;

public enum RequestMode {
    /**
     * Use case: general using of cache.
     * request is sent to real system if it is not found in cache
     */
    rmNew,

    /**
     * Use case: strict checking.
     * sending requests to real system is forbidden.
     * cache is loaded immediately. if request is not found in cache then exception is thrown
     */
    rmNone,

    /**
     * Use case: cache logging from upstream.
     * all requests are sent to real system. all responses recorded to cache
     */
    rmAll
}