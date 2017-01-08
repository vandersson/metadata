package afterski.metadata.domain;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;


/**
 * @Author: vda
 */
public class Video {
    private URI fileSystemLocation;
    private Instant creationTime;
    private Instant normalizedStartTime;
    private Duration length;
    private String author;

    public Video() {
    }

    public Video(URI fileSystemLocation, Instant creationTime, Duration length) {
        this.fileSystemLocation = fileSystemLocation;
        this.creationTime = creationTime;
        this.length = length;
    }

    public URI getFileSystemLocation() {
        return fileSystemLocation;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public Duration getLength() {
        return length;
    }

    public Instant getNormalizedStartTime() {
        return normalizedStartTime;
    }

    public String getAuthor() {
        return author;
    }
}
