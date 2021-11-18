package level.up.kaa_bar.utils;

import org.springframework.data.domain.Page;

import java.util.Map;

public class PaginationParams<T>  {
    private final Page<T> page;

    public PaginationParams(Page<T> page) {
        this.page = page;
    }

    public Map<String, Object> getParams(int pageNum) {
        return Map.of(
                "currentPage", pageNum,
                "hasPrev", pageNum > 1,
                "hasNext", pageNum < page.getTotalPages(),
                "minPage", Math.max(pageNum - 3, 1),
                "maxPage", Math.min(pageNum + 3, page.getTotalPages())
        );
    }
}
