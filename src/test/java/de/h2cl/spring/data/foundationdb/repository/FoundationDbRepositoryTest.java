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
package de.h2cl.spring.data.foundationdb.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.keyvalue.core.KeyValueOperations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import de.h2cl.spring.data.foundationdb.repository.config.EnableFoundationDbRepositories;
import de.h2cl.spring.data.foundationdb.repository.config.FoundationDbConfiguration;
import de.h2cl.spring.data.foundationdb.repository.support.sample.Offer;

@RunWith(SpringRunner.class)
@DirtiesContext
@ContextConfiguration(classes = {FoundationDbConfiguration.class})
@EnableFoundationDbRepositories(basePackageClasses = FoundationDbRepository.class)
public class FoundationDbRepositoryTest {

    @Autowired
    KeyValueOperations keyValueTemplate;

    @Test
    public void simpleCrudTest() {
        keyValueTemplate.findById("ID_1", Offer.class);
        //keyValueTemplate.insert(new Offer("ID_1"));
    }
}