package com.sametozkan.storeapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Utils {

    public static final String generateRandomId() {
        return UUID.randomUUID().toString();
    }

    public static final long getTimestamp() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public static final String convertTimestampToDate(long timestamp) {
        Date date = new Date(timestamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static final long convertDateStringToTimestamp(String dateString) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
            Date date = simpleDateFormat.parse(dateString);
            return date.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
