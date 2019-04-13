package org.anystub.http;

/**
 * to opt out http keys
 * - default logging options: method/version, protocol, full-URL, path-vars
 * - to exclude any part of url: exclude("schema","host","port","path")
 * - to include header use '$' : include("$Content-Type")
 * - to include specific var-args use '&': include("&login","&password")
 * - binary convert allowed: body, &path-var, $header
 * - binary convert allowed on condition: entry/regexp; ex. body/password; $header/token; &path-var/password
 * - specifying any of path-var excludes path-vars
 * - specifying any of header excludes headers
 * - binary convert for path-var/header adds it to include entity
 * - priority from low to high:
 * -- include: method, version, protocol, schema, host, port, path, path-vars (default)
 * -- include: headers
 * -- exclude: method, version, protocol, schema, host, port, path, headers, path-vars
 * -- include: $header (excludes headers), &path-var (excludes path-vars)
 * -- exclude: $header (does not include headers), &path-var
 * - conditional convert to binary excludes headers & path-vars when applied
 */
public class KeyOptions {

    final String[] keywords = {"method", "version", "schema", "host", "port", "path", "path-vars", "headers"};

    void key(String... keys) {

    }

    void keysExclude(String... keys) {

    }

    void binaryConvert(String... keys) {

    }
}
