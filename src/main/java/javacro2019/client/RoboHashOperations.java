package javacro2019.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.validation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@Client("https://robohash.org")
public interface RoboHashOperations {

    @Get("/{text}")
    byte[] getRobot(@NotBlank String text);

}
