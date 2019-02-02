package br.com.deordines.dataanalysis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("supported-file")
public class SupportedFileConfig {

    private static List<String> EXTENSIONS;

    public static List<String> getEXTENSIONS() {
        return EXTENSIONS;
    }

    public void setEXTENSIONS(List<String> EXTENSIONS) {
        SupportedFileConfig.EXTENSIONS = EXTENSIONS;
    }
}
