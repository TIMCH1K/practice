package ru.itis.repositories;

import ru.itis.models.Event;
import ru.itis.models.User;
import java.util.List;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public interface EventsRepository extends CrudRepository<Event> {
    Event findByName(String nameEvent);
    List<Event> findAllByMembersContains(User user);

    void saveUserToEvent(User user, Event event);
}
