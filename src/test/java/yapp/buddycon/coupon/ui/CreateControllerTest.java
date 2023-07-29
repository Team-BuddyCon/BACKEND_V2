package yapp.buddycon.coupon.ui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import yapp.buddycon.TestLocalDateAdapter;
//import yapp.buddycon.coupon.application.CouponCreator;
//import yapp.buddycon.coupon.domain.Store;
//import yapp.buddycon.coupon.dto.CouponSaveRequest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CreateControllerTest {

//  @InjectMocks
//  private CreateController target;
//
//  @Mock
//  private CouponCreator couponCreator;
//  private MockMvc mockMvc;
//  private  Gson gson;
//
//  @BeforeEach
//  public void init() {
//    gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new TestLocalDateAdapter().nullSafe()).create();
//    mockMvc = MockMvcBuilders.standaloneSetup(target)
//      .build();
//  }
//
//  @Test
//  void 쿠폰_생성() throws Exception {
//    final String url = "/api/v1/coupon";
//
//    var result = mockMvc.perform(post(url)
//      .content(gson.toJson(new CouponSaveRequest("name", "barcode", LocalDate.of(2024, 1, 1), "imageurl", Store.CU, "memo")))
//      .contentType(MediaType.APPLICATION_JSON)
//    );
//
//    result.andExpect(status().isOk());
//  }

}