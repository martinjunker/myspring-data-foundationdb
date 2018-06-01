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

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;

import com.apple.foundationdb.tuple.Tuple;

import de.h2cl.spring.data.foundationdb.repository.core.convert.converter.ObjectToTupleConverter;
import de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbMappingContext;
import de.h2cl.spring.data.foundationdb.repository.core.mapping.FoundationDbPersistentEntity;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link MappingFoundationDbConverter} implementation creating flat binary map structure out of a given domain type.
 *
 * @author Christoph Strobl
 * @author Greg Turnquist
 * @author Mark Paluch
 */
@Slf4j
public class MappingFoundationDbConverter implements FoundationDbConverter {

    private final FoundationDbMappingContext mappingContext;
    private final GenericConversionService conversionService;

    /**
     * Creates new {@link MappingFoundationDbConverter}.
     *
     * @param mappingContext can be {@literal null}.
     */
    public MappingFoundationDbConverter(FoundationDbMappingContext mappingContext) {
        this.mappingContext = mappingContext != null ? mappingContext : new FoundationDbMappingContext();
        this.conversionService = new DefaultConversionService();
        conversionService.addConverter(new ObjectToTupleConverter());
    }

    @Override
    public FoundationDbMappingContext getMappingContext() {
        return mappingContext;
    }

    @Override
    public ConversionService getConversionService() {
        return conversionService;
    }


    @Override
    public <R> R read(Class<R> type, Tuple source) {
        return null;
    }

    @Override
    public void write(Object source, Tuple sink) {

        FoundationDbPersistentEntity<?> entity = mappingContext.getPersistentEntity(source.getClass());

        sink.add(entity.getName()); // add class reference
        sink.addAll(conversionService.convert(source, Tuple.class));

    }
}
