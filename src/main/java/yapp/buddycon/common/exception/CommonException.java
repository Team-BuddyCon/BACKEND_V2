package yapp.buddycon.common.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException {

  private CommonExceptionCode commonExceptionCode;

  public CommonException(CommonExceptionCode commonExceptionCode) {
    super(commonExceptionCode.getMessage());
    this.commonExceptionCode = commonExceptionCode;
  }

}
