package yapp.buddycon.coupon.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
//import yapp.buddycon.coupon.domain.CouponStorage;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class CouponReaderTest {

//  @Test
//  void 쿠폰_읽기_테스트(@Mock CouponStorage couponStorage) {
//    var reader = new CouponReader(couponStorage);
//    var sort = Sort.by("id,DESC");
//    var pageRequest = PageRequest.of(0, 2, sort);
//
//    reader.read(pageRequest);
//
//    verify(couponStorage).readWithPaging(pageRequest, 1L);
//    verifyNoMoreInteractions(couponStorage);
//  }
}