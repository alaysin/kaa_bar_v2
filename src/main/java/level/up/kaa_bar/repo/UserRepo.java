package level.up.kaa_bar.repo;

import level.up.kaa_bar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    List<User> findUserByLogin(String login);

    public User findByLoginAndPassword(String login, String password);

    @Query(name = "findByIsAdmin")
    public List<User> findByIsAdmin(boolean isAdmin);

    public default User saveNewUserWithName(String login, String password, String name, String last_name) {
        User newUser = new User(login, password, name, last_name);
        save(newUser);
        return newUser;
    }

}
