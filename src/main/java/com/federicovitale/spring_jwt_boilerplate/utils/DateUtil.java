package com.federicovitale.spring_jwt_boilerplate.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Slf4j
public class DateUtil {
    public static Date now(Long extra) {
        if ( extra == null ) {
            extra = 0L;
        }

        return new Date(System.currentTimeMillis() + extra);
    }

    public static int differenceInHours(Date start, Date end) {
        try {
            long diff = end.getTime() - start.getTime();
            long hourDiff = diff / (1000 * 60 * 60);

            return (int) hourDiff;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return 0;
        }
    }
}
