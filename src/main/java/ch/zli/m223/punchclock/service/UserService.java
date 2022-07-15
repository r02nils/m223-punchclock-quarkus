package ch.zli.m223.punchclock.service;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.jwt.Claims;

import ch.zli.m223.punchclock.domain.User;
import io.quarkus.vertx.web.Body;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    public UserService() {
    }

    @Transactional 
    public int signupUser(User user) {
        if(user.getUsername().length() < 3){
            return -2;
        }
        if(user.getPassword().length() < 8){
            return -1;
        }
        String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(pw_hash);
        entityManager.persist(user);
        return 1;
    }

    @Transactional 
    public int checkIfLoggedIn(String myToken) {
        System.out.println(myToken);
        System.out.println("---");
        AuthenticationService authenticationService = new AuthenticationService();
        List<User> users = findAll();
        for (User user : users) {
            String token = authenticationService.GenerateValidJwtToken(user.getUsername());
            System.out.println(token);
            System.out.println("---");
            if(token.equals(myToken)){
                return 1;
            }
        }

        return -1;
    }

    @Transactional
    public Response loginUser(User u) {
        List<User> users = findAll();
        for (User user : users) {
            if(user.getUsername().equals(u.getUsername())){
                if (BCrypt.checkpw(u.getPassword(), user.getPassword())){
                    System.out.println("It matches");
                }
                else{
                    System.out.println("It does not match");
                    return Response
                    .ok(-1)
                    .header("token", "")
                    .build();
                }
                entityManager.persist(user);
                AuthenticationService authenticationService = new AuthenticationService();
                String token = authenticationService.GenerateValidJwtToken(u.getUsername());

                
                return Response
                .ok(1)
                .header("token", token)
                .build();
            }
        }
        return Response
        .ok(-2)
        .header("token", "")
        .build();
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }
}
