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
package de.h2cl.spring.data.foundationdb.core.convert;

import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbPersistentEntity;
import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbPersistentProperty;
import org.springframework.data.convert.EntityConverter;

import com.apple.foundationdb.tuple.Tuple;

import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbMappingContext;

/**
 * FoundationDb specific {@link EntityConverter}.
 *
 * @author Christoph Strobl
 * @author Mark Paluch
 */
public interface FoundationDbConverter extends EntityConverter<FoundationDbPersistentEntity<?>, FoundationDbPersistentProperty, Object, Tuple> {

    /*
     * (non-Javadoc)
     * @see org.springframework.data.convert.EntityConverter#getMappingContext()
     */
    @Override
    FoundationDbMappingContext getMappingContext();
}
