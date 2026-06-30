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

import java.util.ArrayList;
import java.util.List;

public record MariaDBVersion(String version, String id, boolean eol) {
    public static final List<MariaDBVersion> versions = new ArrayList<>();

    static {
        versions.add(new MariaDBVersion("3.5.9", "4765352", false));
        versions.add(new MariaDBVersion("3.5.8", "4639714", false));
        versions.add(new MariaDBVersion("3.5.7", "4550269", false));
        versions.add(new MariaDBVersion("3.5.6", "4461085", false));
        versions.add(new MariaDBVersion("3.5.5", "4441309", false));
        versions.add(new MariaDBVersion("3.5.4", "4308494", false));
        versions.add(new MariaDBVersion("3.4.3", "4765336", false));
        versions.add(new MariaDBVersion("3.3.5", "4765325", false));
        versions.add(new MariaDBVersion("2.7.14", "4765303", false));
    }

    public static MariaDBVersion getVersion(String version) {
        return versions.stream().filter(v -> v.version().equals(version)).findFirst().orElse(null);
    }
}
