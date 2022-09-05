package ru.netology.web.utils;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;
import ru.netology.web.domain.RegionCity;
import ru.netology.web.domain.RegistrationInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@UtilityClass
public class DataGenerator {

    @UtilityClass
    public static class Registration {
        public static RegistrationInfo generateInfo(String locale) {
            Faker faker = new Faker(new Locale(locale));
            RegionCity regionCity = new RegionCity();
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return new RegistrationInfo(
                    regionCity.getRegionCity()[faker.number().numberBetween(0, regionCity.getRegionCity().length)],
                    faker.name().lastName() + " " + faker.name().firstName(),
                    faker.numerify("+7##########"),
                    today.plusDays(faker.number().numberBetween(3, 9)).format(formatter),
                    today.plusDays(faker.number().numberBetween(3, 9)).format(formatter)
            );
        }
    }
}
