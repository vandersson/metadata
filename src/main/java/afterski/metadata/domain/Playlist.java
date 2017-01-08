package afterski.metadata.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: vda
 */
public class Playlist {
    List<Video> videos = new ArrayList<Video>();

    public void insertVideo(Video video) {
        videos.add(video);
    }

    public Playlist mergePlaylists(Playlist other) {
        return new Playlist();
    }

    public List<Video> getVideos() {
        return videos;
    }
}
