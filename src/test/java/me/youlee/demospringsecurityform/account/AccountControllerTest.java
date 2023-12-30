package me.youlee.demospringsecurityform.account;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AccountService accountService;

//    @Test
//    public void index_anonymous() throws Exception {
//        mockMvc.perform(get("/").with(anonymous()))
//                .andDo(print())
//                .andExpect(status().isOk());
//    }
//

    @Test
    @WithAnonymousUser
    public void index_anonymous() throws Exception {
        mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk());
    }

//    @Test
//    public void index_user() throws Exception {
//        mockMvc.perform(get("/").with(user("wookjin").roles("USER")))
//            .andDo(print())
//            .andExpect(status().isOk());
//    }
//

    @Test
    @WithUser
    public void index_user() throws Exception {
        mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().isOk());
    }

//    @Test
//    public void admin_user() throws Exception {
//        mockMvc.perform(get("/admin").with(user("wookjin").roles("USER")))
//            .andDo(print())
//            .andExpect(status().isForbidden());
//    }

    @Test
    @WithUser
    public void admin_user() throws Exception {
        mockMvc.perform(get("/admin"))
            .andDo(print())
            .andExpect(status().isForbidden());
    }

//    @Test
//    public void admin_admin() throws Exception {
//        mockMvc.perform(get("/admin").with(user("admin").roles("ADMIN")))
//            .andDo(print())
//            .andExpect(status().isOk());
//    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void admin_admin() throws Exception {
        mockMvc.perform(get("/admin"))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void login_success() throws Exception {
        String username = "wookjin";
        String pass = "123";
        Account account = createUser(username, pass);
        mockMvc.perform(formLogin().user(account.getUsername()).password(pass))
            .andExpect(authenticated());
    }

    @Test
    @Transactional
    public void login_success2() throws Exception {
        String username = "wookjin";
        String pass = "123";
        Account account = createUser(username, pass);
        mockMvc.perform(formLogin().user(account.getUsername()).password(pass))
            .andExpect(authenticated());
    }

    @Test
    @Transactional
    public void login_fail() throws Exception {
        String username = "wookjin";
        String pass = "123";
        Account account = createUser(username, pass);
        mockMvc.perform(formLogin().user(account.getUsername()).password("12345"))
            .andExpect(unauthenticated());
    }

    private Account createUser(String username, String pass) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(pass);
        account.setRole("USER");
        return accountService.createNew(account);
    }
}