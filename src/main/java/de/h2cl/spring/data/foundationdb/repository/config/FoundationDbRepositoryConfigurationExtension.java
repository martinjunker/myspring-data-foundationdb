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

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.data.config.ParsingUtils;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension;
import org.springframework.data.repository.config.RepositoryConfigurationSource;

import de.h2cl.spring.data.foundationdb.repository.FoundationDbDatabaseFactory;
import de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbMappingContext;
import de.h2cl.spring.data.foundationdb.repository.support.FoundationDbKeyValueAdapter;

/**
 * @author Christoph Strobl
 * @author Martin Junker
 */
public class FoundationDbRepositoryConfigurationExtension extends KeyValueRepositoryConfigurationExtension {

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
        return "foundationDbKeyValueTemplate";
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension#getDefaultKeyValueTemplateBeanDefinition()
     */
    @Override
    protected AbstractBeanDefinition getDefaultKeyValueTemplateBeanDefinition(
            RepositoryConfigurationSource configurationSource) {

        BeanDefinitionBuilder templateBuilder = BeanDefinitionBuilder.rootBeanDefinition(KeyValueTemplate.class);
        templateBuilder
                .addConstructorArgValue(getAdapterBeanDefinition(configurationSource))
                .addConstructorArgValue(new RootBeanDefinition(FoundationDbMappingContext.class));
        templateBuilder.setRole(BeanDefinition.ROLE_SUPPORT);
        return ParsingUtils.getSourceBeanDefinition(templateBuilder, configurationSource.getSource());
    }

    private AbstractBeanDefinition getAdapterBeanDefinition(RepositoryConfigurationSource configurationSource) {
        BeanDefinitionBuilder adapterBuilder = BeanDefinitionBuilder.rootBeanDefinition(FoundationDbKeyValueAdapter.class);
        adapterBuilder.addConstructorArgValue(getDatabaseFactoryBeanDefinition(configurationSource));
        return ParsingUtils.getSourceBeanDefinition(adapterBuilder, configurationSource.getSource());
    }

    private AbstractBeanDefinition getDatabaseFactoryBeanDefinition(RepositoryConfigurationSource configurationSource) {
        BeanDefinitionBuilder databaseFactoryBuilder = BeanDefinitionBuilder.rootBeanDefinition(FoundationDbDatabaseFactory.class);
        return ParsingUtils.getSourceBeanDefinition(databaseFactoryBuilder, configurationSource.getSource());
    }

}
