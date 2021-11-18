package level.up.kaa_bar.controllers;

import level.up.kaa_bar.model.User;
import level.up.kaa_bar.repo.UserRepoPaging;
import level.up.kaa_bar.utils.PaginationParams;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserRepoPaging userRepoPaging;

    @GetMapping("")
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                        @RequestParam(value = "q", required = false, defaultValue = "") String query,
                        Model model) {

        PageRequest pageRequest = PageRequest.of(page - 1, 10);

        String queryString = query.trim();

        Page<User> users = hasText(queryString) ?
                userRepoPaging.findContact(queryString, pageRequest)
                : userRepoPaging.findAll(pageRequest);

        model.addAttribute("query", query);

        model.addAttribute("users", users.stream().collect(Collectors.toList()));

        PaginationParams<User> paginationParams = new PaginationParams<>(users);
        model.addAllAttributes(paginationParams.getParams(page));

        return "users";
    }

    @PostMapping
    @Transactional
    public @ResponseBody
    String create(@RequestParam("login") String login,
                  @RequestParam("name") String name,
                  @RequestParam("last_name") String last_name,
                  @RequestParam("password") String password
    ) {
        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setLast_name(last_name);
        user.setPassword(password);
        user.setAdmin(false);
        user.setToDelete(false);

        userRepoPaging.save(user);

        return "OK";
    }
}
