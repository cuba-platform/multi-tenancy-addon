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

package com.haulmont.addon.sdbmt.core.app.multitenancy;

/**
 * Provides the current tenant id.
 */
public interface TenantProvider {

    String NAME = "cuba_TenantProvider";

    /**
     * The name attribute of a user's session to save the Tenant ID.
     */
    String TENANT_ID_ATTRIBUTE_NAME = "tenantAttrId";

    /**
     * Constant attribute user session for global visibility of all objects, regardless of the value of tenant.
     */
    String NO_TENANT = "no_tenant";

    /**
     * Provides the current tenant id for save to user session.
     *
     * @return the current tenant id.
     */
    String getCurrentUserTenantId();
}
