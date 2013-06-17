// 命令工厂
package chatRoom.command;

public class CommandFactory {
	// 私有静态实例成员*/
	private static CommandFactory factory;
	
	// 私有构造函数，实现单实例模式
	private CommandFactory(){
		
	}
	
	// 静态方法，返回命令工厂的唯一实例的引用
	public static CommandFactory getInstance(){
		if(factory==null) factory=new CommandFactory();
		return factory;
	}

	// 根据命令名创建命令实例
	public Command createCommand(String cmdName){
		cmdName = cmdName.trim();
		Command cmd = null;
		if(cmdName.equalsIgnoreCase("register")) cmd = RegisterCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("login")) cmd = LoginCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("create")) cmd = CreateChatRoomCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("query")) cmd = QueryCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("lsrooms")) cmd = ListRoomsCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("lsusers")) cmd = ListUsersCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("in")) cmd = InRoomCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("out")) cmd = OutRoomCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("send")) cmd = SendCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("delete")) cmd = DeleteChatRoomCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("clear")) cmd = ClearCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("logout")) cmd = LogoutCommand.factory.createCommand();
		else if(cmdName.equalsIgnoreCase("help")) cmd = HelpCommand.factory.createCommand();
		return cmd;
	}
}
