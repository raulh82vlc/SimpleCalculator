/*
 * Copyright (C) 2017 Raul Hernandez Lopez @raulh82vlc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.raulh82vlc.BasicCalculator.domain.interactors;

import java.util.Queue;

/**
 *
 * Operations strategy to compute values
 *
 * @author Raul Hernandez Lopez.
 */

public interface OperationsStrategy {

    /**
     * Compute an addition of two float values
     * @param value first value
     * @param secondValue second value
     * @return
     */
    float computeAddition(float value, float secondValue);

    /**
     * Compute a substraction of two float numbers
     * @param value first value
     * @param secondValue second value
     * @return
     */
    float computeSubsctraction(float value, float secondValue);

    /**
     * Compute to final result by means of the collection of operations and values
     * @param values
     * @param operations
     * @return
     */
    float computeResult(Queue<Float> values, Queue<String> operations);
}