import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdadter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    // Formato de fecha: AAAA-MM-DD (ISO 8601)
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        // Convierte LocalDate a String en formato ISO
        return new JsonPrimitive(FORMATTER.format(src));
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // Convierte String en formato ISO a LocalDate
        return LocalDate.parse(json.getAsString(), FORMATTER);
    }
}