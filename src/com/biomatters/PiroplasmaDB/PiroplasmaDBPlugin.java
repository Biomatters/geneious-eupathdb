package com.biomatters.PiroplasmaDB;

import com.biomatters.geneious.publicapi.plugin.GeneiousPlugin;
import com.biomatters.geneious.publicapi.plugin.GeneiousService;

import java.io.File;

/**
 * Copyright (C) 2012, Charles Ma <charles_ma@email.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 * 
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
 * Boston, MA  02110-1301, USA.
 */
public class PiroplasmaDBPlugin extends GeneiousPlugin {
    public String getName() {
        return "PiroplasmaDB Plugin";
    }

    public String getHelp() {
        return "Help with PiroplasmaDBPlugin";
    }

    public String getDescription() {
        return "Access PiroplasmaDB database sequence information";
    }

    public String getAuthors() {
        return "Charles Ma, Svenja Gunther, Dept. of Microbiology, Monash University";
    }

    public String getVersion() {
        return "0.1";
    }

    public String getMinimumApiVersion() {
        return "4.1";
    }

    public int getMaximumApiVersion() {
        return 4;
    }

    private File pluginDirectory;
    public void initialize(File pluginUserDirectory, File pluginDirectory) {
        this.pluginDirectory = pluginDirectory;
    }

    public GeneiousService[] getServices() {
        return new GeneiousService[]{
                new PiroplasmaDB(new File(pluginDirectory.getAbsolutePath()))
        };
    }
}
