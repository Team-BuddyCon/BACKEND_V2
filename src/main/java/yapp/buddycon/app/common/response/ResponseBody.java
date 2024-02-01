package yapp.buddycon.app.common.response;

public record ResponseBody<T>(
    int status,
    String message,
    T body
) {
}
