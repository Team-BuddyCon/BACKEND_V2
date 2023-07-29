package yapp.buddycon;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestLocalDateAdapter extends TypeAdapter<LocalDate> {
  DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public void write(JsonWriter out, LocalDate value) throws IOException {
    out.value(value.format(format));
  }

  @Override
  public LocalDate read(JsonReader in) throws IOException {
    return LocalDate.parse(in.nextString(), format);
  }
}