package yapp.buddycon.coupon.infra;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import yapp.buddycon.coupon.domain.Coupon;

import java.time.LocalDate;
import java.util.Optional;

interface JpaCouponRepository extends JpaRepository<Coupon, Long> {
  Coupon save(Coupon entity);

  Optional<Coupon> findById(Long id);

  Slice<Coupon> findAllByUserId(Long userId, Pageable pageable);
}
