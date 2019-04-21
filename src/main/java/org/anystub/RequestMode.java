package org.anystub;

public enum RequestMode {
    /**
     * Use case: general using of cache.
     * request is sent to real system if it is not found in cache
     */
    rmNew,

    /**
     * Use case: strict check.
     * sending requests to real system is forbidden.
     * cache is loaded immediately. if request is not found in cache then exception is thrown
     */
    rmNone,

   /**
     * Use case: paranoid check.
    *  if stub file is new it works as rmAll
    *  * in other
     * sending requests to real system is forbidden
     * search in cache is forbidden
     * cache is loaded immediately.
     * check if all calls go after each other
     * Note: before set rmTrack you have to run rmALL
     */
    rmTrack,

    /**
     * Use case: logging from upstream.
     * all requests are sent to real system. all responses recorded to cache (duplicates may be created in stub-file)
     * old stub-file overridden
     */
    rmAll,

    /**
     * Use case: check anystub works properly
     * all requests are sent to upstream, results return with no mutations
     */
    rmPassThrough

}