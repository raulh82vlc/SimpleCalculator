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

package com.raulh82vlc.BasicCalculator.domain.interactors.calculate;

import com.raulh82vlc.BasicCalculator.domain.executors.Interactor;
import com.raulh82vlc.BasicCalculator.domain.executors.InteractorExecutor;
import com.raulh82vlc.BasicCalculator.domain.executors.MainThread;
import com.raulh82vlc.BasicCalculator.domain.interactors.OperationsStrategy;

import java.util.Queue;

import javax.inject.Inject;

/**
 * Implementation of Checkout of the Shopping List Interactor
 *
 * @author Raul Hernandez Lopez
 */
public class CalculateOperationsInteractorImpl implements CalculateOperationsInteractor, Interactor {

    final private InteractorExecutor executor;
    final private MainThread mainThread;
    private CalculatorResultCallback callback;
    // Strategy to checkout with discounts or without any
    private final OperationsStrategy operationsStrategy;
    private Queue<String> operations;
    private Queue<Float> values;

    @Inject
    public CalculateOperationsInteractorImpl(InteractorExecutor executor,
                                             MainThread mainThread,
                                             OperationsStrategy operationsStrategy) {
        this.executor = executor;
        this.mainThread = mainThread;
        this.operationsStrategy = operationsStrategy;
    }

    @Override
    public void execute(Queue<String> operations, Queue<Float> values, CalculatorResultCallback callback) {
        this.callback = callback;
        this.operations = operations;
        this.values = values;
        executor.run(this);
    }

    @Override
    public void run() {
        notifySuccessfullyCheckedOut(operationsStrategy.computeResult(values, operations));
    }


    /**
     * <p>Notifies to the UI (main) thread the result of checkout,
     * and sends a callback the string</p>
     */
    private void notifySuccessfullyCheckedOut(final float result) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onComputedResultOK(result);
            }
        });
    }
}
