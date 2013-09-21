package de.hsb.simon.client.net;


import java.io.IOException;

import de.hsb.simon.commons.ServerInterface;
import de.hsb.simon.commons.SessionInterface;
import de.root1.simon.Lookup;
import de.root1.simon.Simon;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

public class Client {

    public static void main(String[] args) throws IOException, LookupFailedException, EstablishConnectionFailed {

        // create a callback object
        ClientCallbackImpl clientCallbackImpl = new ClientCallbackImpl();

        // 'lookup' the server object
        Lookup nameLookup = Simon.createNameLookup("127.0.0.1", 44444);
        ServerInterface server = (ServerInterface) nameLookup.lookup("server");

        // use the serverobject as it would exist on your local machine
        SessionInterface session = server.login("DonaldDuck", clientCallbackImpl);

        session.doSomething();

        // do some more stuff
        // ...

        // and finally 'release' the serverobject to release to connection to the server
        nameLookup.release(server);
    }
}