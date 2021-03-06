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

    @Autowired
    private CameraMetadata cameraMetadata;


    public Playlist sortedPlaylist() throws RetreivalFailed {
        return state.readState();
    }

    public void addToPlaylist(URI source, String cameraOperator) throws PersistenceFailed, RetreivalFailed {
        Playlist savedPlaylist = state.readState();

        Camera camera = cameraMetadata.cameras().getOrDefault(cameraOperator, new Camera(cameraOperator));
        //fetch actual camera data
        Playlist importBatch = metadataExtractor.extractFromDirectory(source, camera);

        Playlist combinedPlaylist = savedPlaylist.mergePlaylists(importBatch);

        state.saveState(combinedPlaylist);
    }

}
