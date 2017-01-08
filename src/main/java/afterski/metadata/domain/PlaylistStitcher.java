package afterski.metadata.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;

/**
 * @Author: vda
 */
@Service
public class PlaylistStitcher {
    @Autowired
    private PersistentPlaylist state;

    @Autowired
    private MetadataExtractor metadataExtractor;

    public Playlist sortedPlaylist() throws PersistenceFailed {
        return state.readState();
    }

    public void addToPlaylist(URI source, String cameraOperator) throws PersistenceFailed {
        Playlist savedPlaylist = state.readState();

        Camera camera = new Camera();
        Playlist importBatch = metadataExtractor.extractFromDirectory(source, camera);

        Playlist combinedPlaylist = savedPlaylist.mergePlaylists(importBatch);

        state.saveState(combinedPlaylist);
    }

}
