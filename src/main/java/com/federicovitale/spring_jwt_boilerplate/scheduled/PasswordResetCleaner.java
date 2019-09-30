package com.federicovitale.spring_jwt_boilerplate.scheduled;

import com.federicovitale.spring_jwt_boilerplate.models.User.User;
import com.federicovitale.spring_jwt_boilerplate.models.services.UserPreferenceService;
import com.federicovitale.spring_jwt_boilerplate.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
@Slf4j
public class PasswordResetCleaner {
    @Autowired
    private UserPreferenceService.UserServiceImpl userService;

    @Scheduled(fixedRate = 1000 * 5)
    public void cleanUp() {
        List<User> users = userService
                .findAll()
                .stream()
                .filter(user -> {
                    if ( user.getPasswordRecoveryStart() == null ) return false;

                    Date now = DateUtil.now(0L);
                    Date start = user.getPasswordRecoveryStart();

                    int diff = DateUtil.differenceInHours(start, now);

                    return diff > 1;
                })
                .collect(Collectors.toList());

        users.forEach(user -> {
            user.setStartedPasswordRecovery(false);
            user.setResetToken(null);
            user.setPasswordRecoveryStart(null);
            userService.save(user);
        });

        log.debug(String.format("::> Cleaned %d user's statuses.", users.size()));
    }

    private User clean(User user) {
        user.setPasswordRecoveryStart(null);
        user.setStartedPasswordRecovery(false);
        user.setResetToken(null);
        user = userService.save(user);

        return user;
    }
}
