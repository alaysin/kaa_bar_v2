package level.up.kaa_bar.controllers;

import level.up.kaa_bar.model.User;
import level.up.kaa_bar.repo.UserRepo;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegistrationFormController {

    @Autowired
    private UserRepo userRepo;

    private final PasswordEncoder encoder;

    public RegistrationFormController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @PostMapping("/registration")
    @Transactional
    public String registration(
            Model model,
            @Valid @ModelAttribute("form") AddUserForm form,
//            @ModelAttribute("user-session") UserSession session,
            BindingResult bindingResult
    ) {
        model.addAttribute("form", form);
        model.addAttribute("bindingResult", bindingResult);


        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User registered;

        try {
            registered = createUser(form);
            //usersDAO.saveNewUserWithName(form.getUserLogin(), form.getPassword(), form.getUserName());
        } catch (ConstraintViolationException constraintViolationException) {
            bindingResult.addError(new FieldError("form",
                    "login", "Login is not available"
            ));
            return "registration";
        }
        model.addAttribute("login", registered.getLogin());
        model.addAttribute("password", registered.getPassword());
        model.addAttribute("isAdmin", registered.isAdmin());
        model.addAttribute("name", registered.getName());
        model.addAttribute("last_name", registered.getLast_name());
        model.addAttribute("toDelete", registered.isToDelete());
        return "/login";
    }

    @GetMapping("/registration")
    public String openRegistrationForm(
            Model model,
            @ModelAttribute AddUserForm form,
            BindingResult bindingResult
    ) {
        model.addAttribute("form", form);
        model.addAttribute("bindingResult", bindingResult);

        return "registration";
    }

    private User createUser(AddUserForm addUserForm) {
        User created;
        created = userRepo.saveNewUserWithName(addUserForm.getLogin(), encoder.encode(addUserForm.getPassword()), addUserForm.getName(), addUserForm.getLast_name());
        return created;
    }
}
