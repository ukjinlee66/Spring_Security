package me.youlee.demospringsecurityform.account;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithMockUser;

@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "wookjin", roles = "USER")
public @interface WithUser {

}
