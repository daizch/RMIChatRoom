// 注册命令类，扩展Command接口
package chatRoom.command;

import java.util.*;
import java.io.IOException;
import chatRoom.domain.*;
import chatRoom.exception.*;

public class ListUsersCommand implements Command{
	// 需要查询的聊天室名称
	private String roomName;

	// 静态工厂实例，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new ListUsersCommand();
		}
	};

	// 私有构造函数，只能通过工厂获取对象实例
	private ListUsersCommand(){	
	}

	// 不需对参数进行合法性检查
	public void parse(List param)throws ChatRoomException {
		if(param!=null && param.size() > 0){
			roomName = (String)param.get(0);
		} else {
            roomName = null;
        }
	}

	// 加入聊天室
	public String[] excute()throws ChatRoomException{
        List users;
        if (roomName == null)
            users = RoomManager.getInstance().getAllUsers();
        else
            users = RoomManager.getInstance().getAllUsers(roomName);
		String[] re = new String[users.size() + 1];
        if (users.size() == 0)
            re[0] = "当前没有任何用户在聊天室";
        else {
            int i;
            for (i = 0; i < users.size(); i++)
                re[i] = (String)users.get(i);
            re[i] = "获取房间用户列表成功。";
        }
        return re;
	}
}
