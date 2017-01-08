package afterski.metadata.domain;

import java.net.URI;

/**
 * @Author: vda
 */
public interface MetadataExtractor {

    Playlist extractFromDirectory(URI directory, Camera camera) throws RetreivalFailed;


}
