package afterski.metadata.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;
import java.time.Duration;
import java.time.Instant;


/**
 * @Author: vda
 */
public class Video implements Comparable<Video> {
    private URI fileSystemLocation;
    private Instant creationTime;
    private Instant normalizedStartTime;
    private Duration length;
    private String author;

    public Video() {
    }

    public Video(URI fileSystemLocation, Instant creationTime, Duration length, Camera camera) {
        this.fileSystemLocation = fileSystemLocation;
        this.creationTime = creationTime;
        this.normalizedStartTime = creationTime.plus(camera.getOffset());
        this.length = length;
        this.author = camera.getOperator();
    }

    @JsonCreator
    public Video(@JsonProperty("fileSystemLocation") URI fileSystemLocation,
                 @JsonProperty("creationTimestamp") long creationTime,
                 @JsonProperty("normalizedStartTimestamp") long normalizedStartTime,
                 @JsonProperty("lengthInMillis") long length,
                 @JsonProperty("author")String author) {
        this.fileSystemLocation = fileSystemLocation;
        this.creationTime = Instant.ofEpochMilli(creationTime);
        this.normalizedStartTime = Instant.ofEpochMilli(normalizedStartTime);
        this.length = Duration.ofMillis(length);
        this.author = author;
    }

    public URI getFileSystemLocation() {
        return fileSystemLocation;
    }

    public long getCreationTimestamp() {
        return creationTime.toEpochMilli();
    }

    public long getLengthInMillis() {
        return length.toMillis();
    }

    public long getNormalizedStartTimestamp() {
        return normalizedStartTime.toEpochMilli();
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int compareTo(Video o) {
        return normalizedStartTime.compareTo(o.normalizedStartTime);
    }
}
