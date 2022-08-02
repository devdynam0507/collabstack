package net.collabstack.app.letter.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class DateUtil {

    public static String toStandingTypeDate(final Date now, final LocalDateTime creationDate) {
        final long nowTimeMills = now.getTime();
        final long creationDateTimeMills = Timestamp.valueOf(creationDate).getTime();
        long gap = (nowTimeMills - creationDateTimeMills) / 1000;

        if (gap < 60) {
            return gap + " seconds ago";
        }
        gap /= 60;
        if (gap < 60) {
            return gap + " minutes ago";
        }
        gap /= 60;
        if (gap < 24) {
            return gap + " hours ago";
        }
        gap /= 24;
        if (gap < 31) {
            return gap + " days ago";
        }
        gap /= 30;
        if (gap < 12) {
            return gap + " months ago";
        }
        gap /= 12;
        return gap + " years ago";
    }
}
