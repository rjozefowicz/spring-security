package workshop.sb.security.oauth2Ssodemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @GetMapping
    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest httpServletRequest) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final OAuth2AuthenticationToken auth2Authentication = (OAuth2AuthenticationToken) authentication;
        final OAuth2User oAuth2User = auth2Authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("name", oAuth2User.getAttribute("name"));
        modelAndView.addObject("photo", oAuth2User.getAttribute("picture"));
        return modelAndView;
    }

}
