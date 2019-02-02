package br.com.deordines.dataanalysis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "parser.character")
public class ParserCharacterConfig {

    private static String STANDARD;
    private static String ITEMS;
    private static String ITEM;

    public static String getSTANDARD() {
        return STANDARD;
    }

    public void setSTANDARD(String STANDARD) {
        ParserCharacterConfig.STANDARD = STANDARD;
    }

    public static String getITEMS() {
        return ITEMS;
    }

    public void setITEMS(String ITEMS) {
        ParserCharacterConfig.ITEMS = ITEMS;
    }

    public static String getITEM() {
        return ITEM;
    }

    public void setITEM(String ITEM) {
        ParserCharacterConfig.ITEM = ITEM;
    }
}
