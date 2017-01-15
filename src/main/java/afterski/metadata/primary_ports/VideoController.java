package afterski.metadata.primary_ports;

import afterski.metadata.domain.PersistenceFailed;
import afterski.metadata.domain.Playlist;
import afterski.metadata.domain.PlaylistStitcher;
import afterski.metadata.domain.RetreivalFailed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @Author: vda
 */
@Controller
public class VideoController {
    private static Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    private PlaylistStitcher playlistService;

    @RequestMapping(value = "/videos", method = RequestMethod.GET)
    @ResponseBody
    public Playlist sortedPlaylist() throws RetreivalFailed {
        logger.debug("get /videos entered");
        return playlistService.sortedPlaylist();
    }

    @RequestMapping(value = "/videos/import", consumes = "application/json", method = RequestMethod.POST)
    public ResponseEntity importVideos(@RequestBody ImportSource importSource) throws PersistenceFailed, RetreivalFailed {
        logger.debug(importSource.toString());
        playlistService.addToPlaylist(importSource.getDestination(), importSource.getCameraOperator());
        return ResponseEntity.ok("ok");
    }
}
