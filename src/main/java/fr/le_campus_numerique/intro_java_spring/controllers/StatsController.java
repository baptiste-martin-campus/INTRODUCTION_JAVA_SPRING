package fr.le_campus_numerique.intro_java_spring.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class StatsController {

    private String externalURL = "http://172.22.114.56:9191/";

    @GetMapping("/testseb")
    public String testSeb() {
        String url = externalURL + "test";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/stats/players")
    public String getPlayers() {
        String url = externalURL + "players";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/stats/matches")
    public String getMatches() {
        String url = externalURL + "Matches";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @PostMapping("/stats/matches")
    public String addMatch(@RequestBody List gameResult) {
        try{
            String url = externalURL + "Matches";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<List> requestEntity = new HttpEntity<>(gameResult, headers);
            ResponseEntity<String> result = restTemplate.postForEntity(url, requestEntity, String.class);
            return result.getBody();
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }


}
