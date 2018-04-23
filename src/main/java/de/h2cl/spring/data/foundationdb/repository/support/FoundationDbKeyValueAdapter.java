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
import org.springframework.data.keyvalue.core.KeyValueAdapter;
import org.springframework.data.util.CloseableIterator;
import org.springframework.util.Assert;

import com.apple.foundationdb.Database;
import com.apple.foundationdb.tuple.Tuple;

/**
 * {@link KeyValueAdapter} implementation for {@link com.apple.foundationdb.FDBDatabase}.
 * TODO Implement! ;)
 *
 * @author Martin Junker
 */
public class FoundationDbKeyValueAdapter extends AbstractKeyValueAdapter {

    private final Database database;

    public FoundationDbKeyValueAdapter(FoundationDbDatabaseFactory databaseFactory) {
        database = databaseFactory.build();
    }

    @Override
    public Object put(Object id, Object item, String keyspace) {

        Assert.notNull(id, "Cannot add item with null id.");
        Assert.notNull(keyspace, "Cannot add item for null collection.");

        database.run(tr -> {
            tr.set(Tuple.from(id).pack(), Tuple.from(item).pack());
            return null;
        });
        return null;
    }

    @Override
    public boolean contains(Object id, String keyspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object get(Object id, String keyspace) {

        return database.run(tr -> {
            byte[] result = tr.get(Tuple.from(id).pack()).join();
            return Tuple.fromBytes(result).getString(0);
        });
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
