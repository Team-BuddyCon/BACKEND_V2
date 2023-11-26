package yapp.buddycon.app.notification.adapter.client.response;

public record NotificationSettingResponseDTO(
    boolean activated,
    boolean fourteenDaysBefore,
    boolean sevenDaysBefore,
    boolean threeDaysBefore,
    boolean oneDayBefore,
    boolean theDay
) {

}
