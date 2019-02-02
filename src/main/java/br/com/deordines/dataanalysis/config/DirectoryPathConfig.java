package br.com.deordines.dataanalysis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "directory")
public class DirectoryPathConfig {

    private static String IN;
    private static String PROCESSING;
    private static String PROCESSED;
    private static String OUT;
    private static String ERROR;

    public static String getIN() {
        return IN;
    }

    public void setIN(String IN) {
        DirectoryPathConfig.IN = IN;
    }

    public static String getPROCESSING() {
        return PROCESSING;
    }

    public void setPROCESSING(String PROCESSING) {
        DirectoryPathConfig.PROCESSING = PROCESSING;
    }

    public static String getPROCESSED() {
        return PROCESSED;
    }

    public void setPROCESSED(String PROCESSED) {
        DirectoryPathConfig.PROCESSED = PROCESSED;
    }

    public static String getOUT() {
        return OUT;
    }

    public void setOUT(String OUT) {
        DirectoryPathConfig.OUT = OUT;
    }

    public static String getERROR() {
        return ERROR;
    }

    public void setERROR(String ERROR) {
        DirectoryPathConfig.ERROR = ERROR;
    }
}
