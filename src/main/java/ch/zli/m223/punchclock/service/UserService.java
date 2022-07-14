package ch.zli.m223.punchclock.service;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import ch.zli.m223.punchclock.domain.User;

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
    public int loginUser(User u) {
        List<User> users = findAll();
        for (User user : users) {
            System.out.println(user.getId());
            System.out.println(user.getUsername());
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            if(user.getUsername().equals(u.getUsername())){
                if (BCrypt.checkpw(u.getPassword(), user.getPassword())){
                    System.out.println("It matches");
                }
                else{
                    System.out.println("It does not match");
                    return -1;
                }
                entityManager.persist(user);
                AuthenticationService authenticationService = new AuthenticationService();
                authenticationService.GenerateValidJwtToken(u.getUsername());
                return 1;
            }
        }
        return -2;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }
}
