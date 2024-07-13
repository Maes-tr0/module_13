package com.example.UserManagement;

import java.util.List;

public interface IUserService {
    void create(User user);
    void update(int id, User user);
    boolean delete(int id);
    List<User> getAllUsers();
    User getUserInformationByUserName(String username);
    User getUserInformationByID(int id);
    List<Comment> getAllCommentsFromLastPost(User user);
    void writeToFileAllComments(User user);
    void displayAllOpenTasksForUser(User user);
}
