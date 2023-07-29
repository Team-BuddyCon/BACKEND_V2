package yapp.buddycon.coupon.infra;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import yapp.buddycon.TestDatabaseInitTest;
import yapp.buddycon.coupon.application.CouponReader;
import yapp.buddycon.coupon.domain.Coupon;
import yapp.buddycon.coupon.domain.CouponStorage;
import yapp.buddycon.coupon.domain.Store;
import yapp.buddycon.user.domain.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertThat;

@TestDatabaseInitTest
@Transactional
class JpaCouponStorageTest {

  @Autowired
  private CouponStorage couponStorage;

  @Test
  void findById() {
    var coupon = couponStorage.findById(1L);
    assertEquals(coupon.getName(), "name1");
  }

  @Test
  void save() {
    var coupon = new Coupon("name8", "barcode", LocalDate.now(), "imageUrl", Store.CU, "memo", new User());

    var result = couponStorage.save(coupon);

    assertNotNull(result.getId());
    assertEquals(result.getName(), "name8");
  }

//  @Test
//  void readWithPaging() {
//    var sort = Sort.by("name,DESC");
//    var pageRequest = PageRequest.of(0, 2, sort);
//
//    var result = couponStorage.readWithPaging(pageRequest, 1L);
//
//    assertTrue(result.hasNext());
//    assertFalse(result.hasPrevious());
//    assertEquals(result.getContent().get(0).getName(), "name1");
//    assertEquals(result.getContent().get(1).getName(), "name2");
//
//  }
}