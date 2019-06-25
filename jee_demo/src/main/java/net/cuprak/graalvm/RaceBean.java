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

import net.cuprak.graalvm.model.RoadRace;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Stateless EJB that manages races. Provides a basic almost implemented CRUD interface.
 * @author Ryan Cuprak
 */
@Stateless
public class RaceBean {

    /**
     * Persistence context
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Creates a race
     * @param roadRace - race to be persisted
     */
    public void createRace(RoadRace roadRace) {
        em.persist(roadRace);
    }

    /**
     * Returns a list of the races
     * @return races
     */
    public List<RoadRace> getRoadRaces() {
        TypedQuery<RoadRace> type = em.createNamedQuery("RoadRace.findAll",RoadRace.class);
        return type.getResultList();
    }

}
