
package ru.itis;

import ru.itis.repositories.EventsRepository;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.impl.EventsRepositoryFileImpl;
import ru.itis.repositories.impl.UsersRepositoryFileImpl;
import ru.itis.services.AppService;
import ru.itis.models.Event;
import ru.itis.models.User;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        UsersRepository usersRepository = new UsersRepositoryFileImpl("users.txt");
        EventsRepository eventsRepository = new EventsRepositoryFileImpl("events.txt", "events_users.txt");
        AppService appService = new AppService(usersRepository, eventsRepository);

//        appService.signUp("admin@gmail.com", "qwerty007");
//        appService.signUp("marsel@gmail.com",     "qwerty008");

//        appService.addEvent("Практика по Java", LocalDate.now());
//        appService.addEvent("Практика по Golang", LocalDate.now().plusDays(1));

        appService.addUserToEvent("marsel@gmail.com", "Практика по Golang");

        //
        Event event =  eventsRepository.findByName("Практика по Java");
        if (event != null) {
            System.out.println(event);
        } else {
            System.out.println("Event not found.");
        }
        User foundUser = usersRepository.findByEmail("marsel@gmail.com");
        if(foundUser!= null){
            System.out.println(foundUser);
        } else {
            System.out.println("User not found.");
        }
        List<Event> eventsForUser = appService.getAllEventsByUser("marsel@gmail.com");
        if(eventsForUser != null){
            for(Event event2 : eventsForUser){
                System.out.println(event2);
            }
        } else {
            System.out.println("No events found for this user.");
        }

    }
}