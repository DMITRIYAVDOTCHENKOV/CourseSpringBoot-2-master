package org.example.seminar5task1.controllers;


import lombok.AllArgsConstructor;
import org.example.seminar5task1.dto.TransferRequest;
import org.example.seminar5task1.model.Account;
import org.example.seminar5task1.services.TransferService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class AccountController {

    private final TransferService transferService;


    @PostMapping("/transfer")
    public void transferMoney(
            @RequestBody TransferRequest request
    ) {
        transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount());
    }

    @GetMapping("/accounts")
    public Iterable<Account> getAllAccounts(
            @RequestParam(required = false) String name
    ) {
        if (name == null) {
            return transferService.getAllAccounts();
        } else {
            return transferService.findAccountsByName(name);
        }
    }

}