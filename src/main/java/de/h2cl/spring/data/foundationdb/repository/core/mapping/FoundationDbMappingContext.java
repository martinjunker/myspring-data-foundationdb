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

import static de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbSimpleTypes.FOUNDATION_SIMPLE_TYPES_HOLDER;

import org.springframework.data.keyvalue.core.mapping.context.KeyValueMappingContext;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.mapping.model.SimpleTypeHolder;

/**
 * FoundationDB specific {@link MappingContext}.
 *
 * @author Christoph Strobl
 * @author Oliver Gierke
 */
public class FoundationDbMappingContext extends KeyValueMappingContext<FoundationDbPersistentEntity<?>, FoundationDbPersistentProperty> {

    private static final SimpleTypeHolder SIMPLE_TYPE_HOLDER = FOUNDATION_SIMPLE_TYPES_HOLDER;
}
