package com.jwt.restapi.repository;



import com.jwt.restapi.entity.UserPrivilageAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAssignmentRepository extends JpaRepository<UserPrivilageAssignment, Long> {
    public void deleteByUserid(Long userid);

    public List<UserPrivilageAssignment> findByUserid(Long userid);

    public void deleteByUseridAndPrivilegeId(Long userid, Long privilegeid);
}
