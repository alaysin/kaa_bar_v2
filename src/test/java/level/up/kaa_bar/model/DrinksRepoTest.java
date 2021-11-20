package level.up.kaa_bar.model;

import level.up.kaa_bar.repo.DrinksRepo;
import org.junit.Assert;
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
@Transactional
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class DrinksRepoTest {

    @Autowired
    private DrinksRepo drinksRepo;

    @Before
    @Transactional
    public void configure() {
        Drink drink = new Drink("DrinkTest1", "DrinkBrand1", 10, 10, "Beer");
        Drink drink1 = new Drink("DrinkTest2", "DrinkBrand2", 10, 10, "Beer");

        drinksRepo.save(drink);
        drinksRepo.save(drink1);
    }

    @Test
    public void testFindingByDrinkName() {
        assertNull(drinksRepo.findByName("DrinkTest"));
        Drink found = drinksRepo.findByName("DrinkTest1");
        assertNotNull(found);
        assertEquals("DrinkTest1", found.getName());

    }

}
