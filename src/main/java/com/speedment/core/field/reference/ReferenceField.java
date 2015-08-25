/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.core.field.reference;

import com.speedment.core.field.Field;
import com.speedment.core.field.StandardUnaryOperator;
import java.util.Objects;
import java.util.function.Function;

/**
 * This class represents a Reference Field. A Reference Field is something that
 * extends {@link Object}.
 *
 * @author pemi
 * @param <ENTITY> The entity type
 * @param <V> The value type
 */
public class ReferenceField<ENTITY, V> implements Field<ENTITY> {

    private final String columnName;
    private final Function<ENTITY, V> getter;

    public ReferenceField(String columnName, Function<ENTITY, V> getter) {
        this.columnName = Objects.requireNonNull(columnName);
        this.getter = Objects.requireNonNull(getter);
    }

    /**
     * Returns a {@link java.util.function.Predicate} that will evaluate to
     * {@code true}, if and only if this Field is {@code null}.
     *
     * @return a Predicate that will evaluate to {@code true}, if and only if
     * this Field is {@code null}
     */
    public ReferenceUnaryPredicateBuilder<ENTITY, V> isNull() {
        return newUnary(StandardUnaryOperator.IS_NULL);
    }

    /**
     * Returns a {@link java.util.function.Predicate} that will evaluate to
     * {@code true}, if and only if this Field is <em>not</em> {@code null}.
     *
     * @return a Predicate that will evaluate to {@code true}, if and only if
     * this Field is <em>not</em> {@code null}
     */
    public ReferenceUnaryPredicateBuilder<ENTITY, V> isNotNull() {
        return newUnary(StandardUnaryOperator.IS_NOT_NULL);
    }

    @Override
    public boolean isNullIn(ENTITY entity) {
        return getFrom(entity) == null;
    }

    public V getFrom(ENTITY entity) {
        return getter.apply(entity);
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    protected ReferenceUnaryPredicateBuilder<ENTITY, V> newUnary(StandardUnaryOperator unaryOperator) {
        return new ReferenceUnaryPredicateBuilder<>(this, unaryOperator);
    }

}
