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

import de.h2cl.spring.data.foundationdb.core.FoundationDbOperations;
import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbPersistentProperty;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.util.Assert;

public class PartTreeFoundationDbQuery implements RepositoryQuery {

    private final MappingContext<?, FoundationDbPersistentProperty> mappingContext;

    private final FoundationDbQueryMethod queryMethod;
    private final FoundationDbOperations operations;

    public PartTreeFoundationDbQuery(FoundationDbQueryMethod queryMethod, FoundationDbOperations operations) {

        Assert.notNull(operations, "FoundationDbOperations must not be null!");
        Assert.notNull(queryMethod, "FoundationDbQueryMethod must not be null!");

        this.queryMethod = queryMethod;
        this.operations = operations;

        mappingContext= operations.getConverter().getMappingContext();
    }

    @Override
    public Object execute(Object[] objects) {
        return null;
    }

    @Override
    public QueryMethod getQueryMethod() {
        return null;
    }
}
