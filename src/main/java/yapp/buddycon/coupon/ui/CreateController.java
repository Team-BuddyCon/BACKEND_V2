package yapp.buddycon.coupon.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.common.ApiResponse;
import yapp.buddycon.coupon.application.CouponCreator;
import yapp.buddycon.coupon.dto.CouponSaveRequest;

@RestController
@RequiredArgsConstructor
public class CreateController {

  private final CouponCreator couponCreator;

  @PostMapping("api/v1/coupon")
  public ResponseEntity<?> createCoupon(@RequestBody CouponSaveRequest request) {
    couponCreator.create(request);
    return ApiResponse.success("쿠폰생성");
  }
}
