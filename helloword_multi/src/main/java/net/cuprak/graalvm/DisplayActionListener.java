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

import javax.inject.Inject;
import javax.inject.Named;

/**
 * CDI bean - invoked via AJAX when user clicks on "Display"
 */
@Named
public class DisplayActionListener {

    @Inject
    private MessageForm form;

    @Inject
    private PolygotHelloWorld helloWorldBean;

    /**
     * Invoked from the display button
     */
    public void updateMessage() {
        String msg = "";
        if(form.getName() != null) {
            switch(form.getLanguage()) {
                case "JavaScript": {
                    msg = helloWorldBean.getJavaScriptMsg(form.getName());
                    break;
                }
                case "R": {
                    msg = helloWorldBean.getRMsg(form.getName());
                    break;
                }
                case "Python": {
                    msg = helloWorldBean.getPythonMsg(form.getName());
                    break;
                }
                case "Ruby": {
                    msg = helloWorldBean.getRubyMsg(form.getName());
                    break;
                }
                case "C": {
                    msg = helloWorldBean.getCMsg(form.getName());
                    break;
                }
            }
            form.setResponse(msg);
        } else {
            form.setResponse("");
        }
    }
}
