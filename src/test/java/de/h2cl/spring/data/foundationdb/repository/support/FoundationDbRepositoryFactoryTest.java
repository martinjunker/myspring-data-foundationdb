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
package de.h2cl.spring.data.foundationdb.repository.support;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.repository.Repository;

import de.h2cl.spring.data.foundationdb.repository.support.sample.Offer;

@RunWith(MockitoJUnitRunner.class)
public class FoundationDbRepositoryFactoryTest {

    @Test
    public void createsRepositoryWithIdTypeLong() {

        FoundationDbRepositoryFactory factory = new FoundationDbRepositoryFactory();
        MyOfferRepository repository = factory.getRepository(MyOfferRepository.class);
        assertThat(repository, is(notNullValue()));
    }

    interface MyOfferRepository extends Repository<Offer, Long> {

    }
}
