package afterski.metadata.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;

/**
 * @Author: vda
 */
public class Camera {
    private String operator;
    private Duration offset;

    public Camera() {
    }

    public Camera(String operator) {
        this.operator = operator;
        this.offset = Duration.ZERO;
    }

    @JsonCreator
    public Camera(@JsonProperty("operator") String operator, @JsonProperty("millis_offset") long offset) {
        this.operator = operator;
        this.offset = Duration.ofMillis(offset);
    }

    public String getOperator() {
        return operator;
    }

    public Duration getOffset() {
        return offset;
    }
}
