package level.up.kaa_bar.repo;

import level.up.kaa_bar.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepoPaging extends PagingAndSortingRepository<User, Long> {
    @Query("select u from User u where u.login like concat('%', :query, '%')")
    Page<User> findContact(@Param("query") String query, Pageable pageRequest);

}