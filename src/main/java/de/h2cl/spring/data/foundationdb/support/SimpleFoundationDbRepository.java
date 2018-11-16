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
package de.h2cl.spring.data.foundationdb.support;

import de.h2cl.spring.data.foundationdb.core.FoundationDbOperations;
import de.h2cl.spring.data.foundationdb.query.FoundationDbEntityInformation;
import de.h2cl.spring.data.foundationdb.repository.FoundationDbRepository;
import org.springframework.data.util.Streamable;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository base implementation for FoundationDb.
 *
 * @author Oliver Gierke
 * @author Christoph Strobl
 * @author Thomas Darimont
 * @author Mark Paluch
 * @author Martin Junker
 */
public class SimpleFoundationDbRepository<T, ID> implements FoundationDbRepository<T, ID> {

    private final FoundationDbOperations foundationDbOperations;
    private final FoundationDbEntityInformation<T, ID> entityInformation;

    public SimpleFoundationDbRepository(FoundationDbEntityInformation<T, ID> metadata, FoundationDbOperations foundationDbOperations) {

        Assert.notNull(metadata, "FoundationDbEntityInformation must not be null!");
        Assert.notNull(foundationDbOperations, "FoundationDbOperations must not be null!");

        this.foundationDbOperations = foundationDbOperations;
        this.entityInformation = metadata;
    }


    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    @Override
    public <S extends T> S save(S entity) {

        Assert.notNull(entity, "Entity must not be null!");

        if (entityInformation.isNew(entity)) {
            return foundationDbOperations.insert(entity, entityInformation.getSubspaceName());
        }

        return foundationDbOperations.save(entity, entityInformation.getSubspaceName());
    }

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null}.
     * @return the saved entities will never be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {

        Assert.notNull(entities, "The given Iterable of entities not be null!");

        Streamable<S> source = Streamable.of(entities);
        boolean allNew = source.stream().allMatch(entityInformation::isNew);

        if (allNew) {

            List<S> result = source.stream().collect(Collectors.toList());
            return new ArrayList<>(foundationDbOperations.insert(result, entityInformation.getSubspaceName()));
        }

        return source.stream().map(this::save).collect(Collectors.toList());
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @Override
    public Optional<T> findById(ID id) {

        Assert.notNull(id, "Cannot get item with null id.");

        return Optional.ofNullable(
                foundationDbOperations.findById(id, entityInformation.getJavaType()));
    }

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @Override
    public boolean existsById(ID id) {

        Assert.notNull(id, "The given id must not be null!");

        return findById(id).isPresent();
    }

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @Override
    public Iterable<T> findAll() {
        return null; // TODO implement
    }

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return
     */
    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        return null; // TODO implement
    }

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities
     */
    @Override
    public long count() {
        return 0; // TODO implement
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    @Override
    public void deleteById(ID id) {
// TODO implement
    }

    /**
     * Deletes a given entity.
     *
     * @param entity
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public void delete(T entity) {
// TODO implement
    }

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    @Override
    public void deleteAll(Iterable<? extends T> entities) {
// TODO implement
    }

    /**
     * Deletes all entities managed by the repository.
     */
    @Override
    public void deleteAll() {
// TODO implement
    }
}
