package ec.com.leodev.leoplay.web.controller;

import ec.com.leodev.leoplay.domain.service.IPlayAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final String platform;
    private final IPlayAiService playAiService;

    public HelloController(@Value("${spring.application.name}") String platform, IPlayAiService playAiService) {
        this.platform = platform;
        this.playAiService = playAiService;
    }


    @GetMapping("/hello")
    public String hello() {
        return playAiService.generateGreeting(platform);
    }
}
