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

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.apple.foundationdb.Database;

import de.h2cl.spring.data.foundationdb.repository.FoundationDbRepository;

import lombok.Data;

/**
 * Integration tests for {@link FoundationDbRepositoriesRegistrar} with complete defaulting.
 *
 * @author Christoph Strobl
 * @author Mark Paluch
 */
@Ignore // TODO
@RunWith(SpringRunner.class)
@ContextConfiguration
public class FoundationDbRepositoryRegistrarWithFullDefaultingIntegrationTests {

    @Autowired
    PersonRepository repository;

    @Test
    public void shouldEnableFoundationDbRepositoryCorrectly() {
        assertThat(repository, notNullValue());
    }

    interface PersonRepository extends FoundationDbRepository<Person, String> {

        List<Person> findByFirstname(String firstname);
    }

    @Configuration
    @EnableFoundationDbRepositories(considerNestedRepositories = true)
    static class Config {
        @Bean
        public Database database() {
            return mock(Database.class);
        }
    }

    @Data
    static class Person {

        @Id
        String id;
        String firstname;

    }
}
