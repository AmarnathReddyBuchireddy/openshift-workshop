package gh.atsticks.hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

@RestController
public class HelloController {

    private Random r = new Random();
    private int shutdownCalled;
    Timer timer = new Timer(true);

    @RequestMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }


    @RequestMapping("/host")
    public ResponseEntity<String> defaultResponse() {
        try {
            return ResponseEntity.ok(InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            return ResponseEntity.ok("omg where am i?");
        }
    }

    /**
     * Simulated health endpoint that fails in 10% of times called with a INTERNAL_SERVER_ERROR.
     * @return the current health state.
     */
    @RequestMapping("/health-unstable")
    public ResponseEntity<String> healthUnstable() {
        // Fail every 10%
        if(r.nextInt(10) > 8){
            return new ResponseEntity("Simulated health error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok("I am strong and healthy!");
    }

    /**
     * Simulated shutdown endpoint, that exits after 3 calls.
     * @return the current health state.
     */
    @RequestMapping("/shutdown")
    public ResponseEntity<String> shutdown() {
        // Fail every 10%
        if(shutdownCalled++ >= 3){
            timer.schedule(new TimerTask(){
                @Override
                public void run() {
                    System.exit(-1);
                }
            }, 2000L);
            return new ResponseEntity("Shutdown requested. Exiting in 2 seconds...", HttpStatus.OK);
        }
        return ResponseEntity.ok("Shotdown called: " + shutdownCalled);
    }

}
