package hibernate;

/**
 * Created with IntelliJ IDEA.
 * Author: Tomás Vilaboa
 * Project: Trav
 * Date: 23/04/14
 * Time: 12:10
 */
public class EventJSONWrapper {

    private int id;
    private String title;
    private String url;
    private String clazz;
    private Long start;
    private Long end;

    public EventJSONWrapper(EventsEntity event) {
        id = event.getId();
        title = event.getTitle();
        url = event.getUrl();
        clazz = event.getClazz();
        start = event.getStart();
        end = event.getEnd();


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }
}
