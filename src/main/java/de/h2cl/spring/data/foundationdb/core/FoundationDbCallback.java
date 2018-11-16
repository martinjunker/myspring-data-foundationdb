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
package de.h2cl.spring.data.foundationdb.core;

import org.springframework.lang.Nullable;

/**
 * Generic callback interface for code that operates on a {@link FoundationDbAdapter}. This is particularly useful for
 * delegating code that needs to work closely on the underlying FoundationDb implementation.
 *
 * @param <T>
 * @author Christoph Strobl
 * @author Mark Paluch
 */
public interface FoundationDbCallback<T> {

    /**
     * Gets called by {@code FoundationDbTemplate#execute(FoundationDbCallback)}. Allows for returning a result object created
     * within the callback, i.e. a domain object or a collection of domain objects.
     *
     * @param adapter
     * @return
     */
    @Nullable
    T doInFoundationDb(FoundationDbAdapter adapter);

}
