package yapp.buddycon.app.gifticon;

import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.SliceImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import yapp.buddycon.app.gifticon.adapter.client.ReadGifticonController;
import yapp.buddycon.app.gifticon.adapter.client.response.GifticonResponseDTO;
import yapp.buddycon.app.gifticon.application.port.in.ReadGifticonUseCase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ReadGifticonControllerTest {

  private MockMvc mockMvc;
  private final String BASE_URL = "/api/v1/gifticons";

  @Mock
  private ReadGifticonUseCase gifticonUseCase;
  @InjectMocks
  private ReadGifticonController readGifticonController;

  @BeforeEach
  void init() {
    mockMvc = MockMvcBuilders.standaloneSetup(readGifticonController).build();
  }

  @Nested
  class getUnavailableGifticons {

    private final String API_PATH = "/unavailable";

    @Test
    void 정상_조회() throws Exception {
      // given
      when(gifticonUseCase.getUnavailableGifticons(any(), any()))
              .thenReturn(new SliceImpl<>(Arrays.asList(any(GifticonResponseDTO.class), any(GifticonResponseDTO.class))));

      // when
      final ResultActions resultActions = mockMvc.perform(
          MockMvcRequestBuilders.get(BASE_URL + API_PATH)
      );

      // then
      resultActions
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.body.size").value("2"));
    }

    @Test
    void 빈_리스트() throws Exception {
      // given
      when(gifticonUseCase.getUnavailableGifticons(any(), any())).thenReturn(
          new SliceImpl<>(Collections.emptyList()));

      // when
      final ResultActions resultActions = mockMvc.perform(
          MockMvcRequestBuilders.get(BASE_URL + API_PATH)
      );

      // then
      resultActions
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.body.size").value("0"));
    }

    @Test
    void rowCount가_1보다_작을_경우_valiation에_의해_404반환() throws Exception {
      // given

      // when
      final ResultActions resultActions = mockMvc.perform(
          MockMvcRequestBuilders
              .get(BASE_URL + API_PATH)
              .param("rowCount", "-1")
      );

      // then
      resultActions.andExpect(status().isBadRequest());
    }
  }

  @Test
  void dd() {
    String repeat = "updatedContent".repeat(500);
    System.out.println(repeat.length());
  }

}
