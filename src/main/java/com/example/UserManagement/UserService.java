package com.example.UserManagement;

import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserService implements IUserService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private static final String COMMENTS_URL = "https://jsonplaceholder.typicode.com/comments";
    private static final String TODOS_URL = "https://jsonplaceholder.typicode.com/todos";
    private final HttpClient client;
    private final ObjectMapper mapper;

    public UserService() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void create(User user) {
        try {
            String requestBody = mapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, User user) {
        try {
            String requestBody = mapper.writeValueAsString(user);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserInformationByUserName(String username) {
        try {
            List<User> users = getAllUsers();
            if (users != null) {
                Optional<User> user = users.stream()
                        .filter(u -> u.getUsername().equalsIgnoreCase(username))
                        .findFirst();
                return user.orElse(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUserInformationByID(int id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + id))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), User.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Comment> getAllCommentsFromLastPost(User user) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(POSTS_URL + "?userId=" + user.getID()))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Post> posts = mapper.readValue(response.body(), new TypeReference<>() {
            });

            if (posts.isEmpty()) {
                return null;
            }

            Post lastPost = posts.getLast();

            request = HttpRequest.newBuilder()
                    .uri(URI.create(COMMENTS_URL + "?postId=" + lastPost.getId()))
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return mapper.readValue(response.body(), new TypeReference<>() {
            });

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void writeToFileAllComments(User user) {
        try {
            if (user != null) {
                List<Comment> comments = getAllCommentsFromLastPost(user);
                if (comments != null && !comments.isEmpty()) {
                    String fileName = "src/main/resources/comments.json";
                    File commentsFile = new File(fileName);
                    mapper.writerWithDefaultPrettyPrinter().writeValue(commentsFile, comments);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void displayAllOpenTasksForUser(User user) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(TODOS_URL + "?userId=" + user.getID()))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            List<Task> tasks = mapper.readValue(response.body(), new TypeReference<>(){});

            List<Task> openTasks = tasks.stream()
                    .filter(task -> !task.isCompleted())
                    .toList();

            openTasks.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
