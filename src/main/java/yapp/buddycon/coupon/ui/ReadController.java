package yapp.buddycon.coupon.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import yapp.buddycon.common.ApiResponse;
import yapp.buddycon.coupon.application.CouponReader;

@RestController
@RequiredArgsConstructor
public class ReadController {

  private final CouponReader couponReader;

  @GetMapping("/api/v1/coupon")
  public ResponseEntity<?> read(Pageable pageable) {
    return ApiResponse.successWithResponseBody(couponReader.read(pageable), "쿠폰 페이지네이션");
  }
}
