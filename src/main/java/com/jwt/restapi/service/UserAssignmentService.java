package com.jwt.restapi.service;

import com.jwt.restapi.entity.Privilege;
import com.jwt.restapi.entity.User;
import com.jwt.restapi.entity.UserPrivilageAssignment;
import com.jwt.restapi.repository.UserAssignmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserAssignmentService {
    @Autowired
    public UserAssignmentRepository repository;

    public List<UserPrivilageAssignment> findAll() {
        return repository.findAll();
    }

    public UserPrivilageAssignment getPrivilege(Long id) {
        return repository.findById(id).orElse(null);
    }

    public UserPrivilageAssignment save(UserPrivilageAssignment userPrivilegeAssignment) {
        return repository.save(userPrivilegeAssignment);
    }

    public void delete(Long id) {
        repository.deleteById(Long.valueOf(id));
    }

    public UserPrivilageAssignment getById(Long id) {
        return repository.findById(Long.valueOf(id)).orElse(null);
    }

    public void update(UserPrivilageAssignment userPrivilegeAssignment) {
        repository.save(userPrivilegeAssignment);
    }

    @Transactional
    public List<Privilege> savePrivileges(List<Privilege> privileges, Long userid) {
        repository.deleteByUserid(userid);

        List<UserPrivilageAssignment> assignments = privileges.stream()
                .map(privilege -> new UserPrivilageAssignment(userid, privilege.getId()))
                .toList();

        return repository.saveAll(assignments).stream()
                .map(UserPrivilageAssignment::getPrivilege)
                .toList();
    }

    public void deletePrivileges(Long userid) {
        repository.deleteByUserid(userid);
    }

    public List<Privilege> getUserPrivileges(Long userid) {
        List<UserPrivilageAssignment> assignments = repository.findByUserid(userid);
        return assignments.stream()
                .map(UserPrivilageAssignment::getPrivilege)
                .toList();
    }

    public List<User> getUsersByPrivilege(Long privilegeid) {
        List<UserPrivilageAssignment> assignments = repository.findByUserid(privilegeid);
        return assignments.stream()
                .map(UserPrivilageAssignment::getUser)
                .toList();
    }
}
