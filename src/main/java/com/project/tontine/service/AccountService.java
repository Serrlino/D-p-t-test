package com.project.tontine.service;

import com.project.tontine.model.Account;
import com.project.tontine.model.Member;
import com.project.tontine.repository.AccountRepository;
import com.project.tontine.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AccountService
{
    private AccountRepository accountRepository;
    private MemberRepository memberRepository;

    public void create(Account account)
    {
        accountRepository.save(account);
    }

    public Account readById(Integer id)
    {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Membre n'existe pas présent"));
    }

    public Account readByUsername(String accountUsername)
    {
        return accountRepository.findByUsername(accountUsername).orElseThrow(() -> new RuntimeException("Membre n'existe pas présent"));
    }

    public ArrayList<Account> readByIdMember(Integer idMember)
    {
        Member member = memberRepository.findById(idMember).get();

        return accountRepository.findByMember(member);
    }

    public ArrayList<Account> readAll()
    {
        return (ArrayList<Account>) accountRepository.findAll();
    }

    public ArrayList<Account> readAllWithoutSuperAdmin()
    {
        return accountRepository.findByIdNotIn(new ArrayList<>(){{
            add(1);
            add(2);
        }});
    }

    public void update(Account account)
    {
        readById(account.getId());

        String accountUsername = account.getUsername();

        if(accountUsername != null)
            account.setUsername(accountUsername);

        accountRepository.save(account);
    }

    public void deleteById(Integer id)
    {
        accountRepository.deleteById(id);
    }

    public void deleteAll()
    {
        accountRepository.deleteAll();
    }
}
