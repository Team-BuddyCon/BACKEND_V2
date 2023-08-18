package yapp.buddycon.app.common.response;

public record ResponseBody(
        int status,
        String message,
        Object body
) {
}
