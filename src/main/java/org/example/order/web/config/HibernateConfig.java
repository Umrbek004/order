package org.example.order.web.config;

import jakarta.annotation.Nonnull;
import org.example.order.web.util.UserSession;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class HibernateConfig implements AuditorAware<Long> {

    private final UserSession userSession;

    public HibernateConfig(UserSession userSession) {
        this.userSession = userSession;
    }

    @Override
    @Nonnull
    public Optional<Long> getCurrentAuditor() {
        return Optional.ofNullable(userSession.getUser() != null ? userSession.getUser().getId() : null);
    }
}
