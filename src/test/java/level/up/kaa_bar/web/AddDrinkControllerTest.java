package level.up.kaa_bar.web;

import level.up.kaa_bar.dto.AddDrinkForm;
import level.up.kaa_bar.model.Drink;
import level.up.kaa_bar.repo.DrinksRepo;
import org.h2.command.dml.MergeUsing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;



import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestWebConfiguration.class)
@AutoConfigureMockMvc
public class AddDrinkControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DrinksRepo drinksRepo;

    @Test
    public void add() throws Exception {

        Drink drink = new Drink("test", "test", 1, 1, "test");

        AddDrinkForm addDrinkForm = new AddDrinkForm();

        Mockito.when(drinksRepo.save(drink)).thenReturn(drink);

        mvc.perform(post("/add")
                .with(user("admin").roles("ADMIN"))
                .with(csrf())
//                .param("Drink", "drink")
//                .param("name", "test")
//                .param("brand", "test")
//                .param("")


        ).andExpect(model().attribute("name", drink.getName()));

        Mockito.verify(drinksRepo, Mockito.times(1))
                .saveNewDrink("test", "test", 1, 1, "test");
    }
}
