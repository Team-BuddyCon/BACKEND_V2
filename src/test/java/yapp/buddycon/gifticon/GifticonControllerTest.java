package yapp.buddycon.gifticon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import yapp.buddycon.web.gifticon.adapter.GifticonController;
import yapp.buddycon.web.gifticon.adapter.response.GifticonVO;
import yapp.buddycon.web.gifticon.application.port.in.GifticonUseCase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class GifticonControllerTest {

  private MockMvc mockMvc;
  private final String BASE_URL = "/api/v1/gifticons";

  @Mock
  private GifticonUseCase gifticonUseCase;
  @InjectMocks
  private GifticonController gifticonController;

  @BeforeEach
  void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(gifticonController).build();
  }

  @Nested
  class getGifticons {

    @Test
    void 정상조회() throws Exception {
      // given
      doReturn(new PageImpl<>(Arrays.asList(
           new GifticonVO(),
          new GifticonVO(),
          new GifticonVO()))
      ).when(gifticonUseCase).getGifticons(any());

      // when
      final ResultActions resultActions = mockMvc.perform(
          MockMvcRequestBuilders.get(BASE_URL + "")
      );

      // then
      resultActions
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.totalElements").value("3"));
    }

    @Test
    void 빈리스트() throws Exception {
      // given
      doReturn(Page.empty()).when(gifticonUseCase)
          .getGifticons(any());

      // when
      final ResultActions resultActions = mockMvc.perform(
          MockMvcRequestBuilders.get(BASE_URL + "")
      );

      // then
      resultActions
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.totalElements").value("0"));
    }
  }

}