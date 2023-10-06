package yapp.buddycon.app.gifticon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import yapp.buddycon.app.common.MockAuthenticationArgumentResolver;
import yapp.buddycon.app.gifticon.adapter.client.CreateGifticonController;
import yapp.buddycon.app.gifticon.adapter.client.request.GifticonCreationDto;
import yapp.buddycon.app.gifticon.application.port.in.CreateGifticonUsecase;
import yapp.buddycon.app.gifticon.domain.GifticonStore;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO : MockMvc -> RestAssured (MockMultipartFile 문제 해결)
@ExtendWith(MockitoExtension.class)
public class CreateGifticonControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    private final String BASE_URL = "/api/v1/gifticons";

    @Mock
    private CreateGifticonUsecase usecase;
    @InjectMocks
    private CreateGifticonController controller;
    private final MockAuthenticationArgumentResolver argumentResolver = new MockAuthenticationArgumentResolver();

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ExceptionHandlerExceptionResolver())
                .setCustomArgumentResolvers(argumentResolver)
                .build();
    }

    @Test
    void 정상적으로_기프티콘을_등록할때_200이_반환된다() throws Exception {

        // given
        final var testImage = new MockMultipartFile(
                "image",
                "image.png",
                MediaType.IMAGE_PNG_VALUE,
                "testpng".getBytes()
        );
        final var dto = new GifticonCreationDto("name", LocalDate.MAX, GifticonStore.GS25, "memo");
        final var mockDto = new MockMultipartFile("dto", "", "application/json", mapper.registerModule(new JavaTimeModule()).writeValueAsString(dto).getBytes(StandardCharsets.UTF_8));

        doNothing().when(usecase).createGifticon(any(GifticonCreationDto.class), eq(testImage), anyLong());

        // when
        ResultActions result = mockMvc.perform(
                multipart(BASE_URL)
                        .file(mockDto)
                        .file(testImage)
                        .contentType("multipart/form-data")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        );

        // then
        result.andExpect(status().isOk());
    }

    @Test
    void 입력값_expireDate가_날짜형식이_아닐경우_400_에러를_반환한다() throws Exception {
        // given
        final var testImage = new MockMultipartFile(
                "image",
                "image.png",
                MediaType.IMAGE_PNG_VALUE,
                "testpng".getBytes()
        );

        /**
         * TODO
         * Dto의 expireDate 필드가 "yyyy-MM" 포맷인지 확인하는 테스트
         * 근데 Dto에서 @DateTimeFormat(pattern = "yyyy-MM-dd")를 사용하고있기때문에 날짜 형식이 올바르지 않은 객체를 생성조차 없으므로 현재 테스트를 작성할 수 없음
         * 따라서 현재 임시방편으로 직접 String으로 올바르지 않은 날짜 형식을 가진 dto를 작성함
         * 추후 리팩토링해야함
         */
        final var dto = """
                {\\"name\\":\\"name\\", \\"expireDate\\":\\"20220401\\", \\"store\\":\\"GS25\\", \\"memo\\":\\"memo\\"}
                """;
        final var mockDto = new MockMultipartFile("dto", "", "application/json", mapper.registerModule(new JavaTimeModule()).writeValueAsString(dto).getBytes(StandardCharsets.UTF_8));

        // when
        ResultActions result = mockMvc.perform(
                multipart(BASE_URL)
                        .file(mockDto)
                        .file(testImage)
                        .contentType("multipart/form-data")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        );

        // then
        result.andExpect(status().isBadRequest());
    }

    @Test
    void 입력값_store에_해당하지않는_enum_value가_들어온다면_400_에러를_반환한다() throws Exception {
        // given
        final var testImage = new MockMultipartFile(
                "image",
                "image.png",
                MediaType.IMAGE_PNG_VALUE,
                "testpng".getBytes()
        );

        /**
         * TODO
         * 마찬가지로 store가 enum에 해당하지 않을 경우 객체를 생성조차 없으므로 현재 테스트를 작성할 수 없음
         * 따라서 현재 임시방편으로 직접 String으로 올바르지 않은 날짜 형식을 가진 dto를 작성함
         * 추후 리팩토링해야함
         */
        final var dto = """
                {\\"name\\":\\"name\\", \\"expireDate\\":\\"2022-04-01\\", \\"store\\":\\"NOTHING\\", \\"memo\\":\\"memo\\"}
                """;
        final var mockDto = new MockMultipartFile("dto", "", "application/json", mapper.registerModule(new JavaTimeModule()).writeValueAsString(dto).getBytes(StandardCharsets.UTF_8));

        // when
        ResultActions result = mockMvc.perform(
                multipart(BASE_URL)
                        .file(mockDto)
                        .file(testImage)
                        .contentType("multipart/form-data")
                        .accept(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
        );

        // then
        result.andExpect(status().isBadRequest());
    }
}








//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class CreateGifticonControllerTest {
//
//    @MockBean
//    CreateGifticonService service;
//
//    @LocalServerPort
//    int port;
//
//    @BeforeEach
//    void setUp() {
//        RestAssured.port = port;
//    }
//
//    @Test
//    @Transactional // for rollback
//    void 정상적으로_기프티콘을_등록할때_200이_반환된다() throws IOException {
//
//        // given
//        final var user = new AuthUser(1L);
//        final var testDto = new GifticonCreationDto("name", LocalDate.of(2023, 9, 1), GifticonStore.GS25, null);
//        final var testImage = new MockMultipartFile("test-gifticon-img.png", new FileInputStream("src/test/resources/img/test-gifticon-img.png"));
//
//        doNothing().when(service).createGifticon(testDto, testImage, user.id());
//
////        testImage.transferTo(new File(testImage.getOriginalFilename()));
//
//        // when
//        final var response = RestAssured
//                .given()
////                .log().all().contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
////                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
//                .multiPart("image", testImage)
//                .multiPart("dto", testDto, MediaType.APPLICATION_JSON_VALUE)
////                .multiPart("image", new File("src/test/resources/img/test-gifticon-img.png"))
//                .when().header("Authorization", "Bearer tokenExample")
//                .post("/api/v1/gifticons")
//                .then().log().all()
//                .extract();
//
//        // then
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
//    }
//
//    @Test
//    void 입력값_name에_null값이_들어온다면_400_에러를_반환한다() {
//        final var testDto = new GifticonCreationDto(null, LocalDate.of(2023, 9, 1), GifticonStore.GS25, null);
//        final var testImage = new MockMultipartFile("fileName", "fileName.png", "png", "testFile".getBytes(StandardCharsets.UTF_8));
//
//        // when
//        final var response = RestAssured.given().log().all()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .multiPart("image", testImage)
//                .multiPart("dto", testDto, "application/json")
//                .when().post("/api/v1/gifticons")
//                .then().log().all()
//                .extract();
//
//        // then
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//    }
//
//    @Test
//    void 입력값_expireDate에_null값이_들어온다면_400_에러를_반환한다() {
//        final var testDto = new GifticonCreationDto("name", null, GifticonStore.GS25, null);
//        final var testImage = new MockMultipartFile("fileName", "fileName.png", "png", "testFile".getBytes(StandardCharsets.UTF_8));
//
//        // when
//        final var response = RestAssured.given().log().all()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .multiPart("image", testImage)
//                .multiPart("dto", testDto, "application/json")
//                .when().post("/api/v1/gifticons")
//                .then().log().all()
//                .extract();
//
//        // then
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//    }
//
//    @Test
//    void 입력값_store에_null값이_들어온다면_400_에러를_반환한다() {
//        final var testDto = new GifticonCreationDto("name", LocalDate.of(2023, 9, 1), null, null);
//        final var testImage = new MockMultipartFile("fileName", "fileName.png", "png", "testFile".getBytes(StandardCharsets.UTF_8));
//
//        // when
//        final var response = RestAssured.given().log().all()
//                .contentType(MediaType.APPLICATION_JSON_VALUE)
//                .multiPart("image", testImage)
//                .multiPart("dto", testDto, "application/json")
//                .when().post("/api/v1/gifticons")
//                .then().log().all()
//                .extract();
//
//        // then
//        assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
//    }
//}