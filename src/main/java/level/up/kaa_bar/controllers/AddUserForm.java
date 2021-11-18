package level.up.kaa_bar.controllers;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class AddUserForm {

    @NotBlank
    @Length(max = 50)
    @Getter @Setter
    private String login;

    @NotBlank
    @Length(max = 50)
    @Getter @Setter
    private String password;

    @NotBlank
    @Length(max = 50)
    @Getter @Setter
    private String name;

    @NotBlank
    @Length(max = 50)
    @Getter @Setter
    private String last_name;

    @Getter @Setter
    private boolean isAdmin;

    @Getter @Setter
    private boolean toDelete;

    public AddUserForm() {
    }


    public AddUserForm(String login, String password, String name, String last_name) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.last_name = last_name;
        this.isAdmin = false;
        this.toDelete = false;
    }
}
