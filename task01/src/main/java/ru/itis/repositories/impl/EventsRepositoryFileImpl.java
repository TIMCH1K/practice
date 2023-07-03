package ru.itis.repositories.impl;

import ru.itis.models.Event;
import ru.itis.models.User;
import ru.itis.repositories.EventsRepository;

import javax.xml.crypto.Data;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;


/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class EventsRepositoryFileImpl implements EventsRepository {
    private final String eventFileName;
    private final String eventsAndUsersFileName;

    public EventsRepositoryFileImpl(String eventFileName, String eventsAndUsersFileName) {
        this.eventFileName = eventFileName;
        this.eventsAndUsersFileName = eventsAndUsersFileName;
    }

    @Override
    public void save(Event model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventFileName, true))){
            writer.write(model.getId() + "|" + model.getName() + "|" + model.getDate());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Event findByName(String nameEvent) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("events.txt"));
            String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 3) {
                        String id = parts[0];
                        String name = parts[1];
                        LocalDate date = LocalDate.parse(parts[2], formatter);

                        if (name.equals(nameEvent)) {
                            return new Event(id, date, name);
                        }
                }
            }
            }
            catch(IOException e){
                System.out.println("Error");
                e.printStackTrace();
            }
        return null;
        }

    @Override
    public List<Event> findAllByMembersContains(User user) {
        List<Event> events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("events_users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 2) {
                    String userId = parts[0];
                    String eventId = parts[1];
                    if (userId.equals(user.getId())) {
                        Event event = findEventById(eventId);
                        if (event != null) {
                            events.add(event);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return events;
    }

    private Event findEventById(String id) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        try (BufferedReader reader = new BufferedReader(new FileReader("events.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String eventId = parts[0];
                    LocalDate date = LocalDate.parse(parts[2], formatter);
                    String name = parts[1];
                    if (eventId.equals(id)) {
                        return new Event(eventId, date, name);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void saveUserToEvent(User user, Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsAndUsersFileName, true))){
            writer.write(user.getId() + "|" + event.getId());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
