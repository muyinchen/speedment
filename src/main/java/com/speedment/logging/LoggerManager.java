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
package com.speedment.logging;

import com.speedment.core.platform.Speedment;
import com.speedment.core.platform.component.LoggerFactoryComponent;
import com.speedment.logging.impl.SystemOutLoggerFactory;

/**
 *
 * @author pemi
 */
public interface LoggerManager {

    enum Holder {
        INST;
        private LoggerFactory defaultFactory = new SystemOutLoggerFactory();
    }

    static void setFactory(LoggerFactory newFactory) {
        Holder.INST.defaultFactory = newFactory;
    }

    /**
     * Creates and returns a new <tt>Logger</tt> bound to the given
     * <tt>binding</tt> type using
     *
     * @param binding the <tt>java.lang.Class</tt> to bind to
     * @return the <b>new</b> <tt>Logger</tt> instance
     */
    static Logger getLogger(Class<?> binding) {
        return Holder.INST.defaultFactory.create(binding);
    }

    /**
     * Creates and returns a new <tt>Logger</tt> bound to the given
     * <tt>binding</tt> string using Platform's LoggerFactoryComponent.
     *
     * @param binding the <tt>java.lang.String</tt> to bind to
     * @return the <b>new</b> <tt>Logger</tt> instance
     */
    static Logger getLogger(String binding) {
        return Holder.INST.defaultFactory.create(binding);
    }

    /**
     * Creates and returns a new <tt>Logger</tt> bound to the given
     * <tt>binding</tt> type using Speedment's LoggerFactoryComponent.
     *
     * @param speedment instance to use
     * @param binding the <tt>java.lang.Class</tt> to bind to
     * @return the <b>new</b> <tt>Logger</tt> instance
     */
    static Logger getLogger(Speedment speedment, Class<?> binding) {
        return speedment.get(LoggerFactoryComponent.class).getLoggerFactory().create(binding);
    }

    /**
     * Creates and returns a new <tt>Logger</tt> bound to the given
     * <tt>binding</tt> string using Speedment's LoggerFactoryComponent.
     *
     * @param speedment instance to use
     * @param binding the <tt>java.lang.String</tt> to bind to
     * @return the <b>new</b> <tt>Logger</tt> instance
     */
    static Logger getLogger(Speedment speedment, String binding) {
        return speedment.get(LoggerFactoryComponent.class).getLoggerFactory().create(binding);
    }

}
