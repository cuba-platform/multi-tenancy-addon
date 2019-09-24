/*
 * Copyright (c) 2008-2019 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.addon.sdbmt.core.sys.listener;

import com.haulmont.addon.sdbmt.config.TenantConfig;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.entity.UserRole;
import com.haulmont.addon.sdbmt.entity.Tenant;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component("cuba_TenantListener")
public class TenantListener implements BeforeUpdateEntityListener<Tenant>, BeforeInsertEntityListener<Tenant> {

    @Inject
    protected TenantConfig tenantConfig;

    @Inject
    protected Metadata metadata;

    @Override
    public void onBeforeInsert(Tenant tenant, EntityManager entityManager) {
        updateAccessGroupTenantId(tenant, entityManager);
        updateTenantAdmin(tenant, entityManager);
    }

    @Override
    public void onBeforeUpdate(Tenant tenant, EntityManager entityManager) {
        updateAccessGroupTenantId(tenant, entityManager);
        updateTenantAdmin(tenant, entityManager);
    }

    protected void updateTenantAdmin(Tenant tenant, EntityManager em) {
        User admin = tenant.getAdmin();
        if (admin != null) {
            admin = em.reloadNN(admin, "user.edit");
            admin.setTenantId(tenant.getTenantId());
            assignDefaultTenantRole(admin, em);
            tenant.setAdmin(admin);
        }
    }

    protected void assignDefaultTenantRole(User user, EntityManager em) {
        Role tenantDefaultRole = tenantConfig.getDefaultTenantRole();
        if (tenantDefaultRole == null) {
            throw new RuntimeException("Default tenant role not found");
        }

        if (!userHasRole(user, tenantDefaultRole)) {
            UserRole userRole = metadata.create(UserRole.class);
            userRole.setUser(user);
            userRole.setRole(tenantDefaultRole);
            em.persist(userRole);
            user.getUserRoles().add(userRole);
        }
    }

    protected void updateAccessGroupTenantId(Tenant tenant, EntityManager em) {
        if (tenant.getGroup() != null) {
            Group accessGroup = em.reload(tenant.getGroup(), "group-with-tenantId");
            if (accessGroup != null) {
                accessGroup.setTenantId(tenant.getTenantId());
            }
        }
    }

    protected boolean userHasRole(User user, Role role) {
        if (user.getUserRoles() == null || user.getUserRoles().isEmpty()) {
            return false;
        }
        for (UserRole userRole : user.getUserRoles()) {
            if (role.equals(userRole.getRole())) {
                return true;
            }
        }
        return false;
    }
}
