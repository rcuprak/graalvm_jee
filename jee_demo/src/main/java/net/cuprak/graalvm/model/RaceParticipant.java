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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Represents a race participant
 * @author Ryan Cuprak
 */
@Entity
public class RaceParticipant {

    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue
    private Long participantId;

    /**
     * Name
     */
    private String name;

    /**
     * Age
     */
    private int age;

    /**
     * Elapsed time
     */
    private int elapsedTime;

    /**
     * Last name
     */
    private String lastName;

    /**
     * No arg constructor
     */
    public RaceParticipant() {
        // empty
    }

    /**
     * Creates a new Race Participant
     * @param name - name
     * @param age - age
     * @param elapsedTime - elapsed time
     */
    public RaceParticipant(String name, int age, int elapsedTime) {
        this.name = name;
        this.age = age;
        this.elapsedTime = elapsedTime;
    }

    /**
     * Returns the participant id
     * @return participant id
     */
    public Long getParticipantId() {
        return participantId;
    }

    /**
     * Sets the participant id
     * @param participantId - participant id
     */
    public void setParticipantId(Long participantId) {
        this.participantId = participantId;
    }

    /**
     * Returns the name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     * @param name - name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns participant age
     * @return participant age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets participant age
     * @param age - age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the elapsed time
     * @return elapsed time
     */
    public int getElapsedTime() {
        return elapsedTime;
    }

    /**
     * Sets the elapsed time
     * @param elapsedTime - elapsed time
     */
    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    /**
     * Returns the participant last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the participant's last name
     * @param lastName - last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
