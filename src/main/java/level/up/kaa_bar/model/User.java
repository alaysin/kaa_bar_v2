package level.up.kaa_bar.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (uniqueConstraints={@UniqueConstraint(columnNames={"login"})}, name = "users")
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_seq")
    @SequenceGenerator(name = "users_id_seq", allocationSize = 1)
    @Getter
    private int id;

    @Column(name = "login", unique = true, length = 30)
    @NotBlank
    @Getter
    @Setter
    private String login;

    @Column(name = "password", unique = false, length = 100)
    @NotBlank
    @Getter
    @Setter
    private String password;

    @Column (name = "is_admin")
    @Getter
    @Setter
    private boolean isAdmin;

    @Column(name = "name", unique = false, length = 100)
    @NotBlank
    @Getter
    @Setter
    private String name;

    @Column(name = "last_name", unique = false, length = 100)
    @NotBlank
    @Getter
    @Setter
    private String last_name;


    @Column (name = "to_delete")
    @Getter
    @Setter
    private boolean toDelete;
    /*
        @OneToMany(mappedBy = "manager")
        private List<Drink> drinks = new ArrayList<>();
    */
    public User(String login, String password, boolean isAdmin, String name, String last_name) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
        this.name = name;
        this.last_name = last_name;
        this.toDelete = false;
    }

    public User(String login, String password, String name, String last_name) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.last_name = last_name;
        this.toDelete = false;
        this.isAdmin = false;
    }

}
