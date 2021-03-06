package com.basejava.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    protected static final File PROPS = new File(".\\storage\\resumes.properties");
    private Properties props = new Properties();
    private File storageDir;

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try(InputStream is = new FileInputStream("./config/resumes.properties")) {
            props.load(is);
            storageDir = new File(props.getProperty("storage.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config fail " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }
}
