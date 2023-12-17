package yapp.buddycon.app.gifticon.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Gifticon {

    private final Long id;
    private final Long userId;
    private final String imageUrl;

    private String name;
    private String memo;
    private LocalDate expireDate;
    private boolean used;
    private boolean deleted;
    private GifticonStore gifticonStore;
    private GifticonStoreCategory gifticonStoreCategory;

    @Builder
    public Gifticon(Long id, Long userId, String imageUrl, String name, String memo,
                    LocalDate expireDate, boolean used, boolean deleted, GifticonStore gifticonStore) {
        this.id = id;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.memo = memo;
        this.expireDate = expireDate;
        this.used = used;
        this.gifticonStore = gifticonStore;
        this.gifticonStoreCategory = gifticonStore.getCategory();
    }

    public void modify(String name, String memo, LocalDate expireDate, GifticonStore store) {
        this.name = name;
        this.memo = memo;
        this.expireDate = expireDate;
        this.gifticonStore = store;
    }

    public void delete() {
        this.deleted = true;
    }
}