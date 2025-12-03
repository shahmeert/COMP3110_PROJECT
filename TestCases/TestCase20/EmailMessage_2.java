public class EmailMessage_2 {

    private String sender;
    private String recipient;
    private String subjectLine;
    private String content;

    public EmailMessage_2(String sender, String recipient, String subjectLine, String content) {
        this.sender = sender;
        this.recipient = recipient;
        this.subjectLine = subjectLine;
        this.content = content;
    }

    public String shortPreview(int maxChars) {
        if (content.length() <= maxChars) {
            return content;
        }
        return content.substring(0, maxChars) + "...";
    }

    public String getSubjectLine() {
        return subjectLine;
    }
}
