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
package de.h2cl.spring.data.foundationdb.support;

import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.repository.core.support.PersistentEntityInformation;
import org.springframework.lang.Nullable;

import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbPersistentEntity;
import de.h2cl.spring.data.foundationdb.query.FoundationDbEntityInformation;

/**
 * {@link FoundationDbEntityInformation} implementation using a {@link FoundationDbPersistentEntity} instance to lookup the necessary
 * information. Can be configured with a custom collection to be returned which will trump the one returned by the
 * {@link FoundationDbPersistentEntity} if given.
 *
 * @author Oliver Gierke
 * @author Christoph Strobl
 * @author Mark Paluch
 */
public class MappingFoundationDbEntityInformation<T, ID> extends PersistentEntityInformation<T, ID>
        implements FoundationDbEntityInformation<T, ID> {

    private final FoundationDbPersistentEntity<T> entityMetadata;
    private final @Nullable
    String customSubspaceName;
    private final Class<ID> fallbackIdType;



    /**
     * Creates a new {@link PersistentEntityInformation} for the given {@link PersistentEntity}.
     *
     * @param entity               must not be {@literal null}.
     * @param customSubspaceName
     */
    public MappingFoundationDbEntityInformation(FoundationDbPersistentEntity<T> entity, @Nullable String customSubspaceName, @Nullable Class<ID> fallbackIdType) {
        super(entity);

        this.entityMetadata = entity;
        this.customSubspaceName = customSubspaceName;
        this.fallbackIdType = fallbackIdType;
    }

    /**
     * Creates a new {@link MappingFoundationDbEntityInformation} for the given {@link FoundationDbPersistentEntity} and fallback
     * identifier type.
     *
     * @param entity must not be {@literal null}.
     * @param fallbackIdType can be {@literal null}.
     */
    public MappingFoundationDbEntityInformation(FoundationDbPersistentEntity<T> entity,  @Nullable Class<ID> fallbackIdType) {
      this(entity, null, fallbackIdType);
    }

    @Override
    public String getSubspaceName() {
        return customSubspaceName == null ? entityMetadata.getSubspace() : customSubspaceName;
    }
}
