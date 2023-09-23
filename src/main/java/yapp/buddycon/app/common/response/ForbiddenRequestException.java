package yapp.buddycon.app.common.response;

public class ForbiddenRequestException extends RuntimeException{
  public ForbiddenRequestException(String message) {
    super(message);
  }
}
