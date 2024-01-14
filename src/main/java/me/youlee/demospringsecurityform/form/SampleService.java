package me.youlee.demospringsecurityform.form;

import java.util.Collection;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public void dashboard() {
        /*
         접속한 정보를 SecurityContextHolder에서 가져올 수 있다.
         SecurityContextHolder는 ThreadLocal을 사용한다.
         ThreadLocal은 한 쓰레드 내에서 공유하는 저장소이다.
         즉, 같은 쓰레드 내에서는 SecurityContextHolder를 통해 같은 인증 정보를 공유할 수 있다.
         다른 쓰레드에서는 다른 인증 정보를 가질 수 있다.
         SeucirtyContextHolder는 인증이 된 정보만 포함한다.
         즉, 인증이 되지 않은 정보는 포함하지 않는다.
        */
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        // 접속한 사용자의 정보 (User Detail 타입의 객체)
//        Object principal = authentication.getPrincipal();
//        // 접속한 사용자의 권한 정보(User, Admin 등 여러 권한에 대한 정보를 포함할 수 있다)
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        // 접속한 사용자의 인증에 대한 정보
//        Object credentials = authentication.getCredentials();
//        // 인증이 되었는지 여부
//        boolean authenticated = authentication.isAuthenticated();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("==================");
        System.out.println(userDetails.getUsername());
    }
}
