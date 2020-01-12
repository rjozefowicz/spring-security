package dev.jozefowicz.springsecurity.jwtadvanced.configuration;

import dev.jozefowicz.springsecurity.jwtadvanced.service.RevokedTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SchedulingConfiguration {

    @Autowired
    private RevokedTokenRepository revokedTokenRepository;

    @Scheduled(fixedRate = 60 * 1000)
    public void inMemoryDataCleanup() {
        revokedTokenRepository.removeOutdated();
    }

}
