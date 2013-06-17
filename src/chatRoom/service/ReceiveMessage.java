package chatRoom.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import chatRoom.exception.*;
import chatRoom.domain.*;


public class ReceiveMessage extends java.rmi.server.UnicastRemoteObject
		implements ReceiveMessageInterface {
    public ReceiveMessage() throws java.rmi.RemoteException {
    }
    public void sendMessage(String msg) 
        throws ChatRoomException, java.rmi.RemoteException, IOException {
        System.out.println(msg);
    }
}


