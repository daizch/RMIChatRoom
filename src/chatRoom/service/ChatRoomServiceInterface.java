// chatRoom
package chatRoom.service;

import java.io.IOException;
import chatRoom.exception.ChatRoomException;
import java.rmi.RemoteException;
import chatRoom.exception.*;
import chatRoom.domain.*;

public interface ChatRoomServiceInterface extends java.rmi.Remote {
	public abstract String[] excuteCommand(String command)
			throws ChatRoomException, java.rmi.RemoteException, IOException;
	public abstract String getUserName()
			throws ChatRoomException, java.rmi.RemoteException, IOException;
	public abstract String getRoomName()
			throws ChatRoomException, java.rmi.RemoteException, IOException;
}
