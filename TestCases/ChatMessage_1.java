package TestCases;
public class ChatMessage_1 {

    private String sender;
    private String message;
    private long timestamp;
    private boolean edited;

    public ChatMessage_1(String sender, String message, long timestamp) {
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
        this.edited = false;
    }

    public String preview() {
        if (message.length() <= 25) {
            return message;
        }
        return message.substring(0, 25) + "...";
    }

    public void editMessage(String newMessage) {
        if (newMessage != null && !newMessage.isEmpty()) {
            this.message = newMessage;
            this.edited = true;
        }
    }

    public boolean isEdited() {
        return edited;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String format() {
        return "[" + sender + "] " + message + (edited ? " (edited)" : "");
    }
}
