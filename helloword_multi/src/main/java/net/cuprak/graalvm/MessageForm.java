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

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Form for prompting for a language/name
 * @author Ryan Cuprak
 */
@Named
@SessionScoped
public class MessageForm implements Serializable {

    /**
     * Name
     */
    private String name;

    /**
     * Language that
     */
    private String language;

    /**
     * Response
     */
    private String response;

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
     * Returns the language
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the language
     * @param language - language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Returns the response
     * @return response
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the response
     * @param response - response
     */
    public void setResponse(String response) {
        this.response = response;
    }
}
