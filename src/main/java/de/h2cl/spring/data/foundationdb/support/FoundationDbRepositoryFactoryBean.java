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


import de.h2cl.spring.data.foundationdb.repository.FoundationDbRepository;
import de.h2cl.spring.data.foundationdb.core.FoundationDbOperations;
import org.springframework.data.mapping.context.MappingContext;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * {@link org.springframework.beans.factory.FactoryBean} to create {@link FoundationDbRepository} instances.
 *
 * @author Oliver Gierke
 */
public class FoundationDbRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
        extends RepositoryFactoryBeanSupport<T, S, ID> {

    private @Nullable
    FoundationDbOperations operations;

    private boolean mappingContextConfigured = false;

    /**
     * Creates a new {@link FoundationDbRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public FoundationDbRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory() {
        return new FoundationDbRepositoryFactory(operations);
    }

    /**
     * Configures the {@link FoundationDbOperations} to be used.
     *
     * @param operations the operations to set
     */
    public void setFoundationDbOperations(FoundationDbOperations operations) {
        this.operations = operations;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport#setMappingContext(org.springframework.data.mapping.context.MappingContext)
     */
    @Override
    protected void setMappingContext(MappingContext<?, ?> mappingContext) {

        super.setMappingContext(mappingContext);
        this.mappingContextConfigured = true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.data.repository.support.RepositoryFactoryBeanSupport
     * #afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() {

        super.afterPropertiesSet();
        Assert.state(operations != null, "FoundationDbOperations must not be null!");

        if (!mappingContextConfigured) {
            setMappingContext(operations.getConverter().getMappingContext());
        }
    }
}


