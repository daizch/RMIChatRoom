package chatRoom.command;

import java.util.*;

/**帮助命令类
 *
 */
public class HelpCommand implements Command{
	/**静态工厂对象，返回实现工厂接口的匿名内部类实例*/
	private Boolean isLogin = false;

	public static CmdFactory factory=new CmdFactory(){
		public Command createCommand(){
			return new HelpCommand();
		}
	};

	/**
	 *  私有构造函数，只能通过工厂获取实例
	 */
	private HelpCommand(){
		super();
	}

	/**判断是否是登录用户
	 * */
	public void parse(List param) {
		if(param!=null && param.size() > 0){
			isLogin = true;
		}
	}

	/**
	 * 显示系统命令格式
	 */
	public String[] excute(){
		if (isLogin) {
			String[] re = new String[14];
			re[0] = "****************************************************************************";
			re[1] = "登录用户命令格式如下";
			re[2] = "$create roomName password[option]-------------------------------- 创建聊天室";
			re[3] = "$delete roomName password[option]-------------------------------- 删除聊天室";
			re[4] = "$query roomName password[option]------------------------------- 查询聊天记录";
			re[5] = "$lsrooms----------------------------------------------------- 查询聊天室列表";
			re[6] = "$lsusers--------------------------------------------------- 查询所有用户状态";
			re[7] = "$in roomName----------------------------------------------------- 进入聊天室";
			re[8] = "$out------------------------------------------------------------- 退出聊天室";
			re[9] = "$send message----------------------------------------------------- 发送信息";
			re[10] = "$clear roomName password[option]---------------------------- 清除聊天室记录";
			re[11] = "$logout----------------------------------------------------------- 退出登录";
			re[12] = "$help------------------------------------------------- 登录用户命令格式帮助";
			re[13] = "***************************************************************************";
			isLogin = false;
			return re;
		}
		else {
			String[] re = new String[5];
			re[0] = "*****************************************************************************";
			re[1] = "系统命令格式如下：";
			re[2] = "$register userName password----------------------------------------- 用户注册";
			re[3] = "$login userName password-------------------------------------------- 用户登录";
			re[4] = "$help------------------------------------------------------- 系统命令格式帮助";
			return re;
		}
	}
}
