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

package com.raulh82vlc.BasicCalculator.domain.interactors.parse;

import com.raulh82vlc.BasicCalculator.domain.executors.Interactor;
import com.raulh82vlc.BasicCalculator.domain.executors.InteractorExecutor;
import com.raulh82vlc.BasicCalculator.domain.executors.MainThread;

import java.util.LinkedList;
import java.util.Queue;

import javax.inject.Inject;

/**
 * Implementation of {@link ParseInputInteractor}
 *
 * @author Raul Hernandez Lopez
 */
public class ParseInputInteractorImpl implements ParseInputInteractor, Interactor {

    final private InteractorExecutor executor;
    final private MainThread mainThread;
    private ParseResultCallback callback;
    // Strategy to parse input into understandable outputs
    private final ParseStrategy parseStrategy;
    private String input;

    @Inject
    public ParseInputInteractorImpl(InteractorExecutor executor,
                                    MainThread mainThread,
                                    ParseStrategy parseStrategy) {
        this.executor = executor;
        this.mainThread = mainThread;
        this.parseStrategy = parseStrategy;
    }

    @Override
    public void execute(String input, ParseResultCallback callback) {
        this.callback = callback;
        this.input = input;
        executor.run(this);
    }

    @Override
    public void run() {
        Queue<Float> values = new LinkedList<>();
        Queue<String> operations = new LinkedList<>();
        parseStrategy.parseInputToTokens(input, values, operations);
        notifySuccessfullyCheckedOut(values, operations);
    }

    /**
     * <p>Notifies to the UI (main) thread the result of checkout,
     * and sends a callback the string</p>
     */
    private void notifySuccessfullyCheckedOut(final Queue<Float> values, final Queue<String> operations) {
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onResultParsed(operations, values);
            }
        });
    }
}
