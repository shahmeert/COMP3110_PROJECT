public class EmailMessage_1 {

    private String from;
    private String to;
    private String subject;
    private String body;

    public EmailMessage_1(String from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public String preview() {
        if (body.length() <= 20) {
            return body;
        }
        return body.substring(0, 20) + "...";
    }

    public String getSubject() {
        return subject;
    }
}
