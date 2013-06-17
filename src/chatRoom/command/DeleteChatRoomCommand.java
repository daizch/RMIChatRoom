// 注册命令类，扩展Command接口
package chatRoom.command;

import java.util.*;
import java.io.IOException;
import chatRoom.domain.*;
import chatRoom.exception.*;

public class DeleteChatRoomCommand implements Command{
	// 要删除的聊天室
	private Room room;
	// 静态工厂实例，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new DeleteChatRoomCommand();
		}
	};

	// 私有构造函数，只能通过工厂获取对象实例
	private DeleteChatRoomCommand(){
		room=null;
	}

	// 对参数进行合法性检查
	public void parse(List param)throws ChatRoomException{
		if(param!=null){
			int num=param.size();
			if(num == 2){
				room = new Room((String)param.get(1), (String)param.get(0)); 
			} else if (num == 3) {
				room = new Room((String)param.get(2), (String)param.get(0), (String)param.get(1));
			}
			else throw new ParameterNumWrongException();
		}
		else throw new NoParameterException();
	}

	// 删除聊天室
	public String[] excute()throws ChatRoomException,IOException{
		String[] re = new String[1];
		RoomManager.getInstance().deleteChatRoom(room);
		re[0] = "删除聊天室成功。";
		return re;
	}
}
