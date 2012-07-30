package com.biomatters.PlasmoDB;

import com.biomatters.geneious.publicapi.plugin.GeneiousPlugin;
import com.biomatters.geneious.publicapi.plugin.GeneiousService;
import java.io.File;

public class PlasmoDBPlugin extends GeneiousPlugin {
    public String getName() {
        return "PlasmoDB Plugin";
    }

    public String getHelp() {
        return "Help with PlasmoDBPlugin";
    }

    public String getDescription() {
        return "Access PlasmoDB database sequence information";
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
        		new PlasmoDB(new File(pluginDirectory.getAbsolutePath()))
        };
    }


}
