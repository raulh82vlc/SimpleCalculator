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

import javax.inject.Inject;

/**
 * Operations strategy implementation to calculate all kind of products prices when check out happens
 * @author Raul Hernandez Lopez.
 */

public class OperationsStrategyImpl implements OperationsStrategy {

    public static final String PLUS_CHAR = "+";
    public static final String MINUS_CHAR = "-";

    @Inject
    public OperationsStrategyImpl() {

    }

    @Override
    public float computeAddition(float value, float secondValue) {
        return value + secondValue;
    }

    @Override
    public float computeSubsctraction(float value, float secondValue) {
        return value - secondValue;
    }

    @Override
    public float computeResult(Queue<Float> values, Queue<String> operations) {
        return computeOperations(values, operations);
    }

    /**
     * Compute Operations with values
     */
    protected float computeOperations(Queue<Float> values, Queue<String> operations) {
        String operation;
        float calculation = 0;
        // base case when no operators on screen
        if (operations.size() == 0 && values.size() == 1) {
            return values.remove();
        }
        int i = 0;
        while (operations.size() > 0) {
            operation = operations.remove();
            switch (operation) {
                case MINUS_CHAR:
                    //first iteration two values can be used at the same time
                    if (i == 0 && values.size() >= 2) {
                        calculation = computeSubsctraction(values.remove(), values.remove());
                    } else if (values.size() > 0) {
                        calculation = computeSubsctraction(calculation, values.remove());
                    }
                    break;
                case PLUS_CHAR:
                default:
                    //first iteration two values can be used at the same time
                    if (i == 0 && values.size() >= 2) {
                        calculation = computeAddition(values.remove(), values.remove());
                    } else if (values.size() > 0) {
                        calculation = computeAddition(calculation, values.remove());
                    }
                    break;
            }
            i++;
        }
        return calculation;
    }
}
