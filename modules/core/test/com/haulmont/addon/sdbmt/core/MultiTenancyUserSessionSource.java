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

package com.haulmont.addon.sdbmt.core;

import com.haulmont.cuba.core.sys.AbstractUserSessionSource;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Collections;
import java.util.Locale;
import java.util.UUID;

public class MultiTenancyUserSessionSource extends AbstractUserSessionSource {

    public static final String USER_ID = "60885987-1b61-4247-94c7-dff348347f93";

    private UserSession session;

    @Override
    public boolean checkCurrentUserSession() {
        return true;
    }

    @Override
    public synchronized UserSession getUserSession() {
        if (session == null) {
            User user = new User();
            user.setId(UUID.fromString(USER_ID));
            user.setSysTenantId("tenant-a");
            user.setLogin("userA");
            user.setName("Tenant A");
            user.setPassword(DigestUtils.md5Hex("password"));

            session = new UserSession(UUID.randomUUID(), user, Collections.emptyList(), Locale.forLanguageTag("en"), false);
        }
        return session;
    }

    public void setUserSession(UserSession session) {
        this.session = session;
    }
}

