package ru.itis.og.util;

import java.time.Instant;

public class DateParseUtil {
    public static Instant toInstantFromString(String birthday) {
        String birth = birthday + "T00:00:00.00Z";
        return Instant.parse(birth);
    }
}
