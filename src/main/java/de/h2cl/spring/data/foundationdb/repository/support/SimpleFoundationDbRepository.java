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

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import de.h2cl.spring.data.foundationdb.repository.FoundationDbRepository;

/**
 * Repository base implementation for FoundationDb
 */
public class SimpleFoundationDbRepository<T, ID> implements FoundationDbRepository<T, ID> {

    @Override
    public Iterable<T> findAll(Sort sort) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> S save(S s) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<T> findById(ID id) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(ID id) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<T> findAll() {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterable<T> findAllById(Iterable<ID> iterable) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(ID id) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(T t) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        // TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteAll() {
        // TODO implement
        throw new UnsupportedOperationException();
    }
}
