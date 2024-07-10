// Use Case: Suppose we have a MediaPlayer interface that plays audio files, and an existing 
// VideoPlayer class that plays video files. We need to adapt the VideoPlayer 
// to work with MediaPlayer.
interface MediaPlayer {
    void play(String audioType, String fileName);
}
class VideoPlayer {
    public void playVideo(String fileName) {
        System.out.println("Playing video file. Name: " + fileName);
    }
}
class MediaAdapter implements MediaPlayer {
    VideoPlayer videoPlayer;

    public MediaAdapter(VideoPlayer videoPlayer) {
        this.videoPlayer = videoPlayer;
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("video")) {
            videoPlayer.playVideo(fileName);
        }
    }
}
class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("audio")) {
            System.out.println("Playing audio file. Name: " + fileName);
        } else if (audioType.equalsIgnoreCase("video")) {
            mediaAdapter = new MediaAdapter(new VideoPlayer());
            mediaAdapter.play(audioType, fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("audio", "song.mp3");
        audioPlayer.play("video", "movie.mp4");
        audioPlayer.play("video", "series.mkv");
    }
}
