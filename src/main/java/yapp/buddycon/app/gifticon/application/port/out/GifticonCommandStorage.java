package yapp.buddycon.app.gifticon.application.port.out;

import yapp.buddycon.app.gifticon.domain.Gifticon;

public interface GifticonCommandStorage {
    Gifticon save(Gifticon gifticon);
}
