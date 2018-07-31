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

import de.h2cl.spring.data.foundationdb.FoundationDbFactory;
import de.h2cl.spring.data.foundationdb.core.convert.FoundationDbConverter;
import de.h2cl.spring.data.foundationdb.core.convert.FoundationDbCustomConversions;
import de.h2cl.spring.data.foundationdb.core.convert.MappingFoundationDbConverter;
import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbMappingContext;
import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbPersistentEntity;
import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbPersistentProperty;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.lang.Nullable;

import java.util.Collections;

/**
 * FoundationDbTemplate
 *
 * @author Martin Junker
 */
public class FoundationDbTemplate implements FoundationDbOperations {

    private final FoundationDbFactory foundationDbFactory;
    private final FoundationDbConverter foundationDbConverter;
    private final MappingContext<? extends FoundationDbPersistentEntity<?>, FoundationDbPersistentProperty> mappingContext;

    public FoundationDbTemplate(FoundationDbFactory foundationDbFactory) {
        this(foundationDbFactory, null);
    }

    public FoundationDbTemplate(FoundationDbFactory foundationDbFactory, @Nullable FoundationDbConverter foundationDbConverter) {
        this(foundationDbFactory, foundationDbConverter, null);

    }

    public FoundationDbTemplate(FoundationDbFactory foundationDbFactory, @Nullable FoundationDbConverter foundationDbConverter, @Nullable FoundationDbMappingContext foundationDbMappingContext) {
        this.foundationDbFactory = foundationDbFactory;
        this.foundationDbConverter = foundationDbConverter == null ? getDefaultFoundationDbConverter() : foundationDbConverter;
        this.mappingContext = foundationDbMappingContext == null ? this.foundationDbConverter.getMappingContext() : foundationDbMappingContext;
    }


    @Override
    public <T> T findById(Object id, Class<T> javaType) {
        return null;
    }

    @Override
    public <T> T insert(T entity, String subspaceName) {
        return null;
    }

    @Override
    public <T> T save(T entity, String subspaceName) {
        return null;
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
}
