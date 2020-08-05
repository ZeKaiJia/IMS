package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * The interface Role mapper.
 */
@Repository
public interface RoleMapper {
    void saveRoleInfo(Role role);
    void createUserRole(@Param("usrType") String usrType, @Param("usrName") String usrName);
    void deleteRole(@Param("id") Integer id);
    void deleteUserRole(@Param("usrName") String usrName);
    void disableRole(Role role);
    void recoverRole(Role role);
    void updateRoleInfo(Role role);
    void changeUserRole(@Param("usrType") String usrType, @Param("usrName") String usrName);
    Role selectById(@Param("id") Integer id);
    List<Role> selectAll();
    List<Role> selectAnyParam(Role role);
    Set<String> findRoleByUserName(@Param("usrName") String usrName);
}
