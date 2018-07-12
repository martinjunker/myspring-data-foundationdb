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

import de.h2cl.spring.data.foundationdb.repository.FoundationDbRepository;
import de.h2cl.spring.data.foundationdb.repository.core.FoundationDbOperations;
import de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbPersistentEntity;
import de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbPersistentProperty;
import de.h2cl.spring.data.foundationdb.repository.query.FoundationDbEntityInformation;
import de.h2cl.spring.data.foundationdb.repository.query.FoundationDbQueryMethod;
import de.h2cl.spring.data.foundationdb.repository.query.PartTreeFoundationDbQuery;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Factory to create {@link FoundationDbRepository} instances.
 */
public class FoundationDbRepositoryFactory extends RepositoryFactorySupport {


    private final FoundationDbOperations operations;
    private final MappingContext<? extends FoundationDbPersistentEntity<?>, FoundationDbPersistentProperty> mappingContext;

    /**
     * Creates a new {@link FoundationDbRepositoryFactory} with the given {@link FoundationDbOperations}.
     *
     * @param foundationDbOperations must not be {@literal null}.
     */
    public FoundationDbRepositoryFactory(FoundationDbOperations foundationDbOperations) {

        Assert.notNull(foundationDbOperations, "FoundationDbOperations must not be null!");

        this.operations = foundationDbOperations;
        this.mappingContext = foundationDbOperations.getConverter().getMappingContext();
    }

    /**
     * Returns the {@link EntityInformation} for the given domain class.
     *
     * @param domainClass
     * @return
     */
    @Override
    public <T, ID> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return null;
    }

    /**
     * Create a repository instance as backing for the query proxy.
     *
     * @param metadata
     * @return
     */
    @Override
    protected Object getTargetRepository(RepositoryInformation metadata) {
        FoundationDbEntityInformation<?, Serializable> entityInformation = getEntityInformation(metadata.getDomainType(), metadata);
        return getTargetRepositoryViaReflection(metadata, entityInformation, operations);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getQueryLookupStrategy(org.springframework.data.repository.query.QueryLookupStrategy.Key, org.springframework.data.repository.query.EvaluationContextProvider)
     */
    @Override
    protected Optional<QueryLookupStrategy> getQueryLookupStrategy(@Nullable QueryLookupStrategy.Key key, EvaluationContextProvider evaluationContextProvider) {
        return Optional.of(new FoundationDbQueryLookupStrategy(operations, mappingContext));
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getRepositoryBaseClass(org.springframework.data.repository.core.RepositoryMetadata)
     */
    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        return SimpleFoundationDbRepository.class;
    }

    private <T, ID> FoundationDbEntityInformation<T, ID> getEntityInformation(Class<T> domainClass,
                                                                              @Nullable RepositoryMetadata metadata) {
        FoundationDbPersistentEntity<?> entity = mappingContext.getRequiredPersistentEntity(domainClass);
        return FoundationDbEntityInformationSupport.entityInformationFor(entity,
                metadata != null ? metadata.getIdType() : null);
    }

    /**
     * {@link QueryLookupStrategy} to create {@link org.springframework.data.repository.query.parser.PartTree} instances.
     *
     * @author Oliver Gierke
     * @author Thomas Darimont
     */
    private static class FoundationDbQueryLookupStrategy implements QueryLookupStrategy {

        private final FoundationDbOperations operations;
        MappingContext<? extends FoundationDbPersistentEntity<?>, FoundationDbPersistentProperty> mappingContext;

        public FoundationDbQueryLookupStrategy(FoundationDbOperations operations,
                                               MappingContext<? extends FoundationDbPersistentEntity<?>, FoundationDbPersistentProperty> mappingContext) {
            this.operations = operations;
            this.mappingContext = mappingContext;
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.data.repository.query.QueryLookupStrategy#resolveQuery(java.lang.reflect.Method, org.springframework.data.repository.core.RepositoryMetadata, org.springframework.data.projection.ProjectionFactory, org.springframework.data.repository.core.NamedQueries)
         */
        @Override
        public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory, NamedQueries namedQueries) {
            FoundationDbQueryMethod queryMethod = new FoundationDbQueryMethod(method, metadata, factory, mappingContext);
            return new PartTreeFoundationDbQuery(queryMethod, operations);
        }
    }
}
