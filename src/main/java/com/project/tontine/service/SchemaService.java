package com.project.tontine.service;

import com.project.tontine.enumeration.RoleTypeLabel;
import com.project.tontine.model.Account;
import com.project.tontine.model.Member;
import com.project.tontine.model.Role;
import com.project.tontine.model.RoleType;
import com.project.tontine.repository.MemberRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

@Slf4j
@Service
public class SchemaService
{
    private final JdbcTemplate jdbcTemplate;
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final RoleTypeService roleTypeService;

    @Value("${application.administrator.emails:''}")
    private String administratorEmails;

    public SchemaService(JdbcTemplate jdbcTemplate, MemberService memberService, MemberRepository memberRepository, RoleTypeService roleTypeService)//, PermissionService permissionService
    {
        this.jdbcTemplate = jdbcTemplate;
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.roleTypeService = roleTypeService;
    }

//    private String sanitizeSchemaName(String input)
//    {
//        if (!input.matches("^[a-zA-Z0-9_]+$")) {
//            throw new IllegalArgumentException("Nom de schéma invalide.");
//        }
//        return input;
//    }

    public void createSchema(String rawSchemaName)
    {
//        String schemaName = sanitizeSchemaName(rawSchemaName);
        String schemaName = rawSchemaName;

        jdbcTemplate.execute(
//               "DROP SCHEMA IF EXISTS "+ schemaName + " CASCADE; " +
                "CREATE SCHEMA IF NOT EXISTS "  + schemaName);

    }

    public void generateTables() {

            if (roleTypeService.readAll().isEmpty()) 
            {
                RoleType roleType = new RoleType();

                for (RoleTypeLabel roleTypeLabel : RoleTypeLabel.values())
                {
                    roleType.setId(null);
                    roleType.setLabel(roleTypeLabel);
                    roleType.setDescription("");
                    roleTypeService.create(roleType);
                }
            }
            
            List<String> administratorEmails = List.of(this.administratorEmails.split(","));
                Member member = new Member();
                Account account = new Account();
            Optional<Member> optionalMember = null;
            
                account.setEnable(true);
                member.setEnable(true);
            optionalMember = memberRepository.findByEmail(administratorEmails.get(0));

            if (optionalMember.isEmpty())
            {
                account.setUsername("Admin1");

                member.setName("Tchouanguem");
                member.setFirstname("Yann");
                member.setNumber(690881616);
                member.setEmail(administratorEmails.get(0));
                member.setPassword("One");

                memberService.create(member, 1);
                memberService.createAccount(account, member.getId());
            }

            account.setId(null);
            member.setId(null);
            optionalMember = memberRepository.findByEmail(administratorEmails.get(1));

            if (optionalMember.isEmpty())
            {
                account.setUsername("Admin2");

                member.setName("Kemdem");
                member.setFirstname("Hervé");
                member.setNumber(620357560);
                member.setEmail(administratorEmails.get(1));
                member.setPassword("Two");

                memberService.create(member, 1);
                memberService.createAccount(account, member.getId());
            }

            
		// if(permissionService.readAllPermission().isEmpty())
		// {

		// 	Permission permission = new Permission();

		// 	for(PermissionLabel permissionLabel : PermissionLabel.values())
		// 	{
		// 		permission.setId(0);
		// 		permission.setPermissionLabel(permissionLabel);
		// 		permission.setPermissionDescription("");
		// 		permissionService.createPermission(permission);
		// 	}
		// }
        }
    }