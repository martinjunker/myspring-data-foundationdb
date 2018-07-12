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

import static java.lang.Boolean.TRUE;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.UUID;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import de.h2cl.spring.data.foundationdb.repository.config.EnableFoundationDbRepositories;
import de.h2cl.spring.data.foundationdb.repository.core.FoundationDbTemplate;
import de.h2cl.spring.data.foundationdb.repository.core.SimpleFoundationDbFactory;
import de.h2cl.spring.data.foundationdb.repository.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

/**
 * IntegrationTest needs FoundationDB to be running
 */
@Ignore // TODO
@RunWith(SpringRunner.class)
@ContextConfiguration
public class FoundationDbRepositoryIT {

    private static final String ID = "ID_1";

    @Autowired
    FoundationDbRepository<Person, String> repository;

    @Test
    public void simpleCrudTest() {

        // assertThat("first call returns nothing", repository.findById(ID).isPresent(), is(FALSE));

        Person person = Person.builder()
                .id(ID)
                .firstname("martin")
                .build();
        repository.save(person);

        assertThat("second call returns something", repository.findById(ID).isPresent(), is(TRUE));
    }

    @Configuration
    @EnableFoundationDbRepositories(considerNestedRepositories = true)
    static class Config {

        @Bean
        public FoundationDbTemplate foundationDbTemplate() {
            return new FoundationDbTemplate(new SimpleFoundationDbFactory());
        }

    }

    @Data
    @Builder
    @Document
    static class Person {

        @Id
        String id;
        String firstname;
        UUID uuid;

    }

    interface PersonRepository extends FoundationDbRepository<Person, String> {

        //List<Person> findByFirstname(String firstname);
    }
}