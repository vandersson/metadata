package afterski.metadata.domain;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;


/**
 * @Author: vda
 */
public class Video implements Comparable<Video> {
    private URI fileSystemLocation;
    private Instant creationTime;
    private Optional<Instant> normalizedStartTime = Optional.empty();
    private Duration length;
    private String author;

    public Video() {
    }

    public Video(URI fileSystemLocation, Instant creationTime, Duration length, String author) {
        this.fileSystemLocation = fileSystemLocation;
        this.creationTime = creationTime;
        this.length = length;
        this.author = author;
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
        return normalizedStartTime.orElse(creationTime);
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int compareTo(Video o) {
        return getNormalizedStartTime().compareTo(o.getNormalizedStartTime());
    }
}
