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

package com.raulh82vlc.BasicCalculator.domain.executors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * ThreadsPoolExecutor is the interactor's handler and has settings for the pool.
 * <p/> {@link ThreadPoolExecutor} is the Interactor executor implementation.<p/>
 *
 * @author Raul Hernandez Lopez
 */
public class ThreadsPoolExecutor implements InteractorExecutor {
    /**
     * Constants
     */
    private static final int MAX_SIZE = 6;
    private static final int CORE_DEFAULT_SIZE = Runtime.getRuntime().availableProcessors();
    private static final int TIME_OUT_TIME = 300;
    private static final TimeUnit TIME_UNITS = TimeUnit.SECONDS;
    /**
     * Local variables
     */
    private ThreadPoolExecutor threadsPoolExecutor;


    @Inject
    ThreadsPoolExecutor() {
        BlockingQueue<Runnable> workersQueue = new LinkedBlockingQueue<>();
        threadsPoolExecutor = new ThreadPoolExecutor(CORE_DEFAULT_SIZE, MAX_SIZE,
                TIME_OUT_TIME, TIME_UNITS, workersQueue);
    }

    @Override
    public void run(final Interactor interactor) {
        if (interactor == null) {
            throw new IllegalArgumentException("Interactor must be instantiated");
        }
        threadsPoolExecutor.submit(new Runnable() {
            @Override
            public void run() {
            interactor.run();
            }
        });
    }
}
