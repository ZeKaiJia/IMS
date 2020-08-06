package cn.kevin.ims.mapper;

import cn.kevin.ims.entity.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PermissionMapper {
    void savePermissionInfo(Permission permission);
    void deletePermission(@Param("name") String name);
    void disablePermission(Permission permission);
    void recoverPermission(Permission permission);
    void updatePermissionInfo(Permission permission);
    Permission selectByName(@Param("name") String name);
    List<Permission> selectAll();
    List<Permission> selectAnyParam(Permission permission);
    Set<String> findPermissionByRole(@Param("role") String role);
}
