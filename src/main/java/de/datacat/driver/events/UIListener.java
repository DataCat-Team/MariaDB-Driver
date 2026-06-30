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