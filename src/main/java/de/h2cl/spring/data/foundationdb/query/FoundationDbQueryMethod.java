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
package de.h2cl.spring.data.foundationdb.query;

import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbPersistentEntity;
import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbPersistentProperty;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

/**
 * FoundationDB specific implementation of {@link QueryMethod}.
 *
 * @author Oliver Gierke
 * @author Christoph Strobl
 * @author Mark Paluch
 */
public class FoundationDbQueryMethod extends QueryMethod {

    private final MappingContext<? extends FoundationDbPersistentEntity<?>, FoundationDbPersistentProperty> mappingContext;
    private final Method method;

    public FoundationDbQueryMethod(Method method, RepositoryMetadata metadata, ProjectionFactory projectionFactory, MappingContext<? extends FoundationDbPersistentEntity<?>, FoundationDbPersistentProperty> mappingContext) {
        super(method, metadata, projectionFactory);

        Assert.notNull(mappingContext, "MappingContext must not be null!");

        this.method = method;
        this.mappingContext = mappingContext;
    }
}
