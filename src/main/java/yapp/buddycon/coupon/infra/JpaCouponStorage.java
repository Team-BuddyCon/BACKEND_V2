package yapp.buddycon.coupon.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import yapp.buddycon.coupon.domain.Coupon;
import yapp.buddycon.coupon.domain.CouponStorage;

@RequiredArgsConstructor
public class JpaCouponStorage implements CouponStorage {

  private final JpaCouponRepository jpaCouponRepository;

  @Override
  public Coupon save(Coupon coupon) {
    return jpaCouponRepository.save(coupon);
  }

  @Override
  public Coupon findById(Long id) {
    return jpaCouponRepository.findById(id).orElseThrow(NotExistentMemberException::new);
  }

  @Override
  public Slice<Coupon> readWithPaging(Pageable pageable, Long userId) {
    return jpaCouponRepository.findAllByUserId(userId, pageable);
  }

}
