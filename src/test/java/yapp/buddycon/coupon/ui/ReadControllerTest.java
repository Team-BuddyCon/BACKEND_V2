package yapp.buddycon.coupon.ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import yapp.buddycon.TestDatabaseInitTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@TestDatabaseInitTest
class ReadControllerTest {

  @InjectMocks
  private ReadController target;

  private MockMvc mockMvc;

  @BeforeEach
  public void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(target)
      .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
      .build();
  }

  @Test
  void 쿠폰_생성() throws Exception {
    final String url = "/api/v1/coupon";

    var result = mockMvc.perform(get(url)
      .param("page", "0")
      .param("size", "2")
      .param("sort", "id,DESC")
    );

    result.andExpect(status().isOk());
  }
}