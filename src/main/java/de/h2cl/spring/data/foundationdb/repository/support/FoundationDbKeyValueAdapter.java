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
import de.h2cl.spring.data.foundationdb.repository.core.convert.FoundationDbConverter;
import de.h2cl.spring.data.foundationdb.repository.core.convert.MappingFoundationDbConverter;
import de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbMappingContext;

/**
 * {@link KeyValueAdapter} implementation for {@link com.apple.foundationdb.FDBDatabase}.
 * TODO Implement! ;)
 * Unless I'm not sure if keySpace could be a database or cluster I keep it as default null.
 *
 * @author Martin Junker
 */
public class FoundationDbKeyValueAdapter extends AbstractKeyValueAdapter {

    private final Database database;

    private final FoundationDbConverter foundationConverter;


    public FoundationDbKeyValueAdapter(FoundationDbDatabaseFactory databaseFactory) {
        this(databaseFactory, null);
    }

    public FoundationDbKeyValueAdapter(FoundationDbDatabaseFactory databaseFactory, FoundationDbMappingContext mappingContext) {
        this.database = databaseFactory.build();
        foundationConverter = new MappingFoundationDbConverter(mappingContext);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.keyvalue.core.KeyValueAdapter#put(java.lang.Object, java.lang.Object, java.lang.String)
     */
    @Override
    public Object put(Object id, Object item, String keyspace) {

        Assert.notNull(id, "Cannot add item with null id.");
        Assert.notNull(keyspace, "Cannot add item for null collection.");

        return database.run(tr -> {
            byte[] result = tr.get(Tuple.from(keyspace, id).pack()).join();
            tr.set(Tuple.from(keyspace, id).pack(), Tuple.from(item).pack());
            if (result == null) {
                return null;
            }
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
            byte[] result = tr.get(Tuple.from(keyspace, id).pack()).join();
            if (result == null) {
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
            byte[] result = tr.get(Tuple.from(keyspace, id).pack()).join();
            if (result == null) {
                return null;
            }
            tr.clear(Tuple.from(keyspace, id).pack());
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
        database.run(tr -> {
            tr.clear(Tuple.from(keyspace).range());
            return null;
        });
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
