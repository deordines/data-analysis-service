package br.com.deordines.dataanalysis.service;

import br.com.deordines.dataanalysis.config.SupportedFileConfig;
import br.com.deordines.dataanalysis.exception.CreateFileException;
import br.com.deordines.dataanalysis.exception.MoveFileException;
import br.com.deordines.dataanalysis.exception.ReadFileException;
import br.com.deordines.dataanalysis.exception.WalkFileException;
import br.com.deordines.dataanalysis.exception.WriteFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    private static final String FILENAME_REGEX = "(^.*)([.+$].*)";
    private static final String FILENAME_REPLACEMENT = "$1";
    private static final String EXTENSION_REPLACEMENT = "$2";

    public Path createFile(String filename, byte[] data, String target) {
        try {
            Path targetDir = getPath(target);
            if (!existsDirectory(targetDir)) createDir(targetDir);
            Path filePath = getPath(String.format("%s\\%s", target, filename));
            return write(filePath, data);
        } catch (Exception e) {
            logger.error("Error to creating file.", e);
            throw new CreateFileException();
        }
    }

    public byte[] read(Path filePath) {
        try {
            return Files.readAllBytes(filePath);
        } catch (Exception e) {
            logger.error("Error to reading file.", e);
            throw new ReadFileException();
        }
    }

    private Path write(Path filePath, byte[] data) {
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            outputStream.write(data);
            return filePath;
        } catch (Exception e) {
            logger.error("Error to writing file.", e);
            throw new WriteFileException();
        }
    }

    public Path move(Path sourcePath, String target) {
        try {
            Path targetDir = getPath(target);
            if (!existsDirectory(targetDir)) createDir(targetDir);
            Path targetPath = getPath(String.format("%s/%s", target, sourcePath.getFileName()));
            return Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            logger.error("Error to moving file.", e);
            throw new MoveFileException();
        }
    }

    public List<Path> walk(Path path) {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk.collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error to getting files.", e);
            throw new WalkFileException();
        }
    }

    public String getFilename(Path path) {
        return path.getFileName().toString();
    }

    public Path getPath(String path) {
        return Paths.get(path);
    }

    public Boolean isFile(Path path) {
        return path.toFile().isFile();
    }

    private Path createDir(Path target) throws IOException {
        return Files.createDirectory(target);
    }

    private Boolean existsDirectory(Path path) {
        return path.toFile().isDirectory();
    }

    public Boolean isSupportedFile(String filename) {
        return SupportedFileConfig.getEXTENSIONS().stream()
                .anyMatch(x -> filename.replaceAll(FILENAME_REGEX, EXTENSION_REPLACEMENT).toUpperCase()
                        .contains(x.toUpperCase()));
    }

    public String removeExtensionFromFilename(String filename) {
        return filename.replaceAll(FILENAME_REGEX, FILENAME_REPLACEMENT);
    }
}
