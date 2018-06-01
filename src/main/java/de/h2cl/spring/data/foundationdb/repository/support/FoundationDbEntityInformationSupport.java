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
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbPersistentEntity;
import de.h2cl.spring.data.foundationdb.repository.query.FoundationDbEntityInformation;

/**
 * Support class responsible for creating {@link FoundationDbEntityInformation} instances for a given
 * {@link FoundationDbPersistentEntity}.
 *
 * @author Christoph Strobl
 * @author Mark Paluch
 */
public class FoundationDbEntityInformationSupport {

    private FoundationDbEntityInformationSupport() {
    }

    /**
     * Factory method for creating {@link FoundationDbEntityInformation}.
     *
     * @param entity must not be {@literal null}.
     * @param idType can be {@literal null}.
     * @return never {@literal null}.
     */
    @SuppressWarnings("unchecked")
    static <T, ID> FoundationDbEntityInformation<T, ID> entityInformationFor(FoundationDbPersistentEntity<?> entity,
                                                                             @Nullable Class<?> idType) {

        Assert.notNull(entity, "Entity must not be null!");

        MappingFoundationDbEntityInformation<T, ID> entityInformation = new MappingFoundationDbEntityInformation<>(
                (FoundationDbPersistentEntity<T>) entity, (Class<ID>) idType);

        return ClassUtils.isAssignable(Persistable.class, entity.getType())
                ? new PersistableFoundationDbEntityInformation<>(entityInformation) : entityInformation;
    }
}
