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
package de.h2cl.spring.data.foundationdb.repository.core.convert;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.convert.ConversionService;

import com.apple.foundationdb.tuple.Tuple;

import de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbMappingContext;

/**
 * {@link MappingFoundationDbConverter} implementation creating flat binary map structure out of a given domain type.
 *
 * @author Christoph Strobl
 * @author Greg Turnquist
 * @author Mark Paluch
 */
public class MappingFoundationDbConverter implements FoundationDbConverter, InitializingBean {

    private final FoundationDbMappingContext mappingContext;

    /**
     * Creates new {@link MappingFoundationDbConverter}.
     *
     * @param mappingContext can be {@literal null}.
     */
    public MappingFoundationDbConverter(FoundationDbMappingContext mappingContext) {
        this.mappingContext = mappingContext;
    }

    public FoundationDbMappingContext getMappingContext() {
        return mappingContext;
    }

    @Override
    public ConversionService getConversionService() {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }


    @Override
    public <R> R read(Class<R> type, Tuple source) {
        return null;
    }

    @Override
    public void write(Object source, Tuple sink) {

    }
}
