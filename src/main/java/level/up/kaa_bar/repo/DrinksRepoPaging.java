package level.up.kaa_bar.repo;

import level.up.kaa_bar.model.Drink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface DrinksRepoPaging extends PagingAndSortingRepository<Drink, Long> {
    @Query("select d from Drink  d where d.name like concat('%', :query, '%')")
    Page<Drink> findContact(@Param("query") String query, Pageable pageRequest);
}