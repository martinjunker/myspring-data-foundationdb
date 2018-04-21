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
package de.h2cl.spring.data.foundationdb.repository.config;

import java.lang.annotation.Annotation;

import org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension;
import org.springframework.data.repository.config.RepositoryBeanDefinitionRegistrarSupport;
import org.springframework.data.repository.config.RepositoryConfigurationExtension;

/**
 * Special {@link KeyValueRepositoriesRegistrar} to point the infrastructure to inspect
 * {@link EnableFoundationDbRepositories}.
 *
 * @author Oliver Gierke
 * @author Neil Stevenson
 */
class FoundationDbRepositoriesRegistrar extends RepositoryBeanDefinitionRegistrarSupport {

    @Override
    protected Class<? extends Annotation> getAnnotation() {
        return EnableFoundationDbRepositories.class;
    }

    @Override
    protected RepositoryConfigurationExtension getExtension() {
        return new FoundationDbRepositoryConfigurationExtension();
    }

    /**
     * Hazelcast-specific {@link RepositoryConfigurationExtension}.
     *
     * @author Oliver Gierke
     */
    private static class FoundationDbRepositoryConfigurationExtension extends KeyValueRepositoryConfigurationExtension {

        /*
         * (non-Javadoc)
         * @see org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension
         *                          #getModuleName()
         */
        @Override
        public String getModuleName() {
            return "FoundationDb";
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension
         *                          #getModulePrefix()
         */
        @Override
        protected String getModulePrefix() {
            return "foundationdb";
        }

        /*
         * (non-Javadoc)
         * @see org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension
         *                          #getDefaultKeyValueTemplateRef()
         */
        @Override
        protected String getDefaultKeyValueTemplateRef() {
            return "keyValueTemplate";
        }
    }
}
