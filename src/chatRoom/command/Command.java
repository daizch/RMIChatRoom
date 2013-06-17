// 命令接口
package chatRoom.command;
import java.util.*;
import java.io.IOException;

import chatRoom.exception.*;

public interface Command {
	/**对参数列表进行检查*/
	public void parse(List param)throws ChatRoomException,IOException;
	/**执行命令*/
	public String[] excute()throws ChatRoomException,IOException;
}