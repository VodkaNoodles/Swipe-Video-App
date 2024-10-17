package com.example.swipevideoapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference;

    FirebaseDatabase firebaseDatabase;
    /**
     * Called when the activity is starting. This is where initialization
     * should occur, such as setting the content view and initializing
     * components. This method receives a savedInstanceState parameter
     * for restoring the activity's previous state if it exists.
     *
     * @param savedInstanceState If the activity is being re-initialized
     *                           after previously being shut down, this Bundle
     *                           contains the data it most recently supplied in
     *                           onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final ViewPager2 videoViewPager = findViewById(R.id.videosViewPager);

        List<VideoItem> videoItemsList = new ArrayList<>();

        VideoItem videoFlat = new VideoItem();
        videoFlat.videoURL ="https://firebasestorage.googleapis.com/v0/b/swipe-video-app-stuffs.appspot.com/o/FlatTireVid.mp4?alt=media&token=78010699-b151-4c60-b6c4-12c858bf8ef6";
        videoFlat.videoTitle = "Flat tire on i90 is not fun   ID: 001";
        videoFlat.videoDescription = "Having good friends is nice";
        videoItemsList.add(videoFlat);


        VideoItem videoFrame = new VideoItem();
        videoFrame.videoURL ="https://firebasestorage.googleapis.com/v0/b/swipe-video-app-stuffs.appspot.com/o/FrameCoating.mp4?alt=media&token=852a4dce-345e-421b-819c-44dbbf10d657";
        videoFrame.videoTitle = "Frame coating!!!   ID: 002";
        videoFrame.videoDescription = "Not letting rust take my truck";
        videoItemsList.add(videoFrame);

        VideoItem videoBikePark = new VideoItem();
        videoBikePark.videoURL ="https://firebasestorage.googleapis.com/v0/b/swipe-video-app-stuffs.appspot.com/o/DUTHIE%20CLIP.mp4?alt=media&token=ae672945-cb37-4d46-a74c-001c82b25945";
        videoBikePark.videoTitle = "2nd gen spotted at bike park";
        videoBikePark.videoDescription = "About to go ride at duthie MTB park";
        videoItemsList.add(videoBikePark);



        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));
    }
}