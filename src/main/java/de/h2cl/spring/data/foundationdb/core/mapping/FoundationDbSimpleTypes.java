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
package de.h2cl.spring.data.foundationdb.core.mapping;

import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.mapping.model.SimpleTypeHolder;

import com.apple.foundationdb.tuple.Tuple;
import com.apple.foundationdb.tuple.Versionstamp;

/**
 * Simple constant holder for a {@link SimpleTypeHolder} enriched with FoundationDb specific simple types.
 *
 * @author Oliver Gierke
 * @author Christoph Strobl
 * @author Martin Junker
 */
public abstract class FoundationDbSimpleTypes {

    static {
        Set<Class<?>> simpleTypes = new HashSet<>();
        simpleTypes.add(byte[].class);
        simpleTypes.add(BigInteger.class);
        simpleTypes.add(Boolean.class);
        simpleTypes.add(Double.class);
        simpleTypes.add(Float.class);
        simpleTypes.add(Long.class);
        simpleTypes.add(String.class);
        simpleTypes.add(Tuple.class);
        simpleTypes.add(UUID.class);
        simpleTypes.add(Versionstamp.class);

        FOUNDATION_SIMPLE_TYPES = Collections.unmodifiableSet(simpleTypes);
    }

    private static final Set<Class<?>> FOUNDATION_SIMPLE_TYPES;
    public static final SimpleTypeHolder FOUNDATION_SIMPLE_TYPES_HOLDER = new SimpleTypeHolder(FOUNDATION_SIMPLE_TYPES, true);

    private FoundationDbSimpleTypes() {
    }
}
