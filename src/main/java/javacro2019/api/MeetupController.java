package javacro2019.api;

import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.validation.Validated;
import io.micronaut.views.View;
import javacro2019.client.MeetupOperations;
import javacro2019.client.RoboHashOperations;
import javacro2019.model.Meetup;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

@Controller("/meetup")
@Validated
public class MeetupController {

    @Inject
    private MeetupOperations meetupOperations;

    @Inject
    private RoboHashOperations roboHashOperations;

    @Value("${micronaut.meetup.urlname}")
    private String meetupUrlname;

    @Get("/{meetupId}/host")
    @Produces(MediaType.IMAGE_PNG)
    public byte[] roboMeetup(@NotBlank final String meetupId) {
        Meetup meetup = meetupOperations.getMeetup(meetupUrlname, meetupId);
        byte[] robot = roboHashOperations.getRobot(meetup.toString().replaceAll(" ", "+"));
        return robot;
    }

    @Get("/{meetupId}/basic")
    @Produces(MediaType.TEXT_PLAIN)
    public String plainMeetup(@NotBlank final String meetupId) {
        Meetup meetup = meetupOperations.getMeetup(meetupUrlname, meetupId);
        return meetup.toString();
    }

    @Get("/{meetupId}/visit")
    @View("meetup")
    public HttpResponse visitMeetup(@NotBlank final String meetupId) {
        Meetup meetup = meetupOperations.getMeetup(meetupUrlname, meetupId);
        return HttpResponse.ok(meetup);
     }

}
