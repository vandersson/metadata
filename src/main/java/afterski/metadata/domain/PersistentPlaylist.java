package afterski.metadata.domain;

/**
 * @Author: vda
 */
public interface PersistentPlaylist {

    Playlist readState() throws RetreivalFailed;

    void saveState(Playlist playlist) throws PersistenceFailed;
}
