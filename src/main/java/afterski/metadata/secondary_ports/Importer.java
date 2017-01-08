package afterski.metadata.secondary_ports;

import afterski.metadata.domain.Camera;
import afterski.metadata.domain.MetadataExtractor;
import afterski.metadata.domain.Playlist;
import afterski.metadata.domain.RetreivalFailed;
import afterski.metadata.domain.Video;
import com.coremedia.iso.IsoFile;
import com.coremedia.iso.boxes.MovieHeaderBox;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.mp4parser.util.Path;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: vda
 */
public class Importer implements MetadataExtractor {

    public void read(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Usage: java -jar metadata.jar inputDirectory [outputDirectory]");
        }

        Video vid = read("/home/vda/dev/movies/GOPR0016.MP4");
        ObjectMapper om = new ObjectMapper();
        om.writeValue(System.out, vid);
    }

    public Video read(File videoFile) {

        if (!videoFile.exists()) {
            throw new FileNotFoundException("File " + videoFilePath + " not exists");
        }

        if (!videoFile.canRead()) {
            throw new IllegalStateException("No read permissions to file " + videoFilePath);
        }
        IsoFile isoFile = new IsoFile(videoFilePath);

        MovieHeaderBox nam = Path.getPath(isoFile, "/moov[0]/mvhd[0]");
        Video vid = new Video(new URI(videoFilePath), nam.getCreationTime().toInstant(), Duration.ofMillis(nam.getDuration()));
        isoFile.close();
        return vid;
    }

    @Override
    public Playlist extractFromDirectory(URI directory, Camera camera) throws RetreivalFailed {
        try {
            Playlist playlist = new Playlist();
            Files.newDirectoryStream(Paths.get(directory))
                    .forEach(path -> playlist.insertVideo(read(path.toFile())));
            return null;
        } catch (IOException  e) {
            throw new RetreivalFailed(e);
        }
    }
}
