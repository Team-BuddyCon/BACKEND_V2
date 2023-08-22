package yapp.buddycon.common.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class PagingDTO {

  @Min(value = 0)
  private int pageNumber = 0;
  @Min(value = 1)
  @Max(value = 1000)
  private int rowCount = 20;

  public Pageable toPageable() {
    return PageRequest.of(pageNumber, rowCount);
  }

  public Pageable toPageable(Sort sort) {
    return PageRequest.of(pageNumber, rowCount, sort);
  }
}
