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

import net.cuprak.graalvm.model.RaceParticipant;
import net.cuprak.graalvm.model.RoadRace;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Idea behind this class is that it invokes Python code to cleanup the participant data.
 * Since cleanupData() is a stateless session bean, the changes made by the Python code will be committed
 * to the database. The Python code is working directly on the Java Objects.
 * @author Ryan Cuprak
 */
@Stateless
public class DataCleanupBean {

    /**
     * Persistence context
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * GraalVM context
     */
    @Inject @PythonContext
    private Context context;

    /**
     * Cleanup method
     * Note, this is annotated with the GraalVMTimeout annotation for the TimeoutInterceptor. This interceptor
     * kills the context if it is taking too long to run.
     * Note, this code isn't perfect yet.
     */
    @GraalVMTimeout
    public void cleanupData() {
        TypedQuery<RoadRace> type = em.createNamedQuery("RoadRace.findAll",RoadRace.class);
        Value cleanupObj = context.eval("python","create()");
        List<RoadRace> races = type.getResultList();
        for(RoadRace race : races) {
            for(RaceParticipant participant : race.getParticipants()) {
                cleanupObj.getMember("cleanup").executeVoid(participant);
            }
        }
    }
}
