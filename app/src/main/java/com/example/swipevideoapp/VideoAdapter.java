package com.example.swipevideoapp;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>{

    private List<VideoItem> videoItems;
    /**
     * Constructs a new VideoAdapter with the specified list of video items.
     *
     * @param videoItems A list of VideoItem objects that the adapter will use
     *                   to display video data in a RecyclerView or similar view.
     */
    public VideoAdapter(List<VideoItem> videoItems){
        this.videoItems = videoItems;
    }

    /**
     * Creates a new ViewHolder for the video items. This method is called
     * when the RecyclerView needs a new ViewHolder to represent item.
     *
     * @param parent   The ViewGroup into which the new View will be added
     *                 after it is bound to an adapter position.
     * @param viewType An integer that indicates the type of the view.
     *                 This can be used to provide different layouts for
     *                 different item types.
     * @return A new instance of VideoViewHolder, which holds the view for
     *         the video item.
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video,parent,false)
        );
    }

    /**
     * Binds video data to given viewHold (param).
     * this is called by RecyclerView, updates contents viewHolder
     * using video item at pos in data set
     *
     * @param holder   The holder to be updated to represent
     *                 the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     *                 This is the index video item that should be bound to
     *                 holder.
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));
    }
    /**
     * returns num of all items in data set so RecyclerView knows how many items displayed
     * @return The number of video items in the adapter's data set.
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }



    static class VideoViewHolder extends RecyclerView.ViewHolder {
        TextView textVideoTitle1, textVideoDescription1;
        VideoView videoView;
        ProgressBar progressBar;
        /**
         * Creates a new VideoViewHolder that holds a view for a video item.
         *
         * @param itemView The view representing the video item, typically inflated from XML.
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle1 = itemView.findViewById(R.id.textViewTitle);
            textVideoDescription1 = itemView.findViewById(R.id.textVideoDescription);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        /**
         * Sets the video data for this ViewHolder.
         *
         * @param videoItem The VideoItem object containing data to be displayed.
         */
        void setVideoData(VideoItem videoItem){
            textVideoTitle1.setText(videoItem.videoTitle);
            textVideoDescription1.setText(videoItem.videoDescription);
            videoView.setVideoPath(videoItem.videoURL);

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /**
                 * Called when the media player is prepped to play the video.
                 * This method is called when the media is loaded and ready for playback.
                 *
                 * @param mp The MediaPlayer that is prepared and ready to play.
                 */
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mp.start();

                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float  screenRatio = videoView.getWidth()/(float) videoView.getHeight();

                    float scale = videoRatio / screenRatio;
                    if (scale >= 1f){
                        videoView.setScaleX(scale);
                    }else{
                        videoView.setScaleY(1f / scale);
                    }

                }

            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                /**
                 * Called when the media player has completed playing the video.
                 * This method is called after the media playback has finished,
                 * replays video
                 * @param mp The MediaPlayer that has finished playback.
                 */
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });

        }


    }
}
