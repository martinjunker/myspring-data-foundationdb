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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.data.convert.CustomConversions;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.mapping.model.SimpleTypeHolder;

/**
 * Value object to capture custom conversion. That is essentially a {@link List} of converters and some additional logic
 * around them.
 *
 * @author Mark Paluch
 * @since 2.0
 * @see org.springframework.data.convert.CustomConversions
 * @see SimpleTypeHolder
 */
public class FoundationDbCustomConversions extends CustomConversions {

    private static final StoreConversions STORE_CONVERSIONS;
    private static final List<Object> STORE_CONVERTERS;

    static {

        List<Object> converters = new ArrayList<>(Jsr310Converters.getConvertersToRegister());

        STORE_CONVERTERS = Collections.unmodifiableList(converters);
        STORE_CONVERSIONS = StoreConversions.of(SimpleTypeHolder.DEFAULT, STORE_CONVERTERS);
    }

    /**
     * Creates a new {@link FoundationDbCustomConversions} instance registering the given converters.
     *
     * @param converters       must not be {@literal null}.
     */
    public FoundationDbCustomConversions(Collection<?> converters) {
        super(STORE_CONVERSIONS, converters);
    }
}
