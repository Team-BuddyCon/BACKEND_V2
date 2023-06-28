package yapp.buddycon.coupon.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface CouponStorage {
  Coupon save(Coupon coupon);
  Coupon findById(Long id);

  Slice<Coupon> readWithPaging(Pageable pageable, Long userId);
}
