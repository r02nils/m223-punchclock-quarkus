package ch.zli.m223.punchclock.service;

import java.time.Duration;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ch.zli.m223.punchclock.domain.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import io.smallrye.jwt.build.Jwt;
import org.eclipse.microprofile.jwt.Claims;

@RequestScoped
public class AuthenticationService {
    @Inject
    private EntityManager entityManager;
    
    public String GenerateValidJwtToken(String username){
        String token =
            Jwt.issuer("https://zli.ch/issuer") 
            .upn(username) 
            .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
            .claim(Claims.birthdate.name(), "2001-07-13")
            .expiresIn(Duration.ofHours(1)) 
            .sign();
        return token;
    }

    public String getUsernameFromJwtToken(String token) throws JSONException {
        Base64.Decoder decoder = Base64.getUrlDecoder();

        System.out.println(token.split("\\.")[1]);

        String decodedToken =  new String(decoder.decode(token.split("\\.")[1]));
        try{
            JSONObject obj = new JSONObject(decodedToken);
            String upnName = obj.getString("upn");
            return upnName;
        }
        catch(Exception e){
            System.out.println(e);
        }

        return "";
    }
}
