package chat;

/**
Represents a Message sent by a user.
*/
public class Message
{
	/**
	* String used to store the name of a chatter
	
	*/
	private String chatterName = null;
	/**
	* String containing message
	*/
	private String message = null;

	/**
	* long containing the time when message was delivered
	*/
	private String timeStamp=null;
        private String room = null;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
	/**
	* This constructor accepts a name of the chatterand the message.
	* @param name Name of the chatter
	* @param message message of the chatter
	* @param timeStamp time of the message
	*/
	public Message(String name, String message, String timeStamp,String room)
	{
		this.chatterName = name;
		this.message= message;
		this.timeStamp = timeStamp;
                this.room = room;
	}
	
	/**
	* Returns name of the Chatter
	* @return String
	*/
	public String getChatterName()
	{
		return chatterName;
	}
	
	/**
	* Returns message of the chatter
	* @return String
	*/
	public String getMessage()
	{
		return message;
	}
	/**
	* Returns time of the message
	* @return long
	*/
	public String getTimeStamp()
	{
		return timeStamp;
	}
}