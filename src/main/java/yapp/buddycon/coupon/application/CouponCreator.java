package yapp.buddycon.coupon.application;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.coupon.domain.Coupon;
import yapp.buddycon.coupon.domain.CouponStorage;
import yapp.buddycon.coupon.dto.CouponSaveRequest;
import yapp.buddycon.user.domain.User;

@RequiredArgsConstructor
public class CouponCreator {

  private final CouponStorage couponStorage;

  @Transactional
  public Coupon create(CouponSaveRequest couponSaveRequest) {
    return couponStorage.save(toCoupon(couponSaveRequest));
  }

  private Coupon toCoupon(CouponSaveRequest request) {
    return new Coupon(request.name(), request.barcode(), request.expireTime(), request.imageUrl(), request.store(), request.memo(), new User());
  }
}
