package org.ff4j.audit;

/*
 * #%L
 * ff4j-core
 * %%
 * Copyright (C) 2013 - 2016 FF4J
 * %%
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
 * #L%
 */


import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Handle Rejects when publishing event.
 *
 * @author Cedrick Lunven (@clunven)</a>
 */
public class EventRejectedExecutionHandler implements RejectedExecutionHandler {

    /** {@inheritDoc} */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            this.waitInSeconds(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        // try once again
        executor.execute(r);
    }
    
    /**
     * Wait, extracted method to easy tests.
     *
     * @param nbSecond
     *      number of seconds to wait.
     * @throws InterruptedException
     *      interupted
     */
    public void waitInSeconds(int nbSecond) throws InterruptedException {
        Thread.sleep(1000 * nbSecond);
    }

}