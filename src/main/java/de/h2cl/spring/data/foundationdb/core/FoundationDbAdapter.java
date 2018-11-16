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

import com.apple.foundationdb.Database;
import org.springframework.beans.factory.DisposableBean;

/**
 * {@link FoundationDbAdapter} unifies access and shields the underlying foundationDb specific implementation.
 */
public class FoundationDbAdapter implements DisposableBean {

    private final Database db;

    public FoundationDbAdapter(Database db) {
        this.db = db;
    }

    @Override
    public void destroy() throws Exception {
        db.close();
    }

    public <T> void put(T entity, String subspaceName) {
        // TODO
    }
}
