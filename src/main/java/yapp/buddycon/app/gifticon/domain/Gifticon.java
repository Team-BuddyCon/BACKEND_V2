package yapp.buddycon.app.gifticon.domain;

import java.time.LocalDate;

public record Gifticon(
        Long id,
        Long userId,
        String imageUrl,
        String name,
        String memo,
        LocalDate expireDate,
        boolean used,
        GifticonStore gifticonStore,
        GifticonStoreCategory gifticonStoreCategory
) {



}

//class Gifticon1{
//
//    Supplier<Long> getId = (ThrowingSupplier<Long>) () -> {
//        throw new RuntimeException("초기화 되지 않았습니다.");
//    };
//
//    String barcode;
//    String imageUrl;
//    String name;
//    String memo;
//    LocalDate expireDate;
//    boolean used;
//    GifticonStore gifticonStore;
//    GifticonStoreCategory gifticonStoreCategory;
//
//
//}
//
//interface GitifconBuilder {
//    GitifconBuilder barcode();
//
//    GitifconBuilder imageUrl();
//}
//
//
//class GBuilder implements GitifconBuilder {
//
//    var barcdoe = n
//
//    @Override
//    public GitifconBuilder barcode() {
//        return null;
//    }
//
//    @Override
//    public GitifconBuilder imageUrl() {
//        return null;
//    }
//
//    Gifticon1 build() {
//
//    }
//}
//
//class Service {
//    void run() {
//        new GitifconBuilder()
//                .barcode()
//                .imageUrl()
//                .build()
//    }
//}