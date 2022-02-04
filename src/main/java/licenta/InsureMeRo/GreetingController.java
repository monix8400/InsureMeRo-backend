package licenta.InsureMeRo;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting getGreeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/greeting")
    public Greeting newName(@RequestBody String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

//    @PostMapping("/greeting")
//    public Greeting newGreeting (@RequestBody List<String> peopleName ) {
//        System.out.println(peopleName);
//        for(String people:peopleName)
//            if(people.equals("bb")) return new Greeting(counter.incrementAndGet(), String.format(template, people));
//        return new Greeting(counter.incrementAndGet(), String.format(template, "World"));
//    }
}
