package afterski.metadata.domain;

import java.time.Duration;

/**
 * @Author: vda
 */
public class Camera {
    private String operator;
    private Duration offset;

    public Camera() {
    }

    public Camera(String operator, Duration offset) {
        this.operator = operator;
        this.offset = offset;
    }

    public String getOperator() {
        return operator;
    }

    public Duration getOffset() {
        return offset;
    }
}
