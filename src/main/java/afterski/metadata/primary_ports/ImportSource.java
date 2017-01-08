package afterski.metadata.primary_ports;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author: vda
 */
public class ImportSource {
    private URI destination;
    private String cameraOperator;

    public ImportSource() {
    }

    public ImportSource(URI destination, String cameraOperator) throws URISyntaxException {
        this.destination = destination;
        this.cameraOperator = cameraOperator;
    }

    public URI getDestination() {
        return destination;
    }

    public String getCameraOperator() {
        return cameraOperator;
    }

    @Override
    public String toString() {
        return "ImportSource{" +
                "destination=" + destination +
                ", cameraOperator='" + cameraOperator + '\'' +
                '}';
    }
}
