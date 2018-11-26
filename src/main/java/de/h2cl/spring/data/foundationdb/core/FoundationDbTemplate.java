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
package de.h2cl.spring.data.foundationdb.core;

import com.apple.foundationdb.tuple.Tuple;
import de.h2cl.spring.data.foundationdb.FoundationDbFactory;
import de.h2cl.spring.data.foundationdb.core.convert.FoundationDbConverter;
import de.h2cl.spring.data.foundationdb.core.convert.FoundationDbCustomConversions;
import de.h2cl.spring.data.foundationdb.core.convert.MappingFoundationDbConverter;
import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbMappingContext;
import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbPersistentEntity;
import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbPersistentProperty;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Collections;

/**
 * FoundationDbTemplate
 *
 * @author Christoph Strobl
 * @author Oliver Gierke
 * @author Thomas Darimont
 * @author Mark Paluch
 * @author Martin Junker
 */
public class FoundationDbTemplate implements FoundationDbOperations {

    private static final PersistenceExceptionTranslator DEFAULT_PERSISTENCE_EXCEPTION_TRANSLATOR = new FoundationDbPersistenceExceptionTranslator();

    private final PersistenceExceptionTranslator exceptionTranslator = DEFAULT_PERSISTENCE_EXCEPTION_TRANSLATOR;

    private final FoundationDbConverter foundationDbConverter;
    private final MappingContext<? extends FoundationDbPersistentEntity<?>, FoundationDbPersistentProperty> mappingContext;
    private final FoundationDbAdapter adapter;
    private final EntityOperations operations;

    public FoundationDbTemplate(FoundationDbFactory foundationDbFactory) {
        this(foundationDbFactory, null);
    }

    public FoundationDbTemplate(FoundationDbFactory foundationDbFactory, @Nullable FoundationDbConverter foundationDbConverter) {
        this(foundationDbFactory, foundationDbConverter, null);

    }

    public FoundationDbTemplate(FoundationDbFactory foundationDbFactory, @Nullable FoundationDbConverter foundationDbConverter, @Nullable FoundationDbMappingContext foundationDbMappingContext) {
        this.foundationDbConverter = foundationDbConverter == null ? getDefaultFoundationDbConverter() : foundationDbConverter;
        this.mappingContext = foundationDbMappingContext == null ? this.foundationDbConverter.getMappingContext() : foundationDbMappingContext;
        this.adapter = new FoundationDbAdapter(foundationDbFactory.getDb());
        this.operations = new EntityOperations(this.foundationDbConverter.getMappingContext());
    }


    @Override
    public <T> T findById(Object id, Class<T> javaType) {
        return null;// TODO implement
    }

    @Override
    public <T> T insert(T entity, String subspaceName) {
        return null; // TODO implement
    }

    @Override
    public <T> T save(T entity, String subspaceName) {

        Assert.notNull(entity, "Object to be saved must not be null!");
        Assert.notNull(subspaceName, "subspaceName of object to be saved must not be null");

        // wie wird aus der Entity etwas das ich speichern kann und hin und her mappen
        // foundationDbConverter.write(entity, new Tuple());

        Tuple key = generateKey(entity);
        Tuple value = generateValue(entity);

        execute((FoundationDbCallback<Void>) adapter -> {

            adapter.put(subspaceName, key, value);
            return null;
        });

        return entity;
    }

    private <T> Tuple generateValue(T entity) {
        return null; // TODO
    }

    private <T> Tuple generateKey(T entity) {
        return null; // TODO
    }

    /**
     * @param action must not be {@literal null}.
     * @param <T>
     * @return
     */
    @Nullable
    @Override
    public <T> T execute(FoundationDbCallback<T> action) {

        Assert.notNull(action, "FoundationDbCallback must not be null!");

        try {
            return action.doInFoundationDb(this.adapter);
        } catch (RuntimeException e) {
            throw resolveExceptionIfPossible(e);
        }
    }

    /**
     * Returns the underlying {@link FoundationDbConverter}
     *
     * @return
     */
    @Override
    public FoundationDbConverter getConverter() {
        return this.foundationDbConverter;
    }

    private static FoundationDbConverter getDefaultFoundationDbConverter() {

        FoundationDbCustomConversions conversions = new FoundationDbCustomConversions(Collections.emptyList());

        FoundationDbMappingContext mappingContext = new FoundationDbMappingContext();
        mappingContext.setSimpleTypeHolder(conversions.getSimpleTypeHolder());
        mappingContext.afterPropertiesSet();
        return new MappingFoundationDbConverter(mappingContext);

    }

    private RuntimeException resolveExceptionIfPossible(RuntimeException e) {

        DataAccessException translatedException = exceptionTranslator.translateExceptionIfPossible(e);
        return translatedException != null ? translatedException : e;
    }
}
