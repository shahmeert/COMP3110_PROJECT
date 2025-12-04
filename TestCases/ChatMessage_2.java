package TestCases;
public class ChatMessage_2 {

    private String user;
    private String content;
    private long timeSent;
    private boolean wasEdited;
    private int editCount;

    public ChatMessage_2(String user, String content, long timeSent) {
        this.user = user;
        this.content = content;
        this.timeSent = timeSent;
        this.wasEdited = false;
        this.editCount = 0;
    }

    public void updateContent(String newContent) {
        if (newContent != null && !newContent.isEmpty()) {
            this.content = newContent;
            this.wasEdited = true;
            this.editCount++;
        }
    }

    public boolean edited() {
        return wasEdited;
    }

    public int getEditCount() {
        return editCount;
    }

    public String render() {
        return "[" + user + "] " + content + (wasEdited ? " (edited x" + editCount + ")" : "");
    }
}
