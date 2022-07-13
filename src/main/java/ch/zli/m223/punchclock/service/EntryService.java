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
public class EntryService {
    @Inject
    private EntityManager entityManager;

    public EntryService() {
    }

    @Transactional 
    public Entry createEntry(Entry entry) {
        entityManager.persist(entry);
        return entry;
    }

    @Transactional 
    public Entry addCategory(long eid, long id) {
        Category category = entityManager.find(Category.class, id);
        Entry entry = entityManager.find(Entry.class, eid);
        entry.setCategory(category);
        entityManager.merge(entry);
        return entry;
    }

    @Transactional 
    public Entry updateEntry(Entry entry) {
        entityManager.merge(entry);
        return entry;
    }

    @Transactional 
    public void deleteEntry(long id) {
        Entry entry = entityManager.find(Entry.class, id);
        System.out.println(entry.getId());
        System.out.println(entry.getCheckIn());
        entityManager.remove(entry);
    }

    @SuppressWarnings("unchecked")
    public List<Entry> findAll() {
        var query = entityManager.createQuery("FROM Entry");
        return query.getResultList();
    }
}
