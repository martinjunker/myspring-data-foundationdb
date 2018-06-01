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

import org.springframework.data.domain.Persistable;

import de.h2cl.spring.data.foundationdb.repository.query.FoundationDbEntityInformation;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * {@link FoundationDbEntityInformation} implementation wrapping an existing {@link FoundationDbEntityInformation} considering
 * {@link Persistable} types by delegating {@link #isNew(Object)} and {@link #getId(Object)} to the corresponding
 * {@link Persistable#isNew()} and {@link Persistable#getId()} implementations.
 *
 * @author Christoph Strobl
 * @author Oliver Gierke
 */
@RequiredArgsConstructor
public class PersistableFoundationDbEntityInformation<T, ID> implements FoundationDbEntityInformation<T, ID> {

    private final @NonNull
    FoundationDbEntityInformation<T, ID> delegate;

    /**
     * Returns whether the given entity is considered to be new.
     *
     * @param entity must never be {@literal null}
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean isNew(T entity) {
        if (entity instanceof Persistable) {
            return ((Persistable<ID>) entity).isNew();
        }

        return delegate.isNew(entity);
    }

    /**
     * Returns the id of the given entity or {@literal null} if none can be obtained.
     *
     * @param entity must never be {@literal null}
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public ID getId(T entity) {
        if (entity instanceof Persistable) {
            return ((Persistable<ID>) entity).getId();
        }

        return delegate.getId(entity);
    }

    /**
     * Returns the type of the id of the entity.
     *
     * @return
     */
    @Override
    public Class<ID> getIdType() {
        return delegate.getIdType();
    }

    /**
     * Returns the actual domain class type.
     *
     * @return
     */
    @Override
    public Class<T> getJavaType() {
        return delegate.getJavaType();
    }
}
