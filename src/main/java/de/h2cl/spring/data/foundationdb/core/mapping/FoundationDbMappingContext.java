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
package de.h2cl.spring.data.foundationdb.core.mapping;

import org.springframework.data.mapping.context.AbstractMappingContext;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mapping.model.Property;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.util.TypeInformation;

/**
 * FoundationDB specific {@link MappingContext}.
 *
 * @author Christoph Strobl
 * @author Oliver Gierke
 */
public class FoundationDbMappingContext extends AbstractMappingContext<BasicFoundationDbPersistentEntity<?>, FoundationDbPersistentProperty> {

    public FoundationDbMappingContext() {
        setSimpleTypeHolder(FoundationDbSimpleTypes.FOUNDATION_SIMPLE_TYPES_HOLDER);
    }

    @Override
    protected <T> BasicFoundationDbPersistentEntity<T> createPersistentEntity(TypeInformation<T> typeInformation) {
        return new BasicFoundationDbPersistentEntity<>(typeInformation);
    }

    @Override
    protected FoundationDbPersistentProperty createPersistentProperty(Property property, BasicFoundationDbPersistentEntity<?> owner, SimpleTypeHolder simpleTypeHolder) {
        return new CachingFoundationDbPersistentProperty(property, owner, simpleTypeHolder);
    }
}
