package com.example.kito;

import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.kito.model.Department;
import com.example.kito.model.security.Permission;
import com.example.kito.model.security.Role;
import com.example.kito.model.security.RoleEnum;
import com.example.kito.model.security.User;
import com.example.kito.repository.DepartmentRepository;
import com.example.kito.repository.UserRepository;

@SpringBootApplication
public class KitoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KitoApplication.class, args);
	}

    @Bean
    CommandLineRunner printJDBCUrl(DataSource dataSource) {
        return args -> {
            System.out.println("🔗 JDBC URL: " + dataSource.getConnection().getMetaData().getURL());
        };
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository, DepartmentRepository departmentRepository) {
        return wo -> {
            
            Department developerDepartment = Department.builder()
            .name("Developer")
            .build();
            
            Department designDepartment = Department.builder()
            .name("Design")
            .build();
            
            Department marketingDepartment = Department.builder()
            .name("Marketing")
            .build();

            Department salesDepartment = Department.builder()
                .name("Sales")
                .build();
                
                Department pmDepartment = Department.builder()
                .name("Product Management")
                .build();
                
                departmentRepository.saveAll(
                    List.of(developerDepartment, 
                    designDepartment, 
                    marketingDepartment, 
                    salesDepartment,
                    pmDepartment));
                    
                    /* Create PERMISSIONS */
                    Permission createPermission = Permission.builder()
                    .name("CREATE")
                    .build();
                    
            Permission readPermission = Permission.builder()
                    .name("READ")
                    .build();

            Permission updatePermission = Permission.builder()
                    .name("UPDATE")
                    .build();

            Permission deletePermission = Permission.builder()
                    .name("DELETE")
                    .build();

            Permission refactorPermission = Permission.builder()
                    .name("REFACTOR")
                    .build();

            /* Create ROLES */
            Role roleAdmin = Role.builder()
                    .roleEnum(RoleEnum.ADMIN)
                    .permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission))
                    .build();

            Role roleUser = Role.builder()
                    .roleEnum(RoleEnum.USER)
                    .permissions(Set.of(createPermission, readPermission))
                    .build();

            Role roleInvited = Role.builder()
                    .roleEnum(RoleEnum.INVITED)
                    .permissions(Set.of(readPermission))
                    .build();

            Role roleDeveloper = Role.builder()
                    .roleEnum(RoleEnum.DEVELOPER)
                    .permissions(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
                    .build();

            /* CREATE USERS */
            User userJohan = User.builder()
                    .username("johan")
                    .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6") // 1234
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleAdmin))
                    .build();

            User userCarlos = User.builder()
                    .username("carlos")
                    .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6") // 1234
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleUser))
                    .build();

            User userLuis = User.builder()
                    .username("luis")
                    .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6") // 1234
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleInvited))
                    .build();

            User userLily = User.builder()
                    .username("lily")
                    .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6") // 1234
                    .isEnabled(true)
                    .accountNoExpired(true)
                    .accountNoLocked(true)
                    .credentialNoExpired(true)
                    .roles(Set.of(roleDeveloper))
                    .build();

            userRepository.saveAll(List.of(userJohan, userCarlos, userLuis, userLily));
        };
    }

}
