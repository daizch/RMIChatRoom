// chatRoom
package chatRoom.service;

import java.io.IOException;
import chatRoom.exception.ChatRoomException;

public interface ReceiveMessageInterface extends java.rmi.Remote {
    public abstract void sendMessage(String msg) 
        throws ChatRoomException, java.rmi.RemoteException, IOException;
}
