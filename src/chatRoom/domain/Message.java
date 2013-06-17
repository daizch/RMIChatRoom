package chatRoom.domain;
import java.util.Date;
/**消息类
 *
 */
public class Message {
	/**消息发布者*/
	private String userName;
	/**消息内容*/
	private String content;
	/**发表时间*/
	private Date when;	
	
	/**
	 * 构造函数
	 * @param userName 消息发布者
	 * @param content 消息内容
	 * @param when 发表时间	
	 */
	public Message(String userName,String content,Date when){
		this.userName = userName;
		this.content = content;
		this.when = when;
	}

	/**
	 * 构造函数
	 * @param userName 消息发布者
	 * @param content 消息内容
	 */
	public Message(String userName,String content){
		this.userName = userName;
		this.content = content;
		this.when = new Date();
	}

	/**
	 * 序列化函数
	 */
	public String toString(){
		return "time:"+ when +"\n" + userName + " say:" +content;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @return constitutor
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return interval
	 */
	public Date getWhen() {
		return when;
	}

	/**
	 * @param userName 要设置的 userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param content 要设置的 content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @param when 要设置的 when
	 */
	public void setTime(Date when) {
		this.when = when;
	}
}
