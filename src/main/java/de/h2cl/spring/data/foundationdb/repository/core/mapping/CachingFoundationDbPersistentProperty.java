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
package de.h2cl.spring.data.foundationdb.repository.core.mapping;

import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.model.Property;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.lang.Nullable;

/**
 * {@link FoundationDbPersistentProperty} caching access to {@link #isIdProperty()} and {@link #getFieldName()}.
 *
 * @author Oliver Gierke
 */
public class CachingFoundationDbPersistentProperty extends BasicFoundationDbPersistentProperty {

    private @Nullable
    Boolean isIdProperty;
    private @Nullable
    Boolean isAssociation;
    private @Nullable
    String fieldName;
    private @Nullable
    Boolean usePropertyAccess;
    private @Nullable
    Boolean isTransient;

    /**
     * Creates a new {@link CachingFoundationDbPersistentProperty}.
     *
     * @param property         must not be {@literal null}.
     * @param owner            must not be {@literal null}.
     * @param simpleTypeHolder
     */
    public CachingFoundationDbPersistentProperty(Property property, PersistentEntity<?, FoundationDbPersistentProperty> owner, SimpleTypeHolder simpleTypeHolder) {
        super(property, owner, simpleTypeHolder);
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mapping.PersistentProperty#isIdProperty()
     */
    @Override
    public boolean isIdProperty() {

        if (this.isIdProperty == null) {
            this.isIdProperty = super.isIdProperty();
        }

        return this.isIdProperty;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mapping.model.AnnotationBasedPersistentProperty.isAssociation
     */
    @Override
    public boolean isAssociation() {
        if (this.isAssociation == null) {
            this.isAssociation = super.isAssociation();
        }
        return this.isAssociation;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mapping.model.AnnotationBasedPersistentProperty#usePropertyAccess()
     */
    @Override
    public boolean usePropertyAccess() {

        if (this.usePropertyAccess == null) {
            this.usePropertyAccess = super.usePropertyAccess();
        }

        return this.usePropertyAccess;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.mapping.model.AnnotationBasedPersistentProperty#isTransient()
     */
    @Override
    public boolean isTransient() {

        if (this.isTransient == null) {
            this.isTransient = super.isTransient();
        }

        return this.isTransient;
    }
}
