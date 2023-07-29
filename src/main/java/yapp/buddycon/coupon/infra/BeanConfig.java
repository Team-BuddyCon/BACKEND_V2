package yapp.buddycon.coupon.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import yapp.buddycon.coupon.application.CouponCreator;
import yapp.buddycon.coupon.application.CouponReader;
import yapp.buddycon.coupon.domain.CouponStorage;

@Configuration("CouponBeanConfig")
@RequiredArgsConstructor
public class BeanConfig {

  @Bean
  CouponStorage couponStorage(JpaCouponRepository jpaCouponRepository) {
    return new JpaCouponStorage(jpaCouponRepository);
  }

  @Bean
  CouponCreator couponCreator(CouponStorage couponStorage) {
    return new CouponCreator(couponStorage);
  }

  @Bean
  CouponReader couponReader(CouponStorage couponStorage) {
    return new CouponReader(couponStorage);
  }
}
