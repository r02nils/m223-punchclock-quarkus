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
    public User signupUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Transactional
    public boolean loginUser(User u) {
        List<User> users = findAll();
        for (User user : users) {
            System.out.println(user.getId());
            System.out.println(user.getUsername());
            System.out.println(user.getEmail());
            System.out.println(user.getPassword());
            if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())){
                entityManager.persist(user);
                AuthenticationService authenticationService = new AuthenticationService();
                System.out.println(authenticationService.GenerateValidJwtToken(u.getUsername()));
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        var query = entityManager.createQuery("FROM User");
        return query.getResultList();
    }
}
