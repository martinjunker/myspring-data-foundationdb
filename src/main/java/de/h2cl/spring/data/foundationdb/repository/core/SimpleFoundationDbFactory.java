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
package de.h2cl.spring.data.foundationdb.repository.core;

import com.apple.foundationdb.Database;
import com.apple.foundationdb.FDB;

import de.h2cl.spring.data.foundationdb.repository.FoundationDbFactory;

/**
 * SimpleFoundationDbFactory
 *
 * @author Martin Junker
 */
public class SimpleFoundationDbFactory implements FoundationDbFactory {

    private static final int FOUNDATION_DB_API_VERSION = 510;

    private final FDB fdb = FDB.selectAPIVersion(FOUNDATION_DB_API_VERSION);

    @Override
    public Database getDb() {
        return fdb.open();
    }
}
