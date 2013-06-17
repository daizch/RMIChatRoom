/**
 * 
 */
package chatRoom.command;

import java.io.*;
import java.util.List;

import chatRoom.domain.*;
import chatRoom.exception.*;


/**清理命令，功能：删除一个聊天室的所有聊天记录
 * @version 1.0
 */
public class ClearCommand implements Command{
	/**私有数据成员，用于保存用户信息*/
	private User user;
	private Room room;
	/**静态工厂对象成员，返回实现了工厂接口的匿名内部类实例*/
	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new ClearCommand();
		}
	};
	
	/**私有构造函数，只能通过工厂获取实例
	 * 
	 */
	private ClearCommand(){
		user=null;
		room = null;
	}
	
	/**函数功能：对参数列表进行合法性检查
	 * @param param 参数列表
	 * @throws ChatRoomException
	 */
    public void parse(List param)throws ChatRoomException,IOException
    {
    	if(param!=null){
			int num=param.size();
			String password = null, userName, roomName;
			if(num == 2){
				roomName = (String)param.get(0);
				userName = (String)param.get(1);
			}
			else if(num == 3){
				roomName = (String)param.get(0);
				password = (String)param.get(1);
				userName = (String)param.get(2);
			}
			else throw new ParameterNumWrongException();

			RoomManager rm = RoomManager.getInstance();	
			room = rm.getRoom(roomName);
			if (room != null) {
				if (rm.creatorValidate(room, userName)) {
                    if (!rm.passwordValidate(room, password)) {
                        throw new RoomPasswordWorngException();
                    }
				}
				else throw new CreatorWrongException();
			}
			else throw new RoomNotExistException();
		}
		else throw new NoParameterException();
	}

    /**
     * 函数功能：删除一个聊天室的所有聊天记录
     */
	public String[] excute()throws ChatRoomException,IOException {
		String[] re = new String[1];
		RoomManager.getInstance().clearMessages(room);
		re[0] = "成功清除该聊天室的所有聊天记录.";
		return re;
	}

}
