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
package de.h2cl.spring.data.foundationdb.config;

import com.apple.foundationdb.Database;
import de.h2cl.spring.data.foundationdb.FoundationDbFactory;
import de.h2cl.spring.data.foundationdb.core.FoundationDbTemplate;
import de.h2cl.spring.data.foundationdb.core.SimpleFoundationDbFactory;
import de.h2cl.spring.data.foundationdb.core.convert.FoundationDbCustomConversions;
import de.h2cl.spring.data.foundationdb.core.convert.MappingFoundationDbConverter;
import de.h2cl.spring.data.foundationdb.core.mapping.Document;
import de.h2cl.spring.data.foundationdb.core.mapping.FoundationDbMappingContext;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.convert.CustomConversions;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
public abstract class AbstractFoundationDbConfiguration {

    /**
     * Return the {@link Database} instance to connect to. Annotate with {@link Bean} in case you want to expose a
     * {@link Database} instance to the {@link org.springframework.context.ApplicationContext}.
     *
     * @return
     */
    public abstract Database foundationDb();

    /**
     * Creates a {@link FoundationDbTemplate}.
     *
     * @return
     */
    @Bean
    public FoundationDbTemplate foundationDbTemplate() throws Exception {
        return new FoundationDbTemplate(foundationDbDatabaseFactory(), mappingFoundationDbConverter(), foundationDbMappingContext());
    }

    /**
     * Creates a {@link FoundationDbMappingContext} equipped with entity classes scanned from the mapping base package.
     *
     * @return
     * @throws ClassNotFoundException
     * @see #getMappingBasePackages()
     */
    private FoundationDbMappingContext foundationDbMappingContext() throws ClassNotFoundException {
        FoundationDbMappingContext mappingContext = new FoundationDbMappingContext();
        mappingContext.setInitialEntitySet(getInitialEntitySet());
        mappingContext.setSimpleTypeHolder(customConversions().getSimpleTypeHolder());
        // mappingContext.setFieldNamingStrategy(fieldNamingStrategy());

        return mappingContext;
    }

    /**
     * Creates a {@link SimpleFoundationDbFactory} to be used by the {@link FoundationDbTemplate}. Will use the {@link Database}
     * instance configured in {@link #foundationDb()}.
     *
     * @return
     * @see #foundationDb()
     * @see #foundationDbTemplate()
     */
    @Bean
    public FoundationDbFactory foundationDbDatabaseFactory() {
        return new SimpleFoundationDbFactory();
    }


    /**
     * Register custom {@link Converter}s in a {@link CustomConversions} object if required. These
     * {@link CustomConversions} will be registered with the {@link #mappingFoundationDbConverter()} and
     * {@link #foundationDbMappingContext()}. Returns an empty {@link FoundationDbCustomConversions} instance by default.
     *
     * @return must not be {@literal null}.
     */
    @Bean
    public CustomConversions customConversions() {
        return new FoundationDbCustomConversions(Collections.emptyList());
    }

    /**
     * Returns the base packages to scan for FoundationDB mapped entities at startup. Will return the package name of the
     * configuration class' (the concrete class, not this one here) by default. So if you have a
     * {@code com.acme.AppConfig} extending {@link FoundationDbConfigurationSupport} the base package will be considered
     * {@code com.acme} unless the method is overridden to implement alternate behavior.
     *
     * @return the base packages to scan for mapped {@link Document} classes or an empty collection to not enable scanning
     * for entities.
     * @since 1.10
     */
    protected Collection<String> getMappingBasePackages() {

        Package mappingBasePackage = getClass().getPackage();
        return Collections.singleton(mappingBasePackage == null ? null : mappingBasePackage.getName());
    }

    /**
     * Creates a {@link MappingFoundationDbConverter} using the configured {@link #foundationDbFactory()} and
     * {@link #foundationDbMappingContext()}. Will get {@link #customConversions()} applied.
     *
     * @return
     * @throws Exception
     * @see #customConversions()
     * @see #foundationDbMappingContext()
     * @see #foundationDbFactory()
     */
    @Bean
    public MappingFoundationDbConverter mappingFoundationDbConverter() throws Exception {

        MappingFoundationDbConverter converter = new MappingFoundationDbConverter(foundationDbMappingContext());
        //converter.setCustomConversions(customConversions());

        return converter;
    }

    /**
     * Scans the mapping base package for classes annotated with {@link Document}. By default, it scans for entities in
     * all packages returned by {@link #getMappingBasePackages()}.
     *
     * @return
     * @throws ClassNotFoundException
     * @see #getMappingBasePackages()
     */
    protected Set<Class<?>> getInitialEntitySet() throws ClassNotFoundException {

        Set<Class<?>> initialEntitySet = new HashSet<>();

        for (String basePackage : getMappingBasePackages()) {
            initialEntitySet.addAll(scanForEntities(basePackage));
        }

        return initialEntitySet;
    }

    /**
     * Scans the given base package for entities, i.e. FoundationDB specific types annotated with {@link Document} and
     * {@link Persistent}.
     *
     * @param basePackage must not be {@literal null}.
     * @return
     * @throws ClassNotFoundException
     * @since 1.10
     */
    protected Set<Class<?>> scanForEntities(String basePackage) throws ClassNotFoundException {

        if (!StringUtils.hasText(basePackage)) {
            return Collections.emptySet();
        }

        Set<Class<?>> initialEntitySet = new HashSet<>();

        if (StringUtils.hasText(basePackage)) {

            ClassPathScanningCandidateComponentProvider componentProvider = new ClassPathScanningCandidateComponentProvider(
                    false);
            componentProvider.addIncludeFilter(new AnnotationTypeFilter(Document.class));
            componentProvider.addIncludeFilter(new AnnotationTypeFilter(Persistent.class));

            for (BeanDefinition candidate : componentProvider.findCandidateComponents(basePackage)) {

                initialEntitySet
                        .add(ClassUtils.forName(candidate.getBeanClassName(), AbstractFoundationDbConfiguration.class.getClassLoader()));
            }
        }

        return initialEntitySet;
    }
}
