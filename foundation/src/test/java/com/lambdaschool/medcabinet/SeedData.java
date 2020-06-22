package com.lambdaschool.medcabinet;

import com.lambdaschool.medcabinet.models.User;
import com.lambdaschool.medcabinet.models.Useremail;
import com.lambdaschool.medcabinet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
        implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    /**
     * Generates test, seed data for our application
     * First a set of known data is seeded into our database.
     * Second a random set of data using Java Faker is seeded into our database.
     * Note this process does not remove data from the database. So if data exists in the database
     * prior to running this process, that data remains in the database.
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args)
            throws
            Exception
    {

        User u1 = new User("admin",
                           "password",
                           "admin@lambdaschool.local");
        u1.getUseremails()
                .add(new Useremail(u1,
                                   "admin@email.local"));
        u1.getUseremails()
                .add(new Useremail(u1,
                                   "admin@mymail.local"));

        userService.save(u1);


        User u2 = new User("cinnamon",
                           "1234567",
                           "cinnamon@lambdaschool.local");
        u2.getUseremails()
                .add(new Useremail(u2,
                                   "cinnamon@mymail.local"));
        u2.getUseremails()
                .add(new Useremail(u2,
                                   "hops@mymail.local"));
        u2.getUseremails()
                .add(new Useremail(u2,
                                   "bunny@email.local"));
        userService.save(u2);


        User u3 = new User("barnbarn",
                           "ILuvM4th!",
                           "barnbarn@lambdaschool.local");
        u3.getUseremails()
                .add(new Useremail(u3,
                                   "barnbarn@email.local"));
        userService.save(u3);


        User u4 = new User("puttat",
                           "password",
                           "puttat@school.lambda");
        userService.save(u4);


        User u5 = new User("misskitty",
                           "password",
                           "misskitty@school.lambda");
        userService.save(u5);
    }
}