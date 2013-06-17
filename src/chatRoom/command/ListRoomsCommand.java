// 注册命令类，扩展Command接口
package chatRoom.command;

import java.util.*;
import java.io.IOException;
import chatRoom.domain.*;
import chatRoom.exception.*;

public class ListRoomsCommand implements Command{
	// 静态工厂实例，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new ListRoomsCommand();
		}
	};

	// 私有构造函数，只能通过工厂获取对象实例
	private ListRoomsCommand(){	
	}

	// 不需对参数进行合法性检查
	public void parse(List param)throws ChatRoomException{
	}

	// 获取聊天室列表
	public String[] excute()throws ChatRoomException{
		List rooms = RoomManager.getInstance().getAllRooms();
        if (rooms != null && rooms.size() > 0) {
            String[] re = new String[rooms.size() + 1];
            int i;
            for (i = 0; i < rooms.size(); i++)
                re[i] = (String)rooms.get(i);
            re[i] = "获取房间列表成功。";
            return re;
        } else {
            String[] re = new String[2];
            re[0] = "No chat room created!";
            re[1] = "获取房间列表成功。";
            return re;
        }
	}
}
