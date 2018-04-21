/*
 * Copyright 2010-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.h2cl.spring.data.foundationdb.repository.support;

import java.util.Map;

import org.springframework.data.keyvalue.core.AbstractKeyValueAdapter;
import org.springframework.data.util.CloseableIterator;

/**
 * TODO Implement! ;)
 */
public class FoundationDbKeyValueAdapter extends AbstractKeyValueAdapter {


    @Override
    public Object put(Object id, Object item, String keyspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object id, String keyspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(Object id, String keyspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object delete(Object id, String keyspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<?> getAllOf(String keyspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CloseableIterator<Map.Entry<Object, Object>> entries(String keyspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAllOf(String keyspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count(String keyspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void destroy() throws Exception {
        throw new UnsupportedOperationException();
    }
}
