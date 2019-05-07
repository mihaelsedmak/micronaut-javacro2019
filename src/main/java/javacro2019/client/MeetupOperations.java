package javacro2019.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.validation.Validated;
import javacro2019.model.Meetup;

import javax.validation.constraints.NotBlank;

@Validated
@Client("https://api.meetup.com")
public interface MeetupOperations {

    @Get("/{urlname}/events/{id}?key=${micronaut.meetup.auth-key}")
    Meetup getMeetup(@NotBlank String urlname, @NotBlank String id);


}
