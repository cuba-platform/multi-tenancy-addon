package com.haulmont.addon.sdbmt.security.role;

import com.haulmont.addon.sdbmt.entity.Tenant;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.*;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = TenantUserManagerRole.ROLE_NAME)
public class TenantUserManagerRole extends AnnotatedRoleDefinition {

    public static final String ROLE_NAME = "tenant-user-manager";

    @EntityAccess(entityClass = Group.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = com.haulmont.cuba.security.entity.Role.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Tenant.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = User.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE})
    @EntityAccess(entityClass = UserRole.class,
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityName = "sec$Target",
            operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = Group.class, modify = "*")
    @EntityAttributeAccess(entityClass = com.haulmont.cuba.security.entity.Role.class, modify = "*")
    @EntityAttributeAccess(entityClass = Tenant.class, modify = "*")
    @EntityAttributeAccess(entityClass = User.class, modify = "*")
    @EntityAttributeAccess(entityClass = UserRole.class, modify = "*")
    @EntityAttributeAccess(entityName = "sec$Target", modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @ScreenAccess(screenIds = {"tenant-management",
            "administration",
            "cubasdbmt$Tenant.browse",
            "cubasdbmt$Tenant.edit",
            "sec$Role.browse",
            "sec$Role.lookup",
            "sec$Role.edit",
            "sec$Group.browse",
            "sec$Group.lookup",
            "sec$Group.edit",
            "sec$User.browse",
            "sec$User.lookup",
            "sec$User.edit"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @Override
    public String getLocName() {
        return "Tenant User Manager";
    }
}
