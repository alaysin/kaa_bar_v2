package level.up.kaa_bar.controllers;

import level.up.kaa_bar.dto.AddUserForm;
import level.up.kaa_bar.model.User;
import level.up.kaa_bar.repo.UserRepo;
import lombok.AllArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
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

    private final String REG = "registration";

    public RegistrationFormController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @GetMapping("/registration")
    public String openRegistrationForm(
            Model model,
            @ModelAttribute AddUserForm form,
            BindingResult bindingResult
    ) {
        model.addAttribute("form", form);
        model.addAttribute("bindingResult", bindingResult);

        return REG;
    }

    @PostMapping("/registration")
    @Transactional
    public String registration(
            Model model,
            @ModelAttribute("form")
            @Valid AddUserForm form,
            BindingResult bindingResult
    ) {
//        model.addAttribute("form", form);
//        model.addAttribute("bindingResult", bindingResult);


        if (bindingResult.hasErrors()) {
            return REG;
        }

        User registered;

        try {
            registered = createUser(form);

        } catch (Exception e) {
            bindingResult.addError(
                    new FieldError(
                            "form",
                            "login",
                            "Login is not available"
                    ));
            return REG;
        }
        model.addAttribute("login", registered.getLogin());
        model.addAttribute("password", registered.getPassword());
        return "/login";
    }


    private User createUser(AddUserForm addUserForm) {
        User created;
        created = userRepo.saveNewUser(
                addUserForm.getLogin(),
                encoder.encode(addUserForm.getPassword()),
                addUserForm.getName(),
                addUserForm.getLast_name());
        return created;
    }
}
