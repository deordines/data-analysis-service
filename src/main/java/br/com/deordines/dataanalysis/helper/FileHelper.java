package br.com.deordines.dataanalysis.helper;

import br.com.deordines.dataanalysis.exception.CreateFileException;
import br.com.deordines.dataanalysis.exception.CustomException;
import br.com.deordines.dataanalysis.exception.MoveFileException;
import br.com.deordines.dataanalysis.exception.ReadFileException;
import br.com.deordines.dataanalysis.exception.WalkFileException;
import br.com.deordines.dataanalysis.exception.WriteFileException;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHelper {

    public static Path createFile(String filename, byte[] data, String target) {
        try {
            Path targetDir = getPath(target);
            if (!existsDirectory(targetDir)) createDir(targetDir);
            Path filePath = getPath(String.format("%s\\%s", target, filename));
            return write(filePath, data);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CreateFileException();
        }
    }

    public static byte[] read(Path filePath) {
        try {
            return Files.readAllBytes(filePath);
        } catch (Exception e) {
            throw new ReadFileException();
        }
    }

    private static Path write(Path filePath, byte[] data) {
        try (OutputStream outputStream = Files.newOutputStream(filePath)) {
            outputStream.write(data);
            return filePath;
        } catch (Exception e) {
            throw new WriteFileException();
        }
    }

    public static Path move(Path sourcePath, String target) {
        try {
            Path targetDir = getPath(target);
            if (!existsDirectory(targetDir)) createDir(targetDir);
            Path targetPath = getPath(String.format("%s/%s", target, sourcePath.getFileName()));
            return Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new MoveFileException();
        }
    }

    public static List<Path> walk(Path path) {
        try (Stream<Path> walk = Files.walk(path)) {
            return walk.collect(Collectors.toList());
        } catch (Exception e) {
            throw new WalkFileException();
        }
    }

    public static String getFilename(Path path) {
        return path.getFileName().toString();
    }

    public static Path getPath(String path) {
        return Paths.get(path);
    }

    public static Boolean isFile(Path path) {
        return path.toFile().isFile();
    }

    private static Path createDir(Path target) throws Exception {
        return Files.createDirectory(target);
    }

    private static Boolean existsDirectory(Path path) {
        return path.toFile().isDirectory();
    }
}
