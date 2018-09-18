package exception;

public class InfoException extends Exception {

    public String alertType;
    public String title;
    public String header;
    public String text;

    public InfoException(String alertType, String title, String header, String text) {
        super();
        this.alertType = alertType;
        this.title = title;
        this.header = header;
        this.text = text;
    }

    public String toJSONString() {
        return String.format("{'alertType' : %s, 'title' : %s, 'header' : %s, 'text' : %s}", alertType, title, header, text).toString();
    }
}
