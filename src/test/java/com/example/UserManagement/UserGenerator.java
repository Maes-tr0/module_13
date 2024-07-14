package com.example.UserManagement;

import com.example.UserManagement.user.Address;
import com.example.UserManagement.user.Company;
import com.example.UserManagement.user.Geo;
import com.example.UserManagement.user.User;

import java.util.*;

public class UserGenerator {

    private static final Random random = new Random();
    private static final String[] names = {"Leanne Graham", "Ervin Howell", "Clementine Bauch", "Patricia Lebsack"};
    private static final String[] usernames = {"Bret", "Antonette", "Samantha", "Karianne"};
    private static final String[] emails = {"Sincere@april.biz", "Shanna@melissa.tv", "Nathan@yesenia.net", "Julianne.OConner@kory.org"};
    private static final String[] streets = {"Kulas Light", "Victor Plains", "Douglas Extension", "Hoeger Mall"};
    private static final String[] suites = {"Apt. 556", "Suite 879", "Suite 847", "Apt. 692"};
    private static final String[] cities = {"Gwenborough", "Wisokyburgh", "McKenziehaven", "South Elvis"};
    private static final String[] zipcodes = {"92998-3874", "90566-7771", "59590-4157", "53919-4257"};
    private static final String[] companyNames = {"Romaguera-Crona", "Deckow-Crist", "Romaguera-Jacobson", "Robel-Corkery"};
    private static final String[] catchPhrases = {"Multi-layered client-server neural-net", "Proactive didactic contingency", "Face to face bifurcated interface", "User-centric fault-tolerant solution"};
    private static final String[] bsArray = {"harness real-time e-markets", "synergize scalable supply-chains", "e-enable innovative applications", "repurpose dynamic infomediaries"};
    private static final String[] phones = {"1-770-736-8031 x56442", "010-692-6593 x09125", "1-463-123-4447", "493-170-9623 x156"};
    private static final String[] websites = {"hildegard.org", "anastasia.net", "ramiro.info", "kale.biz"};

    public static User generateUser() {
        int id = random.nextInt(1000);
        String name = getRandomElement(names);
        String username = getRandomElement(usernames);
        String email = getRandomElement(emails);

        Address address = new Address(
                getRandomElement(streets),
                getRandomElement(suites),
                getRandomElement(cities),
                getRandomElement(zipcodes),
                new Geo(
                        String.valueOf(random.nextDouble() * 180 - 90),
                        String.valueOf(random.nextDouble() * 360 - 180)
                )
        );

        String phone = getRandomElement(phones);
        String website = getRandomElement(websites);

        Company company = new Company(
                getRandomElement(companyNames),
                getRandomElement(catchPhrases),
                getRandomElement(bsArray)
        );

        //List<Post> posts = generatePosts(id);

        return new User(id, name, username, email, address, phone, website, company);
    }

    private static <T> T getRandomElement(T[] array) {
        return array[random.nextInt(array.length)];
    }

//    private static List<Post> generatePosts(int userId) {
//        List<Post> posts = new ArrayList<>();
//        int numPosts = random.nextInt(10) + 1;
//
//        for (int i = 0; i < numPosts; i++) {
//            int postId = random.nextInt(1000);
//            String title = "Post Title " + (i + 1);
//            String body = "This is the body of post " + (i + 1);
//            List<Comment> comments = generateComments(postId);
//
//            posts.add(new Post(userId, postId, title, body, comments));
//        }
//
//        return posts;
//    }
//
//    private static List<Comment> generateComments(int postId) {
//        List<Comment> comments = new ArrayList<>();
//        int numComments = random.nextInt(5) + 1;
//
//        for (int i = 0; i < numComments; i++) {
//            int commentId = random.nextInt(1000);
//            String name = "Commenter " + (i + 1);
//            String email = "commenter" + (i + 1) + "@example.com";
//            String body = "This is the body of comment " + (i + 1);
//
//            comments.add(new Comment(postId, commentId, name, email, body));
//        }
//
//        return comments;
//    }
}

