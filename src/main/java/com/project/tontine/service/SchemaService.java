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

@Transactional(propagation = Propagation.REQUIRES_NEW)
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

    public SchemaService(JdbcTemplate jdbcTemplate, MemberService memberService, MemberRepository memberRepository, RoleTypeService roleTypeService)
    {
        this.jdbcTemplate = jdbcTemplate;
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.roleTypeService = roleTypeService;
    }


    public void createSchema(String rawSchemaName)
    {
        String schemaName = sanitizeSchemaName(rawSchemaName);

        jdbcTemplate.execute(
//               "DROP SCHEMA IF EXISTS "+ schemaName + " CASCADE; " +
                "CREATE SCHEMA IF NOT EXISTS "  + schemaName);
    }

    public void changeSchema(String rawSchemaName)
    {
        String schemaName = sanitizeSchemaName(rawSchemaName);
        jdbcTemplate.execute("SET search_path TO  " + schemaName);
    }

    private String sanitizeSchemaName(String input)
    {
        if (!input.matches("^[a-zA-Z0-9_]+$")) {
            throw new IllegalArgumentException("Nom de schéma invalide.");
        }
        return input;
    }

    public DataSource generateDataSourceBySchema(String schema)
    {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5433/tontine");
        config.setUsername("postgres");
        config.setPassword("aaaaaaa");

        config.setConnectionInitSql("SET search_path TO " + schema);

        return new HikariDataSource(config);
    }

    public void generateTables(DataSource dataSource, String directory) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.project.tontine." + directory);
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "create");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        emf.setJpaProperties(props);

        emf.afterPropertiesSet(); // <<< C'est cette ligne qui déclenche la génération des tables

        EntityManager em = emf.getObject().createEntityManager();

        //-----------------------------------------------------------------------------------------------1
        if (directory.equals("model")) {
            List<String> administratorEmails = List.of(this.administratorEmails.split(","));
            Optional<Member> optionalMember = memberRepository.findByEmail(administratorEmails.get(0));

            if (optionalMember.isEmpty()) {
                Member member = new Member();
                Account account = new Account();

                account.setUsername("Admin1");
                account.setEnable(true);

                member.setName("Tchouanguem");
                member.setFirstname("Yann");
                member.setNumber(690881616);
                member.setEmail(administratorEmails.get(0));
                member.setPassword("One");

                memberService.create(member, 1);
                member.setEnable(true);
                memberRepository.save(member);
                memberService.createAccount(account, member.getId());
            }

            optionalMember = memberRepository.findByEmail(administratorEmails.get(1));

            if (optionalMember.isEmpty()) {
                Member member = new Member();
                Account account = new Account();

                account.setUsername("Admin2");
                account.setEnable(true);

                member.setName("Kemdem");
                member.setFirstname("Hervé");
                member.setNumber(620357560);
                member.setEmail(administratorEmails.get(1));
                member.setPassword("Two");

                memberService.create(member, 1);
                member.setEnable(true);
                memberRepository.save(member);
                memberService.createAccount(account, member.getId());
            }
            //-----------------------------------------------------------------------------------------------1


            //-----------------------------------------------------------------------------------------------2
            if (roleTypeService.readAll().isEmpty()) {
                RoleType roleType = new RoleType();

                for (RoleTypeLabel roleTypeLabel : RoleTypeLabel.values()) {
                    roleType.setId(0);
                    roleType.setLabel(roleTypeLabel);
                    roleType.setDescription("");
                    roleTypeService.create(roleType);
                }
            }
            //-----------------------------------------------------------------------------------------------1
        }
    }}
