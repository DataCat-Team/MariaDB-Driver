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