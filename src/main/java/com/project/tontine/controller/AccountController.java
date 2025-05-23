package com.project.tontine.controller;

import com.project.tontine.model.Group;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.tontine.model.Account;
import com.project.tontine.service.AccountService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;


@RestController
@RequestMapping(path = "account")
@AllArgsConstructor
public class AccountController
{
    private AccountService accountService;

    @PostMapping
    public void create(@RequestBody Account account)
    {
		accountService.create(account);
    }

    @GetMapping(path = "id/{id}")
    public Account read(@PathVariable Integer id)
    {
        return accountService.readById(id);
    }

    @GetMapping(path = "username/{accountUsername}")
    public Account read(@PathVariable String accountUsername)
    {
        return accountService.readByUsername(accountUsername);
    }

    @GetMapping(path = "member/{idMember}")
    public ArrayList<Account> readByMember(@PathVariable Integer idMember)
    {
        return accountService.readByIdMember(idMember);
    }

    @GetMapping
    public ArrayList<Account> read()
    {
        return accountService.readAll();
    }

    @PutMapping
    public void update(@RequestBody Account account)
    {
        accountService.update(account);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable Integer id)
    {
        accountService.deleteById(id);
    }

    @DeleteMapping
    public void delete()
    {
        accountService.deleteAll();
    }
}