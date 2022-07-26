package net.collabstack.app.letter.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DateUtilTest {

    @Test
    void toStandingTypeDateSecondTest() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusSeconds(20);

        final String standingTypeString = DateUtil.toStandingTypeDate(new Date(), now);

        assertThat(standingTypeString).isEqualTo("20 seconds ago");
    }

    @Test
    void toStandingTypeDateMinuteTest() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusMinutes(12);

        final String standingTypeString = DateUtil.toStandingTypeDate(new Date(), now);

        assertThat(standingTypeString).isEqualTo("12 minutes ago");
    }

    @Test
    void toStandingTypeDateHoursTest() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusHours(23);

        final String standingTypeString = DateUtil.toStandingTypeDate(new Date(), now);

        assertThat(standingTypeString).isEqualTo("23 hours ago");
    }

    @Test
    void toStandingTypeDateDaysTest() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusDays(29);

        final String standingTypeString = DateUtil.toStandingTypeDate(new Date(), now);

        assertThat(standingTypeString).isEqualTo("29 days ago");
    }

    @Test
    void toStandingTypeDateMonthsTest() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusMonths(11);

        final String standingTypeString = DateUtil.toStandingTypeDate(new Date(), now);

        assertThat(standingTypeString).isEqualTo("11 months ago");
    }

    @Test
    void toStandingTypeDateYearsTest() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusYears(3);

        final String standingTypeString = DateUtil.toStandingTypeDate(new Date(), now);

        assertThat(standingTypeString).isEqualTo("3 years ago");
    }

    @Test
    @DisplayName("62초 뒤일경우")
    void toStandingTypeDateTest_after62sec() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusSeconds(62);

        final String standingTypeString = DateUtil.toStandingTypeDate(new Date(), now);

        assertThat(standingTypeString).isEqualTo("1 minutes ago");
    }

    @Test
    @DisplayName("32일 뒤일경우")
    void toStandingTypeDateTest_after32days() {
        LocalDateTime now = LocalDateTime.now();
        now = now.minusDays(32);

        final String standingTypeString = DateUtil.toStandingTypeDate(new Date(), now);

        assertThat(standingTypeString).isEqualTo("1 months ago");
    }
}