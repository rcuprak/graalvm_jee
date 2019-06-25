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

import fish.payara.micro.BootstrapException;
import fish.payara.micro.PayaraMicro;

import javax.naming.InitialContext;

/**
 * Starts the Payara (aka GlassFish) and deploys the HelloWorld application.
 * Provides methods for calling beans within the container.
 * @author Ryan Cuprak
 */
public class PayaraMicroWrapper {

    /**
     * Starts the Payara container
     * @throws Exception - thrown if there is an error
     */
    public static void run() throws Exception {
        PayaraMicro micro = PayaraMicro.getInstance();
        micro.addDeployment("helloworld/target/helloworld-1.0-SNAPSHOT.war");
        micro.setHttpPort(9000);
        try {
            micro.bootStrap();
            System.out.println("-----------> DONE BOOTSTRAPING");
        } catch (BootstrapException e) {
            e.printStackTrace();
        }
    }

    /**
     * Invoked from the express application. We lookup and invoke a bean.
     * @return hello world string from Payara
     */
    public static String sayHello() {
        try {
            InitialContext ctx = new InitialContext();
            StarterBean bean = (StarterBean)ctx.lookup("java:global/helloworld-1.0-SNAPSHOT/StarterBeanImpl");
            return bean.sayHelloWorld();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
