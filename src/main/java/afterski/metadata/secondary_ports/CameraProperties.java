package afterski.metadata.secondary_ports;

import afterski.metadata.domain.Camera;
import afterski.metadata.domain.CameraMetadata;
import afterski.metadata.domain.RetreivalFailed;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: vda
 */
@Component
public class CameraProperties implements CameraMetadata {
    private static final String FILE_LOCATION = "cameras.json";
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public Map<String, Camera> cameras() throws RetreivalFailed {
        try {
            Map<String, Camera> cameras = new HashMap<>();
            List<Camera> fileContent = mapper.readValue(this.getClass().getClassLoader().getResource(FILE_LOCATION),
                    new TypeReference<List<Camera>>() {
                    });
            fileContent.stream().forEach(c -> cameras.put(c.getOperator(), c));
            return cameras;
        } catch (IOException e) {
            throw new RetreivalFailed(e);
        }
    }
}
