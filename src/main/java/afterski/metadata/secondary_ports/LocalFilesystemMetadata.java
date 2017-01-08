package afterski.metadata.secondary_ports;

import afterski.metadata.domain.Camera;
import afterski.metadata.domain.MetadataExtractor;
import afterski.metadata.domain.Playlist;
import afterski.metadata.domain.RetreivalFailed;
import afterski.metadata.domain.Video;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.MovieHeaderBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.time.Duration;

/**
 * @Author: vda
 */
@Component
public class LocalFilesystemMetadata implements MetadataExtractor {
    private static Logger logger = LoggerFactory.getLogger(LocalFilesystemMetadata.class);
    private PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:**.{mp4,MP4}");

    private Video read(Path videoFile, Camera camera) throws IOException {
        if (!Files.exists(videoFile)) {
            throw new FileNotFoundException("File " + videoFile.toString() + " not exists");
        }

        if (!Files.isReadable(videoFile)) {
            throw new IllegalStateException("No read permissions to file " + videoFile.toString());
        }
        IsoFile isoFile = new IsoFile(videoFile.toString());

        MovieHeaderBox nam = isoFile.getMovieBox().getMovieHeaderBox();
        Video vid = new Video(videoFile.toUri(),
                nam.getCreationTime().toInstant(),
                Duration.ofMillis(nam.getDuration()),
                camera.getOperator());
        isoFile.close();
        return vid;
    }

    @Override
    public Playlist extractFromDirectory(URI directory, Camera camera) throws RetreivalFailed {
        try {
            Playlist playlist = new Playlist();
            Files.newDirectoryStream(Paths.get(directory))
                    .forEach(path -> {
                        if (matcher.matches(path)) {
                            try {
                                logger.debug("trying to parse {}", path);
                                playlist.insertVideo(read(path, camera));
                            } catch (IOException e) {
                                logger.error("Could not read file with path: {}", path);
                            }
                        }
                    });
            return playlist;
        } catch (IOException  e) {
            throw new RetreivalFailed(e);
        }
    }
}
