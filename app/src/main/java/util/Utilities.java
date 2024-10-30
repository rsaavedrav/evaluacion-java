package util;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.UUID;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.*;

public class Utilities {
    private static final String PATRON_FECHA = "dd-MM-yyyy";
    private static final long MSB = 0x8000000000000000L;
    @Value("${pattern.email.date}")
    private static String PATTERN_DATE;

    @Value("${pattern.email}")
    private static String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static String PASS_REGEX = "[a-zA-Z0-9.]{8}"; // "[a-zA-Z0-9@.]+"; //^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]{8}$

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean patternMatches(String emailAddress) {
        return Pattern.compile(EMAIL_REGEX)
                .matcher(emailAddress)
                .matches();
    }

    public static boolean validaPasswordMatches(String pass) {
        return Pattern.compile(PASS_REGEX)
                .matcher(pass)
                .matches();
    }

    public static String getUUID() {
        String uuid;
        SecureRandom ng = new SecureRandom();
        uuid = Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
        return uuid;
    }

    public static UUID UUID() {
        return UUID.randomUUID();
    }

    public static Timestamp createUserDate() {
        Instant now = Instant.now();
        Timestamp currentTimestamp = Timestamp.from(now);
        return currentTimestamp;
    }

    public static String getNowFormatDate() {
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE);
        String fechaFormateada = fechaActual.format(formatter);

        return "{\"fecha\": \"" + fechaFormateada + "\"}";
    }
}
