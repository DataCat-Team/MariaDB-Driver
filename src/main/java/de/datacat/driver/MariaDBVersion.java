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
