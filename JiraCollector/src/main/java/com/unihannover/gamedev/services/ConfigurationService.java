package com.unihannover.gamedev.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unihannover.gamedev.models.Configuration;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class ConfigurationService {

    /**
     * Location of the configuration file
     */
    private static final String CONFIG_FILEPATH = "config/configuration.json";

    /**
     * Returns the collector configuration object
     *
     * @return Configuration
     */
    public Configuration getConfig() {
        return this.parseFromFile();
    }

    /**
     * Persists a configuration object
     *
     * @param Configuration config
     */
    public void setConfig(Configuration config) {
        this.writeToFile(config);
    }

    /**
     * Reads the collector configuration file and parses it into an object
     *
     * @return Configuration object
     */
    private static Configuration parseFromFile() {
        try {
            return (new ObjectMapper()).readValue(new File(CONFIG_FILEPATH), Configuration.class);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Writes the collector configuration as JSON into the config file
     *
     * TODO: Implement
     *
     * @param config
     */
    private static void writeToFile(Configuration config) {
        try {
            (new ObjectMapper()).writeValue(new File(CONFIG_FILEPATH), config);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
