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
package net.cuprak.graalvm.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a road race
 * @author Ryan Cuprak
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "RoadRace.findAll", query = "SELECT r FROM RoadRace r"),
})
public class RoadRace {

    @Id
    @GeneratedValue
    private Long raceId;

    private String name;

    private double distanceKm;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="race_participants",
            joinColumns={@JoinColumn(name="participantId")},
            inverseJoinColumns={@JoinColumn(name="race_id")})
    private Set<RaceParticipant> participants = new HashSet<>();

    /**
     * No-arg constructor for JPA
     */
    public RoadRace() {
        // empty
    }

    /**
     * Creates a new RoadRace
     * @param name - name
     * @param distanceKm - distance
     */
    public RoadRace(String name, double distanceKm) {
        this.name = name;
        this.distanceKm = distanceKm;
    }

    public Long getRaceId() {
        return raceId;
    }

    public void setRaceId(Long raceId) {
        this.raceId = raceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(double distanceKm) {
        this.distanceKm = distanceKm;
    }

    public Set<RaceParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<RaceParticipant> participants) {
        this.participants = participants;
    }

    public void addParticipant(RaceParticipant raceParticipant) {
        this.participants.add(raceParticipant);
    }
}
