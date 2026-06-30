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

import de.datacat.driver.database.DB;
import de.datacat.driver.database.DBMeta;
import de.datacat.driver.events.UIListener;
import de.julianweinelt.datacat.dbx.api.drivers.DriverDownloadManager;
import de.julianweinelt.datacat.dbx.api.plugins.DbxPlugin;
import de.julianweinelt.datacat.dbx.database.DatabaseRegistry;

public class MariaDB extends DbxPlugin {
    @Override
    public void preInit() {
        getLogger().info("Loading MariaDB Driver plugin...");
        getRegistry().registerListener(new UIListener(), this);
    }

    @Override
    public void init() {
        getLogger().info("Registering MariaDB Database Interpreters...");
        DatabaseRegistry.instance().registerMapping("mariadb", DB::new, new DBMeta());
        DriverDownloadManager.instance().register(new Driver());
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void onDefineEvents() {

    }
}
