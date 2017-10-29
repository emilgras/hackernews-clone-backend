package com.daef;

import com.daef.models.Post;
import com.daef.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableMongoRepositories("com.daef.repositories")


@SpringBootApplication//(scanBasePackages = {"com.daef.repositories", "controllers","security","com.daef.models"})
public class Application implements CommandLineRunner {

    @Autowired
    private PostRepository repository;
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of articles
        repository.save(new Post("Charlie", "story", "aioCQsy3E", "Student Guide 101", "Cool stuff", -1, 2));
        repository.save(new Post("Frank", "story", "aioCQsy3E", "Student Guide 102", "Bad  stuff", -1, 2));

        // fetch all articles
        System.out.println("Articles found with findAll():");
        System.out.println("-------------------------------");
        repository.findAll().forEach((post) -> {
            System.out.println(post.PostText);
        });
        System.out.println();

        // fetch an individual article
        System.out.println("Post found with findPostByUser(\"Charlie\")");
        System.out.println("--------------------------------");
        System.out.println(repository.findPostByUserName("Charlie").PostText);

    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}