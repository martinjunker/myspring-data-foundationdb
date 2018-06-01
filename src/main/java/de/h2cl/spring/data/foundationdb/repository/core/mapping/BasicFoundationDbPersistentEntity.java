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

import org.springframework.data.mapping.model.BasicPersistentEntity;
import org.springframework.data.util.TypeInformation;

/**
 * {@link FoundationDbPersistentEntity} implementation.
 *
 * @param <T>
 * @author Christoph Strobl
 * @author Mark Paluch
 */
public class BasicFoundationDbPersistentEntity<T> extends BasicPersistentEntity<T, FoundationDbPersistentProperty>
        implements FoundationDbPersistentEntity<T> {

    /**
     * @param information must not be {@literal null}.
     */
    public BasicFoundationDbPersistentEntity(TypeInformation<T> information) {
        super(information, null);
    }
}