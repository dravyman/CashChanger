package exception;

public class InfoException extends Exception {

    public String alertType;
    public String title;
    public String header;
    public String content;

    public InfoException(String alertType, String title, String header, String content) {
        super();
        this.alertType = alertType;
        this.title = title;
        this.header = header;
        this.content = content;
    }

    public String toJSONString() {
        return String.format("{\"alertType\" : \"%s\", \"title\" : \"%s\", \"header\" : \"%s\", \"content\" : \"%s\"}", alertType, title, header, content).toString();
    }
}
