package level.up.kaa_bar.model;

import level.up.kaa_bar.repo.UserRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Before
    public void configure() {
        User newUser= new User("login", "password", "name", "last_name");
        userRepo.save(newUser);
    }
    @Test
    public void findByLogin() {
        assertNull(userRepo.findUserByLogin("non existing user"));
        User found = userRepo.findUserByLogin("login");
        assertNotNull(found);
        assertEquals("login", found.getLogin());
    }
}
