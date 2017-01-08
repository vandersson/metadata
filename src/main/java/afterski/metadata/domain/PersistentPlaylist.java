package afterski.metadata.domain;

/**
 * @Author: vda
 */
public interface PersistentPlaylist {

    Playlist readState() throws PersistenceFailed;

    void saveState(Playlist playlist) throws PersistenceFailed;
}
