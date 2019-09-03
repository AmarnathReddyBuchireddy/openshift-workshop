package gh.atsticks.hello;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HelloControllerUnitTest {

    @Test
    public void saysHello() {
        HelloController sut = new HelloController();

        ResponseEntity<String> response = sut.hello();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("hello");
    }

    @Test
    public void returnsHostname() {
        //FIXME refactor Inetaddress into helper method to mock this ;)
    }
}
