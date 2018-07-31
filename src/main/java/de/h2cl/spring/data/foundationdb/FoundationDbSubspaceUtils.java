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
package de.h2cl.spring.data.foundationdb;

import org.springframework.util.StringUtils;

/**
 * Helper class featuring helper methods for working with FoundationDb subspaces.
 * <p/>
 *
 * @author Thomas Risberg
 */
public abstract class FoundationDbSubspaceUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private FoundationDbSubspaceUtils() {

    }

    /**
     * Obtains the subspace name to use for the provided class
     *
     * @param entityClass The class to determine the preferred subspace name for
     * @return The preferred subspace name
     */
    public static String getPreferredSubspaceName(Class<?> entityClass) {
        return StringUtils.uncapitalize(entityClass.getSimpleName());
    }
}
