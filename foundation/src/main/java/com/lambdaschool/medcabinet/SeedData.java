package com.lambdaschool.medcabinet;


import com.lambdaschool.medcabinet.models.Strain;
import com.lambdaschool.medcabinet.models.User;
import com.lambdaschool.medcabinet.services.StrainService;
import com.lambdaschool.medcabinet.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    StrainService strainService;

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

        Set<String> f1 = new HashSet<>();
        f1.add("Earthy");
        f1.add("Pine");
        f1.add("Minty");
        Set<String> p1 = new HashSet<>();
        p1.add("Euphoric");
        p1.add("Happy");
        p1.add("Creative");
        Set<String> n1 = new HashSet<>();
        n1.add("Dizzy");
        n1.add("Dry Mouth");
        n1.add("Paranoid");
        Set<String> m1 = new HashSet<>();
        m1.add("Depression");
        m1.add("Pain");
        m1.add("Stress");

        String desc = "Alaskan Ice by Green House Seeds is a powerful sativa that crosses a euphoric White Widow hybrid with the energizing buzz of Haze. Frostlike resin blankets the buds in a promise of soaring psychoactivity, anchored only by its moderate CBD content. The intensity of this 70% sativa strain is recommended for evening consumption and unproductive weekends. Alaskan Ice is a slight variant of Moby Dick, but poses a greater challenge to growers; cultivators with the expertise to raise Alaskan Ice will be rewarded with a highly potent harvest of sour, spicy buds following a 9 week flowering period. The high resin content of Alaskan Ice has made this strain a favorite among hash producers and patients with severe symptoms.";
        String desc2 = "For those craving a cerebral buzz with a citrus kick, 3C Agent Tangie is perfect. The glittery colas are light green with a zesty, floral flavor. Its effects linger in the crown of the skull and disperse throughout the body in steady waves of invigoration. This strain may assist those suffering from perpetual procrastination, depression, and fatigue";

        Strain s1 = new Strain("Alaskan Ice",123,f1, p1, n1, m1,"hybrid", 4.4,desc);
        Strain s2 = new Strain("Agent Tangie",456,f1, p1, n1, m1,"sativa", 4.5,desc2);

        s1 = strainService.save(s1);
        s2 = strainService.save(s2);

        User u1 = new User("admin",
                           "password",
                           "admin@lambdaschool.local");

        u1.getStrains().add(s1);
        u1.getStrains().add(s2);

        userService.save(u1);



        User u2 = new User("cinnamon",
                           "1234567",
                           "cinnamon@lambdaschool.local");

        userService.save(u2);


        User u3 = new User("barnbarn",
                           "ILuvM4th!",
                           "barnbarn@lambdaschool.local");

        userService.save(u3);


        User u4 = new User("puttat",
                           "password",
                           "puttat@school.lambda");
        userService.save(u4);


        User u5 = new User("misskitty",
                           "password",
                           "misskitty@school.lambda");
        userService.save(u5);




        // using JavaFaker create a bunch of regular users
        // https://www.baeldung.com/java-faker
        // https://www.baeldung.com/regular-expressions-java

//        FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
//                                                                    new RandomService());
//        Faker nameFaker = new Faker(new Locale("en-US"));
//
//        for (int i = 0; i < 25; i++)
//        {
//            new User();
//            User fakeUser;
//            fakeUser = new User(nameFaker.name()
//                                        .username(),
//                                "password",
//                                nameFaker.internet()
//                                        .emailAddress());
//            userService.save(fakeUser);
//        }
    }
}