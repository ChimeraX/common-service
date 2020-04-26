package org.chimerax.common.security.jwt;

import io.jsonwebtoken.Header;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: Silviu-Mihnea Cucuiet
 * Date: 26-Apr-20
 * Time: 11:45 AM
 */
public class JWTHeader implements Header<JWTHeader> {

    private Map<String, Object> header = new HashMap<>();

    @Override
    public String getType() {
        return header.get(Header.TYPE).toString();
    }

    @Override
    public JWTHeader setType(final String typ) {
        header.put(Header.TYPE, typ);
        return this;
    }

    @Override
    public String getContentType() {
        return header.get(Header.CONTENT_TYPE).toString();
    }

    @Override
    public JWTHeader setContentType(String cty) {
        header.put(Header.CONTENT_TYPE, cty);
        return this;
    }

    @Override
    public String getCompressionAlgorithm() {
        return header.get(Header.COMPRESSION_ALGORITHM).toString();
    }

    @Override
    public JWTHeader setCompressionAlgorithm(final String alg) {
        header.put(Header.COMPRESSION_ALGORITHM, alg);
        return this;
    }

    @Override
    public int size() {
        return header.size();
    }

    @Override
    public boolean isEmpty() {
        return header.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return header.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return header.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return header.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return header.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return header.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> m) {
        header.putAll(m);
    }

    @Override
    public void clear() {
        header.clear();
    }

    @Override
    public Set<String> keySet() {
        return header.keySet();
    }

    @Override
    public Collection<Object> values() {
        return header.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return header.entrySet();
    }
}
