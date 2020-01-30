package dev.jozefowicz.springsecurity.oauth2tester.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.Charset;

import static java.util.Objects.nonNull;

@RequestMapping("/")
@Controller
public class IndexController {

    private final RestTemplate restTemplate = new RestTemplate();

    private ThreadLocal<OAuth2TokenResponse> token = new ThreadLocal<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;

    @Value("${token.endpoint}")
    private String tokenEndpoint;

    @GetMapping
    public ModelAndView index() {
        final ModelAndView modelAndView = new ModelAndView("index");
        final OAuth2TokenResponse oAuth2TokenResponse = token.get();
        if (nonNull(oAuth2TokenResponse)) {
            modelAndView.addObject("token", oAuth2TokenResponse.getAccess_token());
        }
        return modelAndView;
    }

    @RequestMapping("/callback")
    public ModelAndView callback(@RequestParam("code") String code, @RequestParam("state") String state) throws JsonProcessingException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", httpBasicHeader());

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("code", code);
        map.add("grant_type", "authorization_code");
        map.add("redirect_uri", "http://localhost:5000/callback");
        map.add("scope", "read");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(tokenEndpoint, request , String.class);

        final OAuth2TokenResponse oAuth2TokenResponse = objectMapper.readValue(response.getBody(), OAuth2TokenResponse.class);
        token.set(oAuth2TokenResponse);
        final ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("token", oAuth2TokenResponse.getAccess_token());
        return modelAndView;
    }

    private String httpBasicHeader() {
        String auth = clientId + ":" + clientSecret;
        byte[] encodedAuth = Base64.encodeBase64(
                auth.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(encodedAuth);
    }

}
