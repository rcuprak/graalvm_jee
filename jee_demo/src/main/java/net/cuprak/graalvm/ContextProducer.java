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
import org.graalvm.polyglot.Source;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.net.URL;

/**
 * Producer GraalVM contexts
 * This bean is deployed on startup so that we don't have to wait for GraalVM;
 * @author Ryan Cuprak
 */
@Startup
@Singleton
public class ContextProducer {

    /**
     * GraalVM context
     */
    private Context context;

    /**
     * Initializes the context
     */
    @PostConstruct
    public void init() {
        URL url = ContextProducer.class.getResource("/net/cuprak/scripts/Cleanup.py");
        context = Context.newBuilder().allowAllAccess(true).build();
        try {
            context.eval(Source.newBuilder("python",url).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a python context, note, for demo purposes I split the contexts along language lines.
     * @return python context
     */
    @Produces
    @PythonContext
    public Context getPythonContext() {
        return context;
    }
}
