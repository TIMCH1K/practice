package ru.itis.repositories.impl;

import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.io.*;

/**
 * 6/30/2023
 * Repository Example
 *
 * @author Marsel Sidikov (AIT TR)
 */
public class UsersRepositoryFileImpl implements UsersRepository {

    private final String fileName;

    public UsersRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void save(User model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
            writer.write(model.getId() + "|" + model.getEmail() + "|" + model.getPassword());
            writer.newLine();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public User findByEmail(String emailUser) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line;
            while((line = reader.readLine())!=null){
                String[] parts = line.split("\\|");
                if(parts.length >= 3){
                    String id = parts[0];
                    String email = parts[1];
                    String password = parts[2];
                    if(email.equals(emailUser)){
                        return new User(id, email, password);
                    }
                }

            }

        }
        catch (IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
        return null;
    }
}
