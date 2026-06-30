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
