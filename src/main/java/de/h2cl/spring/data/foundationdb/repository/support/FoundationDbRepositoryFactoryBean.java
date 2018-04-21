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

import org.springframework.data.keyvalue.repository.support.KeyValueRepositoryFactoryBean;
import org.springframework.data.repository.Repository;

import de.h2cl.spring.data.foundationdb.repository.FoundationDbRepository;

/**
 * {@link org.springframework.beans.factory.FactoryBean} to create {@link FoundationDbRepository} instances.
 */
public class FoundationDbRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
        extends KeyValueRepositoryFactoryBean<T, S, ID> {

    public FoundationDbRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

}


