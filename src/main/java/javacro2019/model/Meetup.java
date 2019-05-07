package javacro2019.model;

import lombok.Data;

@Data
public class Meetup {

    private String name;
    private Venue venue;
    private Group group;
    private String link;
    private String description;

    @Override public String toString() {
        StringBuffer text = new StringBuffer();
        text.append(this.getName()).
                append(" by ").append(this.getGroup().getName()).
                append(" @ ").append(this.getVenue().getName());
        return text.toString();
    }

}
