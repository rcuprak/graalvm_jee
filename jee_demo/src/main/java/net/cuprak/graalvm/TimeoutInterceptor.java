/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.cuprak.graalvm;

import org.graalvm.polyglot.Context;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Kill the script if has been executing too long
 * @author Ryan Cuprak
 */
@Interceptor @GraalVMTimeout
public class TimeoutInterceptor {

    /**
     * Python Context
     */
    @Inject @PythonContext
    private Context context;

    /**
     * Monitors the
     * @param ctx - invocation context
     * @return whatever the wrapped method return
     * @throws Exception - thrown if there is an error
     */
    @AroundInvoke
    public Object monitorInvoation(InvocationContext ctx) throws Exception {
        Timer timer = new Timer("Timer");
        try {
            TimerTask task = new TimerTask() {
                public void run() {
                    context.close();
                }
            };
            timer.schedule(task, 10000L);
            return ctx.proceed();
        } finally {
            timer.cancel();
        }
    }
}
