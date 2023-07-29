package yapp.buddycon.coupon.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.coupon.domain.Coupon;
import yapp.buddycon.coupon.domain.CouponStorage;

@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponReader {

  private final CouponStorage couponStorage;

  public Slice<Coupon> read(Pageable pageable) {
    return couponStorage.readWithPaging(pageable, 1L);
  }
}
