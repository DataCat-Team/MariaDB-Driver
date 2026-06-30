package de.datacat.driver.events;

import de.julianweinelt.datacat.dbx.api.drivers.DriverManagerService;
import de.julianweinelt.datacat.dbx.api.events.Event;
import de.julianweinelt.datacat.dbx.api.events.Subscribe;
import de.julianweinelt.datacat.dbx.api.ui.UIService;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;

@Slf4j
public class UIListener {
    @Subscribe("UIInitializedEvent")
    public void onUIEvent(Event event) {
        log.info("UI Initialized");
        if (DriverManagerService.instance().getLoadedDrivers().contains("mariadb")) return;

        JFrame frame = event.get("frame").asValue(JFrame.class);

        int result = JOptionPane.showConfirmDialog(frame, "You've installed to MariaDB Driver Yarn, but not loaded any drivers yet.\n" +
                "Would you like to open the driver manager?", "No MariaDB Drivers installed", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (result == JOptionPane.YES_OPTION) {
            UIService.instance().openDialog("de.julianweinelt.datacat.ui.driver.DriverDownloadDialog", frame, true, "mariadb");
        }
    }
}