package de.hsb.simon.client.net;

import de.hsb.simon.commons.ClientCallbackInterface;
import de.root1.simon.SimonUnreferenced;
import de.root1.simon.annotation.SimonRemote;

//mark this class as a remote class and export all methods known in ClientCallbackInterface
@SimonRemote(value = {ClientCallbackInterface.class})
public class ClientCallbackImpl implements ClientCallbackInterface, SimonUnreferenced {

	private static final long serialVersionUID = 1L;

	@Override
	public void callback(String text) {

		System.out.println("This message was received from the server: " + text);

	}

	@Override
	public void unreferenced() {
		System.out.println("unreferenced: "+this);
 	}
}