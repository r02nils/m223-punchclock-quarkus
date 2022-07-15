package ch.zli.m223.punchclock.domain;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class DBInit {
 
    @PostConstruct
    private void postConstruct() {
        System.out.println("test1");
        Gender gender1 = new Gender();
        gender1.setGender("m");

        Gender gender2 = new Gender();
        gender2.setGender("w");

        User user = new User();
        user.createUser("nils", "test@gmail.com", "password", gender1);
    }
}