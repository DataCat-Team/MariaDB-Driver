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

package de.datacat.driver.database;

import de.julianweinelt.datacat.dbx.database.ADatabase;
import de.julianweinelt.datacat.dbx.database.DatabaseMetaData;
import de.julianweinelt.datacat.dbx.database.DatabaseSyntax;

import java.util.Map;

public class DBMeta implements DatabaseMetaData {
    @Override
    public String jdbcString() {
        return "mariadb";
    }

    @Override
    public int defaultPort() {
        return 3306;
    }

    @Override
    public String jdbcURL() {
        return "jdbc:mariadb://${server}/${database}${parameters}";
    }

    @Override
    public DatabaseSyntax syntax() {
        return DatabaseSyntax.MYSQL;
    }

    @Override
    public String engineName() {
        return "mariadb";
    }

    @Override
    public String parameters(Map<String, String> parameters) {
        StringBuilder paramURL = new StringBuilder("?");
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            paramURL.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return paramURL.substring(0, paramURL.length() - 1);
    }

    @Override
    public ADatabase.ParameterBuilder defaultParameters() {
        return new ADatabase.ParameterBuilder()
                .parameter("useJDBCCompliantTimezoneShift", "true")
                .parameter("useLegacyDatetimeCode", "false")
                .parameter("serverTimezone", "UTC")
                .parameter("autoReconnect", "true");
    }
}
