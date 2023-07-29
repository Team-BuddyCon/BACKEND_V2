package yapp.buddycon.coupon.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
//import yapp.buddycon.coupon.application.CouponCreator;
//import yapp.buddycon.coupon.domain.Coupon;
//import yapp.buddycon.coupon.domain.CouponStorage;
//import yapp.buddycon.coupon.domain.Store;
//import yapp.buddycon.coupon.dto.CouponSaveRequest;
//import yapp.buddycon.user.domain.User;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class CouponCreatorTest {

//  @Test
//  void 쿠폰_생성_테스트(@Mock CouponStorage couponStorage) {
//
//    var request = new CouponSaveRequest("name", "barcode", LocalDate.of(2024, 1, 1), "imageUrl", Store.CU, "memo");
//    var creator = new CouponCreator(couponStorage);
//
//    creator.create(request);
//
//    verify(couponStorage).save(new Coupon("name", "barcode", LocalDate.of(2024,1,1), "imageUrl", Store.CU, "memo", new User()));
//    verifyNoMoreInteractions(couponStorage);
//  }

}