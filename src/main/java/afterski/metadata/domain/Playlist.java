package afterski.metadata.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: vda
 */
public class Playlist {
    private List<Video> videos = new ArrayList<>();

    public void insertVideo(Video video) {
        videos.add(video);
    }

    public Playlist mergePlaylists(Playlist other) {
        Playlist mergedPlaylist = new Playlist();
        mergedPlaylist.videos.addAll(this.videos);
        mergedPlaylist.videos.addAll(other.videos);
        Collections.sort(mergedPlaylist.videos);

        return mergedPlaylist;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
