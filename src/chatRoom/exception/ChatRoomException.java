package chatRoom.exception;
// 抽象类，继承Exception，是这个程序中所有异常类型的父类

/**
 * @author daizecheng
 *
 */
public abstract class ChatRoomException extends Exception {
	public ChatRoomException() {
		super();
	}

	/** 显示出错具体信息 */
	public abstract void printInfo();

	public abstract String getInfo();
}
