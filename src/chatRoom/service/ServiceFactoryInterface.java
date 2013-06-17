// chatRoom
package chatRoom.service;

import java.io.IOException;
import chatRoom.exception.ChatRoomException;
import java.rmi.RemoteException;
import chatRoom.exception.*;
import chatRoom.domain.*;

public interface ServiceFactoryInterface extends java.rmi.Remote {
	public abstract String getChatServiceName()
			throws java.rmi.RemoteException, IOException;
}
