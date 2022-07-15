package ch.zli.m223.punchclock.util;

import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Gender;
import ch.zli.m223.punchclock.domain.User;
import ch.zli.m223.punchclock.service.CategoryService;
import ch.zli.m223.punchclock.service.UserService;
import io.quarkus.runtime.StartupEvent;

@Singleton
public class Startup {
    @Inject
    UserService userService;

    @Inject
    CategoryService categoryService;

    @Transactional
    public void loadUsers(@Observes StartupEvent evt) {
        Gender gender1 = new Gender();
        gender1.setGender("m");

        Gender gender2 = new Gender();
        gender2.setGender("w");

        //Create test user (regular)
        User regularUser = new User();
        regularUser.createUser("nils", "r02nils@gmail.com", "password", gender1);

        Category category = new Category();
        category.setName("test kategorie");
    }
}