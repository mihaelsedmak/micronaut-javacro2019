package javacro2019.api;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpResponseFactory;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import javacro2019.client.RoboHashOperations;
import javacro2019.client.MeetupOperations;
import javacro2019.model.Meetup;
import javax.inject.Inject;

@Controller("/meetup")
public class MeetupController {

    @Inject
    private MeetupOperations meetupOperations;

    @Inject
    private RoboHashOperations roboHashOperations;

    @Value("${micronaut.meetup.urlname}")
    private String meetupUrlname;

    @Value("${micronaut.meetup.id}")
    private String meetupId;


    @Get("/robot")
    @Produces(MediaType.IMAGE_PNG)
    public byte[] roboMeetup() {
        Meetup meetup = meetupOperations.getMeetup(meetupUrlname, meetupId);
        byte[] robot = roboHashOperations.getRobot(meetup.toString().replaceAll(" ", "+"));
        return robot;
    }

    @Get("/plain")
    @Produces(MediaType.TEXT_HTML)
    public String plainMeetup() {
        Meetup meetup = meetupOperations.getMeetup(meetupUrlname, meetupId);
        return meetup.toString();
    }

}
