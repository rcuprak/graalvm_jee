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
import org.graalvm.polyglot.Value;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import java.io.IOException;
import java.net.URL;

/**
 * Wraps the GraalVM classes used to invoke
 */
@Stateless
public class PolygotHelloWorld {

    private Context context;

    private Value jsHelloWorld;
    private Value rHelloWorld;
    private Value pHelloWorld;
    private Value rubyHelloWorld;
    private Value cHelloWorld;

    @PostConstruct
    public void init() {
        try {
            URL jsScriptUrl = PolygotHelloWorld.class.getResource("/net/cuprak/graalvm/scripts/HelloWorld.js");
            URL rScriptUrl = PolygotHelloWorld.class.getResource("/net/cuprak/graalvm/scripts/HelloWorld.R");
            URL pScriptUrl = PolygotHelloWorld.class.getResource("/net/cuprak/graalvm/scripts/HelloWorld.py");
            URL rbScriptUrl = PolygotHelloWorld.class.getResource("/net/cuprak/graalvm/scripts/HelloWorld.rb");
            URL cScriptUrl = PolygotHelloWorld.class.getResource("/net/cuprak/graalvm/scripts/hello.bc");
            context = Context.newBuilder().allowAllAccess(true).build();
            context.eval(Source.newBuilder("js", jsScriptUrl).build());
            context.eval(Source.newBuilder("R", rScriptUrl).build());
            context.eval(Source.newBuilder("python", pScriptUrl).build());
            jsHelloWorld = context.eval("js","helloWorld");
            rHelloWorld = context.eval("R","helloWorld");
            pHelloWorld = context.eval("python","helloWorld");
            rubyHelloWorld =  context.eval(Source.newBuilder("ruby",rbScriptUrl).build());
            cHelloWorld = context.eval(Source.newBuilder("llvm",cScriptUrl).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getJavaScriptMsg(String name) {
        return jsHelloWorld.execute(name).asString();
    }

    public String getRMsg(String name) {
        return rHelloWorld.execute(name).getArrayElement(0).asString();
    }

    public String getPythonMsg(String name) {
        return pHelloWorld.execute(name).asString();
    }

    public String getRubyMsg(String name) {
        return rubyHelloWorld.execute(name).asString();
    }

    public String getCMsg(String name) {
        Object obj = cHelloWorld.getMember("__sulong_byte_array_to_native").execute(name.getBytes());
        Value fn = cHelloWorld.getMember("helloWorld");
        Value v = fn.execute(obj);
        System.out.println("Return: " + v.asString());
        return v.asString();
    }
}
