package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/pozdrav")
    public String hello(){
        return "Pozdrav";
    }
}
