package afterski.metadata.secondary_ports;

import afterski.metadata.domain.PersistenceFailed;
import afterski.metadata.domain.PersistentPlaylist;
import afterski.metadata.domain.Playlist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author: vda
 */
@Component
public class JsonFilePlaylist implements PersistentPlaylist {
    private static final String FILE_LOCATION = "./state.json";
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public Playlist readState() throws PersistenceFailed {
        try {
            Playlist savedPlaylist = new Playlist();
            if (Files.exists(Paths.get(FILE_LOCATION))) {
                savedPlaylist = mapper.readValue(new File(FILE_LOCATION), Playlist.class);

            }
            return savedPlaylist;
        } catch (IOException e) {
            throw new PersistenceFailed(e);
        }
    }

    @Override
    public void saveState(Playlist playlist) throws PersistenceFailed {
        try {
            mapper.writeValue(new File(FILE_LOCATION), playlist);
        } catch (IOException e) {

            throw new PersistenceFailed(e);
        }
    }
}
