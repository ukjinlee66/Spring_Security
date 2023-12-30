package me.youlee.demospringsecurityform.account;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AccountController {

    private final AccountService accountService;
    @GetMapping("/account/{role}/{username}/{password}")
    public Account createAccount(Account account) {
        return accountService.createNew(account);
    }
}
