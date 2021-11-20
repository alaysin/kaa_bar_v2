package level.up.kaa_bar.model;

import level.up.kaa_bar.repo.DrinksRepo;
import org.apache.catalina.LifecycleState;
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

import java.util.List;

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
        System.out.println(drink.getId());

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

    @Test
    public void testFindingByDrinkBrand() {
//        assertNull(drinksRepo.findByBrand("DrinkBrand"));
        List<Drink> found = drinksRepo.findByBrand("DrinkBrand1");
        assertNotNull(found);
        assertEquals("DrinkBrand1", found.get(0).getBrand());
    }

    @Test
    public void testFindByBrandAndAndTyp() {
        assertNull(drinksRepo.findByName("DrinkTest"));
        Drink found = drinksRepo.findByBrandAndAndTyp("DrinkBrand1", "Beer");
        assertNotNull(found);
        assertEquals("DrinkBrand1", found.getBrand());
        assertEquals("Beer", found.getTyp());

    }

    @Test
    public void testFindByPrice() {
//        assertNull(drinksRepo.findByPrice(100));
        List<Drink> found = drinksRepo.findByPrice(10);
        assertNotNull(found);
        assertEquals(10, found.get(0).getPrice());
    }

    @Test
    public void testSaveNewDrink() {
        Drink added = drinksRepo.saveNewDrink("DrinkTest1", "DrinkBrand1", 10, 10, "Beer");
        assertTrue(drinksRepo.findById(added.getId()).isPresent());
    }

    @Test
    public void testSaveDrink() {
        Drink testDrink = new Drink("DrinkTest1", "DrinkBrand1", 10, 10, "Beer");
        Drink added = drinksRepo.saveDrink(testDrink);
        assertTrue(drinksRepo.findById(added.getId()).isPresent());
    }
}
