package yapp.buddycon.app.common;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import yapp.buddycon.app.auth.adapter.AuthenticationArgumentResolver;
import yapp.buddycon.app.auth.adapter.jwt.JwtTokenDecryptor;

public class MockAuthenticationArgumentResolver extends AuthenticationArgumentResolver {

    public MockAuthenticationArgumentResolver() {
        super(new JwtTokenDecryptor());
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        return new AuthUser(1L);
    }
}
