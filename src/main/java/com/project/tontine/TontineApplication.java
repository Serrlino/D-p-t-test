package com.project.tontine;

import com.project.tontine.enumeration.PermissionLabel;
import com.project.tontine.enumeration.RoleTypeLabel;
import com.project.tontine.model.Account;
import com.project.tontine.model.Member;
// import com.project.tontine.model.Permission;
import com.project.tontine.model.RoleType;
import com.project.tontine.repository.MemberRepository;
import com.project.tontine.service.MemberService;
// import com.project.tontine.service.PermissionService;
import com.project.tontine.service.RoleTypeService;
import com.project.tontine.service.SchemaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class TontineApplication implements CommandLineRunner
{
	private static final Logger log = LoggerFactory.getLogger(TontineApplication.class);
	private final String administratorEmails;
	private final MemberRepository memberRepository;
	private final MemberService memberService;
	private final RoleTypeService roleTypeService;
	private final SchemaService schemaService;
	// private final PermissionService permissionService;

	public TontineApplication(@Value("${application.administrator.emails:''}") String administratorEmails, MemberRepository memberRepository, MemberService memberService, RoleTypeService roleTypeService, SchemaService schemaService//, PermissionService permissionService
	)
	{
        this.administratorEmails = administratorEmails;
		this.memberRepository = memberRepository;
        this.memberService = memberService;
		this.roleTypeService = roleTypeService;
        // // this.permissionService = permissionService;
        this.schemaService = schemaService;
    }

	@Override
	public void run(String... args) throws Exception
	{
		schemaService.createSchema("global_schema");

		//-----------------------------------------------------------------------------------------------1
		List<String> administratorEmails = List.of(this.administratorEmails.split(","));
		Optional<Member> optionalMember = memberRepository.findByEmail(administratorEmails.get(0));

		if(optionalMember.isEmpty())
		{
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

		if(optionalMember.isEmpty())
		{
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
		if(roleTypeService.readAll().isEmpty())
		{
			RoleType roleType = new RoleType();

			for(RoleTypeLabel roleTypeLabel : RoleTypeLabel.values())
			{
				roleType.setId(0);
				roleType.setLabel(roleTypeLabel);
				roleType.setDescription("");
				roleTypeService.create(roleType);
			}
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
		//-----------------------------------------------------------------------------------------------2

		// log.info("""
		
		
		// 111
		
		
		// """);
	}

    public static void main(String[] args) {
		SpringApplication.run(TontineApplication.class, args);
	}
}