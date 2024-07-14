package com.example.UserManagement;

import com.example.UserManagement.user.Comment;
import com.example.UserManagement.user.User;

import java.util.*;

public class Demo {
    public static void main(String[] args) {
        IUserService userService = new UserService();
        User updatedUser = UserGenerator.generateUser();
        User user = UserGenerator.generateUser();

        // create
        System.out.println("Creating user:------------------------------------------");
        System.out.println(user);
        userService.create(user);
        System.out.println("User created!\n");

        // update
        System.out.println("Updating user with ID 1:--------------------------------");
        User originalUser = userService.getUserInformationByID(1);
        System.out.println("Original user: " + originalUser);
        userService.update(1, updatedUser);
        User updated = userService.getUserInformationByID(1);
        System.out.println("Updated user: " + updated + "\n");

        // delete
        System.out.println("Deleting user with ID 1:--------------------------------");
        User userToDelete = userService.getUserInformationByID(1);
        System.out.println("User to be deleted: " + userToDelete);
        boolean result = userService.delete(1);
        System.out.println("Delete result: " + result);
        User deletedUser = userService.getUserInformationByID(1);
        System.out.println("User after deletion (should be null): " + deletedUser + "\n");

        // all users
        System.out.println("Fetching all users:-------------------------------------");
        List<User> users = userService.getAllUsers();
        System.out.println("All users:\n" + users + "\n");

        // user by name
        System.out.println("Fetching user by username 'Bret':----------------------");
        User userByName = userService.getUserInformationByUserName("Bret");
        System.out.println("User by name:\n" + userByName + "\n");

        // user by ID
        System.out.println("Fetching user by ID 1:----------------------------------");
        User userByID = userService.getUserInformationByID(1);
        System.out.println("User by ID:\n" + userByID + "\n");

        // all comments from last post by userID
        System.out.println("Fetching all comments from last post by user ID 1:------");
        List<Comment> comments = userService.getAllCommentsFromLastPost(userService.getUserInformationByID(1));
        System.out.println("All comments:\n" + comments + "\n");

        // write all comments to file
        System.out.println("Writing all comments to file:---------------------------");
        userService.writeToFileAllComments(userService.getUserInformationByID(1));
        System.out.println("All comments written to files!\n");

        // display all open tasks for user
        System.out.println("Displaying all open tasks for user ID 1:----------------");
        userService.displayAllOpenTasksForUser(userService.getUserInformationByID(1));
    }
}
