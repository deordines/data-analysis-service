package br.com.deordines.dataanalysis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class FolderConfig {

    @Bean
    public void createFolderIn() throws IOException {
        Files.createDirectories(Paths.get(DirectoryPathConfig.getIN()));
    }
}
