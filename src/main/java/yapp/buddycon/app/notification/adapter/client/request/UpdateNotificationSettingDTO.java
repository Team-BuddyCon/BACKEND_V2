package yapp.buddycon.app.notification.adapter.client.request;

import jakarta.validation.constraints.NotNull;

public record UpdateNotificationSettingDTO(
    @NotNull Boolean activated,
    @NotNull Boolean fourteenDaysBefore,
    @NotNull Boolean sevenDaysBefore,
    @NotNull Boolean threeDaysBefore,
    @NotNull Boolean oneDayBefore,
    @NotNull Boolean theDay
) {

}
