/*
 * MariaDB Driver Yarn for providing MariaDB support in DataCat
 *  Copyright (C) 2026 Julian Weinelt
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see https://www.gnu.org/licenses/.
 *
 */

package de.datacat.driver;

import de.julianweinelt.datacat.dbx.api.VersionStatus;
import de.julianweinelt.datacat.dbx.api.drivers.PluginDriver;

import java.util.concurrent.CompletableFuture;

public class Driver extends PluginDriver {
    public Driver() {
        super("mariadb");
    }

    @Override
    public CompletableFuture<String> latestVersion() {
        return CompletableFuture.completedFuture(MariaDBVersion.versions.get(0).version());
    }

    @Override
    public String downloadURL(String version) {
        MariaDBVersion ver = MariaDBVersion.getVersion(version);
        return "https://dlm.mariadb.com/" + ver.id() + "/Connectors/java/connector-java-"
                + version + "/mariadb-java-client-" + version + ".jar";
    }

    @Override
    public void defineVersions() {
        createVersions();
    }

    @Override
    public CompletableFuture<Void> defineVersionsASync() {
        createVersions();
        return CompletableFuture.completedFuture(null);
    }

    private void createVersions() {
        for (MariaDBVersion v : MariaDBVersion.versions) {
            addVersion(v.version(), true, VersionStatus.RELEASE);
        }
    }
}