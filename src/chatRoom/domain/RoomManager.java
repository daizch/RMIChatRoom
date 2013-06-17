package chatRoom.domain;

import java.util.*;
import java.text.*;
import java.lang.String;

import chatRoom.exception.*;


/**聊天室管理类
 *
 */
public class RoomManager {
	/*聊天室列表, roomName --> room*/
    private static HashMap<String,Room> roomList = new HashMap<String,Room>();
    /*用户聊天状态 username --> roomname*/
    private static HashMap<String,String> userInRoom = new HashMap<String,String>();
    private static RoomManager roomManager = new RoomManager();
    /**
     * 构造函数   
     */
    public RoomManager()
    {

    }
   // 静态方法，返回管理类的唯一实例的引用
    public static RoomManager getInstance(){
        return roomManager;
    }

    /*
    * @param roomName 聊天室名称
    *返回聊天室
    */
    public static Room getRoom(String roomName) {
        if (roomList.containsKey(roomName))
            return roomList.get(roomName);
        else 
            return null;
    }

    /*
    * @param userName 聊天室创建者名称
    * 如果验证成功返回true，否则返回false
    */
    public static Boolean creatorValidate(Room room, String userName) {
        return userName.equalsIgnoreCase(room.getCreator());
    }

    /*
    * @param password 聊天室密码
    * 如果验证成功返回true，否则返回false
    */
    public static Boolean passwordValidate(Room room, String password) {
        if (room.getIsSetPassword())
            return password != null && !password.isEmpty() && password.equalsIgnoreCase(room.getPassword());
        return true;
    }

    /*
    * @param roomName 聊天室名称
    * @param password 聊天室密码
    * 如果验证成功返回true，否则返回false
    */
    public static Boolean RoomValidate(String roomName, String password) {        
        Room room = getRoom(roomName);        
        return passwordValidate(room, password);
    } 

     /*
    * @param room 聊天室对象
    * 函数功能：清空聊天记录
    */
    public static void clearMessages(Room room) {                
        room.clearMessages();
    }    

    /*
    * @param room 聊天室对象
    * 函数功能：创建聊天室
    */
    public static void createChatRoom(Room room) throws ChatRoomException{                
        if (getRoom(room.getRoomName()) == null) {
            roomList.put(room.getRoomName(), room);

        }
        else throw new RoomHasExistedException();
    } 

     /*
    * @param room 聊天室对象
    * 函数功能：删除聊天室
    */
    public static void deleteChatRoom(Room room) throws ChatRoomException{                
        Room localRoom = getRoom(room.getRoomName());
        if (localRoom != null) {
            if (room.getCreator() == localRoom.getCreator()) {
                String rn = room.getRoomName();
                roomList.remove(rn);
                Iterator iterator = userInRoom.keySet().iterator();
                List toRemove =  new ArrayList<String>();
                while (iterator.hasNext()) {
                    Object key = iterator.next();
                    if (userInRoom.get(key).equalsIgnoreCase(rn))
                        toRemove.add((String)key);
                }
                for (int i = 0; i < toRemove.size(); i++)
                    userInRoom.remove(toRemove.get(i));

            }
            else throw new CreatorWrongException();
        }
        else throw new RoomNotExistException();
    } 

     /*
    * @param userName 用户名称
    * @param roomName 聊天室名称
    * 函数功能：加入聊天室
    */
    public static void joinRoom(String userName, String roomName) throws ChatRoomException{
        Room room = getRoom(roomName);
        if (room != null) {
            room.addUser(userName);
            userInRoom.put(userName, roomName);
        }
        else throw new RoomNotExistException();
    } 
    
     /*
    * @param userName 用户名称
    * 函数功能：从聊天室中移除用户
    */
    public static void removeUserFromRoom(String userName) throws ChatRoomException{
        String roomName = userInRoom.get(userName);
        if (roomName == null)
            return;
        Room room = getRoom(roomName);
        if (room != null) {
            room.removeUser(userName);
            userInRoom.remove(userName);
        }
        else throw new RoomNotExistException();
    }


     /*
    * @param roomName 聊天室名称
    * @param msg 消息内容
    * 函数功能：记录聊天信息
    */
    public static void putMessageToRoom(String roomName, Message msg) throws ChatRoomException{
        Room room = getRoom(roomName);
        if (room != null) {
            room.addMessage(msg);
        }
        else throw new RoomNotExistException();
    }

    /*
    * 函数功能：返回所有房间名称
    */
    public static List getAllRooms() {
    	List rooms = new ArrayList(roomList.keySet());
        return rooms;
    }

    /*
    * @param roomName 聊天室名称
    * 函数功能：返回在当前聊天室里的用户列表
    */
    public static List getAllUsers(String roomName) { 
        Room room = getRoom(roomName);
        return room.getUserList();
    }

    /*
    * 函数功能：返回所有用户状态
    */
    public static List getAllUsers() { 
        List usersInfo = new ArrayList<String>();
        Iterator iterator = userInRoom.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            usersInfo.add (key + "---in room---" + userInRoom.get(key));
        }
        return usersInfo;
    }
}
