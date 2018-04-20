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

import java.io.Serializable;

import org.springframework.data.repository.core.EntityInformation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.lang.Nullable;

import de.h2cl.spring.data.foundationdb.repository.FoundationDbRepository;
import de.h2cl.spring.data.foundationdb.repository.query.FoundationDbEntityInformation;

/**
 * Factory to create {@link FoundationDbRepository} instances.
 */
public class FoundationDbRepositoryFactory extends RepositoryFactorySupport {

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getEntityInformation(java.lang.Class)
     */
    @Override
    public <T, ID> EntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return getEntityInformation(domainClass, null);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.repository.core.support.RepositoryFactorySupport#getTargetRepository(org.springframework.data.repository.core.RepositoryInformation)
     */
    @Override
    protected Object getTargetRepository(RepositoryInformation information) {
        FoundationDbEntityInformation<?, Serializable> entityInformation = getEntityInformation(information.getDomainType(),
                information);
        return getTargetRepositoryViaReflection(information, entityInformation);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata repositoryMetadata) {
        return SimpleFoundationDbRepository.class;
    }


    private <T, ID> FoundationDbEntityInformation<T, ID> getEntityInformation(Class<T> domainClass,
                                                                              @Nullable RepositoryMetadata metadata) {
        // TODO implement
        throw new UnsupportedOperationException();
    }
}
