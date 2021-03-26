package view.semesterview;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends StringConverter<LocalDate>{
    @Override
    public String toString(LocalDate date) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return date.format(dateFormat);
    }

    @Override
    public LocalDate fromString(String string) {
        return LocalDate.now();
    }
}
