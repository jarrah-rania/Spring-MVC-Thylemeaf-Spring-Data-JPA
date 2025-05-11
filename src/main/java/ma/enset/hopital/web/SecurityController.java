package ma.enset.hopital.web;

import org.springframework.web.bind.annotation.GetMapping;

public class SecurityController {
    @GetMapping("/notAuthorized")
    public String notAuthorized()
    {
        return "notAuthirized";
    }
    @GetMapping("/login")
    public String login()
    {
        return "login";
    }
}

