package com.pds.curiousmind.application;

import com.pds.curiousmind.model.contentblock.Difficulty;
import com.pds.curiousmind.model.library.implementation.CourseLibrary;
import com.pds.curiousmind.model.library.implementation.UserLibrary;
import com.pds.curiousmind.model.question.implementation.FlashCard;
import com.pds.curiousmind.model.registeredContentBlock.RegisteredContentBlock;
import com.pds.curiousmind.model.strategy.StrategyType;
import com.pds.curiousmind.model.user.User;
import com.pds.curiousmind.model.course.Course;
import com.pds.curiousmind.model.contentblock.ContentBlock;
import com.pds.curiousmind.model.question.Question;
import com.pds.curiousmind.model.question.option.Option;
import com.pds.curiousmind.model.registeredCourse.RegisteredCourse;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        CourseLibrary courseLibrary = CourseLibrary.INSTANCE;
        UserLibrary userLibrary = UserLibrary.INSTANCE;

        Course tempCourse = createRandomCourse();
        courseLibrary.add(tempCourse);

        User tempUser = createRandomUser();
        User user = userLibrary.add(tempUser);

        StrategyType[] strategies = StrategyType.values();
        StrategyType randomStrategy = strategies[new Random().nextInt(strategies.length)];
        user.registerInCourse(tempCourse, randomStrategy);
        userLibrary.update(user);

        printAllUserDetails(userLibrary.getAll());

    }

    public static void printAllUserDetails(List<User> userlist) {
        for (User userObtained : userlist) {
            System.out.println("User Details:");
            System.out.println("User Name: " + userObtained.getFullName());
            System.out.println("User Email: " + userObtained.getEmail());
            System.out.println("User Username: " + userObtained.getUsername());
            for (RegisteredCourse rc : userObtained.getRegisteredCourses()) {
                System.out.println("-- Registered Course --");
                System.out.println("Course Name: " + rc.getName());
                System.out.println("Strategy ID: " + rc.getStrategy());
                System.out.println("Content Blocks:");
                for (RegisteredContentBlock block : rc.getRegisteredContentBlocks()) {
                    System.out.println("Content Block Name: " + block.getName());
                    System.out.println("Difficulty Level: " + block.getDifficulty());
                    for (Question q : block.getQuestions()) {
                        System.out.println("-- Questions --");
                        System.out.println("Question Type: " + q.getClass().getName());
                        System.out.println("Question Statement: " + q.getStatement());
                        System.out.println("Correct Answer: " + q.getCorrectAnswer());
                        System.out.println("-- Options --");
                        for (Option opt : q.getOptions()) {
                            System.out.println("Option: " + opt.getLabel());
                        }
                    }
                }
            }
            System.out.println("-----------------------------------------------");
        }
        System.out.println("\n");
    }


    public static Course createRandomCourse() {
        Random random = new Random();
        String name = "Course_" + UUID.randomUUID().toString().substring(0, 8);
        String description = "Description_" + UUID.randomUUID().toString().substring(0, 8);
        String imageURL = "http://example.com/image" + random.nextInt(1000) + ".jpg";
        List<StrategyType> strategies = List.of(
                StrategyType.values()[random.nextInt(StrategyType.values().length)],
                StrategyType.values()[random.nextInt(StrategyType.values().length)]
        );

        // Create random options
        List<Option> options = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            options.add(new Option("Option " + (char)('A' + i)));
        }

        // Create a random question
        Question question = new FlashCard(
                "Indication_" + random.nextInt(100),
                "Statement_" + random.nextInt(100),
                options.getFirst().getLabel(),
                options
        );
        List<Question> questions = new ArrayList<>();
        questions.add(question);

        // Create a content block
        ContentBlock contentBlock = new ContentBlock(
                "Block_" + UUID.randomUUID().toString().substring(0, 4),
                Difficulty.values()[random.nextInt(Difficulty.values().length)],
                questions
        );
        List<ContentBlock> contentBlocks = new ArrayList<>();
        contentBlocks.add(contentBlock);

        // Create and return the course
        return new Course(
                name,
                description,
                imageURL,
                strategies,
                contentBlocks
        );
    }

    public static User createRandomUser() {
        Random random = new Random();
        String fullName = "User_" + UUID.randomUUID().toString().substring(0, 8);
        String email = "user" + random.nextInt(10000) + "@mail.com";
        String password = "pass" + UUID.randomUUID().toString().substring(0, 6);
        String username = "username_" + UUID.randomUUID().toString().substring(0, 6);

        return new User(fullName, email, password, username);
    }
}