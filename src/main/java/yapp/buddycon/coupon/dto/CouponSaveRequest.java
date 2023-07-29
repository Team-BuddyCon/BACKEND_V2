package yapp.buddycon.coupon.dto;

import yapp.buddycon.coupon.domain.Store;

import java.time.LocalDate;

public record CouponSaveRequest(
  String name, String barcode, LocalDate expireTime, String imageUrl, Store store, String memo
) {
}
