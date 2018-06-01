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
package de.h2cl.spring.data.foundationdb.repository.core;

import org.springframework.data.mapping.context.MappingContext;

import de.h2cl.spring.data.foundationdb.repository.FoundationDbDatabaseFactory;
import de.h2cl.spring.data.foundationdb.repository.core.convert.FoundationDbConverter;
import de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbPersistentEntity;
import de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbPersistentProperty;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FoundationDbTemplate implements FoundationDbOperations {

    private final FoundationDbConverter foundationDbConverter;
    private final MappingContext<? extends FoundationDbPersistentEntity<?>, FoundationDbPersistentProperty> mappingContext;
    private final FoundationDbDatabaseFactory foundationDbDatabaseFactory;

    @Override
    public <T> T findById(Object id, Class<T> javaType) {
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
}
