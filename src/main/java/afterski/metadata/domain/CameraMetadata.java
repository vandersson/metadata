package afterski.metadata.domain;

import java.util.Map;

/**
 * @Author: vda
 */
public interface CameraMetadata {
    Map<String, Camera> cameras() throws RetreivalFailed;
}
