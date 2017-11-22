package simple.oauth.resourceowner.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class MainController {
    @RequestMapping("/")
    public ResponseEntity<String> home() {
        return new ResponseEntity<String>("Hello", HttpStatus.OK);
    }

    @RequestMapping("/secured")
    public ResponseEntity<String> secured() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            System.out.println(authentication.getCredentials());
            System.out.println(authentication.getName());
            System.out.println(((OAuth2Authentication) authentication).getUserAuthentication());
            System.out.println(((OAuth2Authentication) authentication).getOAuth2Request());
            System.out.println(authentication.getPrincipal());
            System.out.println(authentication.getAuthorities());
        }
        System.out.println(authentication);
        System.out.println(authentication.getClass().getName());
        return new ResponseEntity<>("Secured", HttpStatus.OK);
    }

//    @PreAuthorize("hasRole('USER')")
    @RequestMapping("/secured2")
    public ResponseEntity<String> secured2(Principal p) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            System.out.println(authentication.getCredentials());
            System.out.println(authentication.getName());
            System.out.println(((OAuth2Authentication) authentication).getUserAuthentication());
            System.out.println(((OAuth2Authentication) authentication).getOAuth2Request());
            System.out.println(authentication.getPrincipal());
            System.out.println(authentication.getAuthorities());
        }
        System.out.println(authentication);
        System.out.println(authentication.getClass().getName());
        return new ResponseEntity<>("Secured2", HttpStatus.OK);
    }

    @PreAuthorize("hasPermission(authentication)")
    @RequestMapping("/custom")
    public ResponseEntity<String> custom(){

        return new ResponseEntity<String>("Custom",HttpStatus.OK);
    }
}
