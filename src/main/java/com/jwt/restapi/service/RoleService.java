package com.jwt.restapi.service;



import com.jwt.restapi.entity.UserPrivilageAssignment;
import com.jwt.restapi.repository.PrivilegeRepository;
import com.jwt.restapi.repository.RoleRepository;
import com.jwt.restapi.repository.UserAssignmentRepository;
import com.jwt.restapi.repository.UserRepository;
import com.jwt.restapi.entity.Privilege;
import com.jwt.restapi.entity.Role;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;
    private final UserAssignmentRepository assignmentRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository, PrivilegeRepository privilegeRepository, UserAssignmentRepository assignmentRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.privilegeRepository = privilegeRepository;
        this.assignmentRepository = assignmentRepository;
    }

    //Get All Roles
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    //Get Role By Id
    public Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    //Delete Role
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    //Update Role
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void assignUserRole(Long userid, Long roleid) {
        List<Privilege> privileges = privilegeRepository.findByRoleid(roleid);

        List<UserPrivilageAssignment> existingAssignment = assignmentRepository.findByUserid(userid);

        assignmentRepository.deleteAll(existingAssignment);

        List<UserPrivilageAssignment> assignments  = privileges.stream()
                .map(privilege -> new UserPrivilageAssignment(userid, privilege.getId()))
                .toList();

        assignmentRepository.saveAll(assignments);
    }

    @Transactional
    public void unAssignUserRole(Long userid, Long roleid) {
        List<Privilege> privileges = privilegeRepository.findByRoleid(roleid);
        privileges.forEach(privilege -> assignmentRepository.deleteByUseridAndPrivilegeId(userid, privilege.getId()));
    }

    public List<Privilege> getPrivilegesInRole(Long roleid) {
        return privilegeRepository.findByRoleid(roleid);
    }
}

