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

import de.h2cl.spring.data.foundationdb.repository.FoundationDbDatabaseFactory;

/**
 * {@link KeyValueAdapter} implementation for {@link com.apple.foundationdb.FDBDatabase}.
 * TODO Implement! ;)
 * Unless I'm not sure if keySpace could be a database or cluster I keep it as default null.
 *
 * @author Martin Junker
 */
public class FoundationDbKeyValueAdapter extends AbstractKeyValueAdapter {

    private final Database database;

    public FoundationDbKeyValueAdapter() {
        this.database = new FoundationDbDatabaseFactory().build();
    }

    public FoundationDbKeyValueAdapter(Database database) {
        this.database = database;
    }

    public FoundationDbKeyValueAdapter(FoundationDbDatabaseFactory databaseFactory) {
        database = databaseFactory.build();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.keyvalue.core.KeyValueAdapter#put(java.lang.Object, java.lang.Object, java.lang.String)
     */
    @Override
    public Object put(Object id, Object item, String keyspace) {

        Assert.notNull(id, "Cannot add item with null id.");
        // Assert.notNull(keySpace, "Cannot add item for null collection.");

        return database.run(tr -> {
            byte[] result = tr.get(Tuple.from(id).pack()).join();
            if(result == null) {
                return null;
            }
            tr.set(Tuple.from(id).pack(), Tuple.from(item).pack());
            return Tuple.fromBytes(result).get(0);
        });
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.keyvalue.core.KeyValueAdapter#contains(java.lang.Object, java.lang.String)
     */
    @Override
    public boolean contains(Object id, String keyspace) {
        return get(id, keyspace) != null;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.keyvalue.core.KeyValueAdapter#get(java.lang.Object, java.lang.String)
     */
    @Override
    public Object get(Object id, String keyspace) {

        Assert.notNull(id, "Cannot get item with null id.");
        return database.run(tr -> {
            byte[] result = tr.get(Tuple.from(id).pack()).join();
            if(result == null) {
                return null;
            }
            return Tuple.fromBytes(result).get(0);
        });
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.keyvalue.core.KeyValueAdapter#delete(java.lang.Object, java.lang.String)
     */
    @Override
    public Object delete(Object id, String keyspace) {

        Assert.notNull(id, "Cannot delete item with null id.");
        return database.run(tr -> {
            byte[] result = tr.get(Tuple.from(id).pack()).join();
            if (result == null) {
                return null;
            }
            tr.clear(Tuple.from(id).pack());
            return Tuple.fromBytes(result).get(0);
        });
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.keyvalue.core.KeyValueAdapter#getAllOf(java.lang.String)
     */
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
        database.close();
    }

    @Override
    public long count(String keyspace) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void destroy() throws Exception {
        database.close();
    }
}
