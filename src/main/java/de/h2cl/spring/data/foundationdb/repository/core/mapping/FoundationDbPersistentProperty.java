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
package de.h2cl.spring.data.foundationdb.repository.core.mapping;

import org.springframework.data.keyvalue.core.mapping.KeyValuePersistentProperty;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.data.mapping.model.Property;
import org.springframework.data.mapping.model.SimpleTypeHolder;

/**
 * FoundationDB specific {@link PersistentProperty} implementation.
 *
 * @author Christoph Strobl
 */
public class FoundationDbPersistentProperty extends KeyValuePersistentProperty<FoundationDbPersistentProperty> {

    /**
     * Creates new {@link FoundationDbPersistentProperty}.
     *
     * @param property
     * @param owner
     * @param simpleTypeHolder
     */
    public FoundationDbPersistentProperty(Property property, PersistentEntity<?, FoundationDbPersistentProperty> owner,
                                          SimpleTypeHolder simpleTypeHolder) {
        super(property, owner, simpleTypeHolder);
    }
}
