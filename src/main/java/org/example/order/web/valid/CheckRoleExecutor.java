package org.example.order.web.valid;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.order.constants.RoleName;
import org.example.order.web.entity.User;
import org.example.order.web.exception.ForbiddenException;
import org.example.order.web.util.messages.error.ErrorMessageKey;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CheckRoleExecutor {
    @Before(value = "@annotation(checkRole)")
    public void checkRole(CheckRole checkRole) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RoleName[] value = checkRole.value();
        boolean exist = false;
        outerLoop:

        for (GrantedAuthority authority : principal.getAuthorities()) {
            for (RoleName roleName : value) {
                if (roleName.name().equals(authority.getAuthority())) {
                    exist = true;
                    break outerLoop;
                }
            }
        }
        if (!exist) throw new ForbiddenException(principal.getAuthorities().toString(), ErrorMessageKey.FORBIDDEN);
    }
}
