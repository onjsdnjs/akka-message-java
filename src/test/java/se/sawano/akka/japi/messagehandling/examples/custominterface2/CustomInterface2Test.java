/*
 * Copyright 2013 Daniel Sawano
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package se.sawano.akka.japi.messagehandling.examples.custominterface2;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.JavaTestKit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomInterface2Test {

    ActorSystem system;

    @Before
    public void setUp() throws Exception {
        system = ActorSystem.create();
    }

    @After
    public void tearDown() throws Exception {
        JavaTestKit.shutdownActorSystem(system);
    }

    @Test
    public void shouldCommunicateWithActor() throws Exception {
        new JavaTestKit(system) {{
            final ActorRef actorRef = getSystem().actorOf(Props.create(CustomMessageActor.class));

            final SomeMessage msg = new SomeMessage();
            actorRef.tell(msg, getRef());

            expectMsgAllOf(msg);
        }};
    }
}
