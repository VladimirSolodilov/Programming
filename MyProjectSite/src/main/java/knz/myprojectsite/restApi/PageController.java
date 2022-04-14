package knz.myprojectsite.restApi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    @GetMapping
    public String defaultPage(){
        return "main_non-authorized";
    }

    @GetMapping("/main_authorized")
    public String main_autorized(){
        return "main_authorized";
    }

    @GetMapping("/main_non-authorized")
    public String main_nonAutorized(){
        return "main_non-authorized";
    }

    @GetMapping("/signup")
    public String signupPage(){
        return "signup";
    }
    @GetMapping("/signin")
    public String signinPage(){
        return "signin";
    }
    @GetMapping("/profile")
    public String profilePage(){
        return "profile";
    }
    @GetMapping("/game")
    public String gameroomPage(){
        return "gameroom";
    }
}
