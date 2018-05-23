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
import org.springframework.data.config.ParsingUtils;
import org.springframework.data.keyvalue.core.KeyValueTemplate;
import org.springframework.data.keyvalue.repository.config.KeyValueRepositoryConfigurationExtension;
import org.springframework.data.map.MapKeyValueAdapter;
import org.springframework.data.repository.config.RepositoryConfigurationSource;

/**
 *
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

        BeanDefinitionBuilder adapterBuilder = BeanDefinitionBuilder.rootBeanDefinition(MapKeyValueAdapter.class);

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(KeyValueTemplate.class);
        builder
                .addConstructorArgValue(ParsingUtils.getSourceBeanDefinition(adapterBuilder, configurationSource.getSource()));
        builder.setRole(BeanDefinition.ROLE_SUPPORT);

        return ParsingUtils.getSourceBeanDefinition(builder, configurationSource.getSource());
    }
}
