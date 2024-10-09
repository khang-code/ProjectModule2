package com.codegym.service;

import com.codegym.entity.people.Reader;
import com.codegym.entity.people.Role;
import com.codegym.entity.people.Sponsor;
import com.codegym.entity.people.User;
import com.codegym.service_show.UserServiceShow;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserService {
    private static final String USER_FILE_PATH = "src/com/codegym/users.csv";

    private UserServiceShow userServiceShow;
    private Map<String, User> users = new HashMap<>();
    private final Map<String, Reader> readers = new HashMap<>();
    private final Map<String, Sponsor> sponsors = new HashMap<>();
    private final Map<String, List<Role>> pendingSponsors = new HashMap<>();

    public UserService(UserServiceShow userServiceShow) {
        this.userServiceShow = userServiceShow;
        this.users = readUsersFromFile();
    }

    public boolean isUsernameTaken(String username) {
        return users.containsKey(username);
    }

    public void registerReader(String username, String password, String phoneNumber, String email) {
        if (isUsernameTaken(username)) {
            userServiceShow.showMessage("Username is already in use, please choose another one.");
        } else {
            User newUser = new User(username, username, password);
            Reader reader = new Reader(username, username, password, phoneNumber);
            users.put(username, newUser);
            readers.put(username, reader);
            addRoleToUser(username, Role.READER_USER);
            writeUsersToFile(users); // Fixed typo in method name
        }
    }

    public void addSponsor(String username, String phoneNumber, String email, Role role) throws IllegalAccessException {
        User user = users.get(username);
        if (user == null) {
            throw new IllegalAccessException("No such user");
        } else {
            user.addRole(role);
            writeUsersToFile(users);
        }
    }

    public boolean removeUser(String username) {
        boolean removed = users.remove(username) != null;
        if (removed) {
            writeUsersToFile(users);
        }
        return removed;
    }

    public User getUser(String username) {
        return users.get(username);
    }

    public Map<String, Reader> getAllReaders() {
        return readers;
    }

    public void writeUsersToFile(Map<String, User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_PATH))) {
            writer.write("UserName,Password,PhoneNumber,Email,FullName,Role\n");

            for (User user : users.values()) {
                String rolesString;
                rolesString = user.getRoles().stream()
                        .map(Role::name)
                        .collect(Collectors.joining("-"));

                writer.write(String.format("%s,%s,%s,%s,%s,%s%n",
                        user.getUsername(),
                        user.getPassword(),
                        user.getPhoneNumber(),
                        user.getEmail(),
                        rolesString));
            }
        } catch (IOException e) {
            System.err.println("Error writing data to file: " + USER_FILE_PATH);
            e.printStackTrace();
        }
    }

    private Map<String, User> readUsersFromFile() {
        Map<String, User> users = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            reader.readLine(); // Skip header
            String line;

            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");

                if (split.length != 6) {
                    System.err.println("Invalid data: " + line);
                    continue;
                }

                String username = split[0].trim();
                String password = split[1].trim();
                String phoneNumber = split[2].trim();
                String email = split[3].trim();
                String roleString = split[4].trim();

                User user = new User(username, password, phoneNumber);

                Set<Role> roles = Arrays.stream(roleString.split("-"))
                        .map(role -> {
                            try {
                                return Role.valueOf(role);
                            } catch (IllegalArgumentException e) {
                                System.err.println("Invalid role: " + role);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet());

                user.setRoles(roles);
                users.put(username, user);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + USER_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + USER_FILE_PATH);
            e.printStackTrace();
        }
        return users;
    }

    private void addRoleToUser(String username, Role role) {
        User user = users.get(username);
        if (user != null) {
            user.addRole(role);
            writeUsersToFile(users);
        }
    }
}
