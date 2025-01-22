package com.example.hiber_api;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.hiber_api.model.Department;
import com.example.hiber_api.model.security.Permission;
import com.example.hiber_api.model.security.Role;
import com.example.hiber_api.model.security.RoleEnum;
import com.example.hiber_api.model.security.User;
import com.example.hiber_api.repository.DepartmentRepository;
import com.example.hiber_api.repository.EmployeeRepository;
import com.example.hiber_api.repository.UserRepository;

@SpringBootApplication
public class HiberApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HiberApiApplication.class, args);
	}

    @Bean
    CommandLineRunner init(EmployeeRepository employeeRepository, UserRepository userRepository, DepartmentRepository departmentRepository) {
        return _ -> {
            
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
                    
            employeeRepository.createEmployees();
                    
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
