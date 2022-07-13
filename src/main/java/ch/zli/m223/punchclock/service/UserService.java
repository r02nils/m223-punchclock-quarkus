package ch.zli.m223.punchclock.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.punchclock.domain.Category;
import ch.zli.m223.punchclock.domain.Entry;

@ApplicationScoped
public class UserService {
    @Inject
    private EntityManager entityManager;

    public UserService() {
    }


}
