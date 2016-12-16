package edu.uoc.monuments.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import edu.uoc.library.LibraryManager;
import edu.uoc.library.calback.GetCallback;
import edu.uoc.library.model.Monument;
import edu.uoc.monuments.R;

/**
 * Created by UOC on 28/09/2016.
 */
public class DetailMonumentActivity extends AppCompatActivity {

    private final static String MONUMENT_ID_KEY = "MONUMENT_ID_KEY";

    private TextView mDescription;
    private ImageView mImage;
    private ProgressBar mProgressBar;

    public static Intent makeIntent(Context context, int id) {
        Intent intent = new Intent(context, DetailMonumentActivity.class);
        intent.putExtra(MONUMENT_ID_KEY, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_monument);

        // Set views
        mDescription = (TextView) findViewById(R.id.monument_description);
        mImage = (ImageView) findViewById(R.id.monument_image);
        mProgressBar = (ProgressBar) findViewById(R.id.load_progress);

        // Get arguments
        int id_monument = getIntent().getIntExtra(MONUMENT_ID_KEY, 0);

        LibraryManager.getInstance(getApplicationContext()).getMonumentById(id_monument, new GetCallback<Monument>() {
            @Override
            public void onSuccess(Monument monument) {
                if (monument != null) {
                    mProgressBar.setVisibility(View.GONE);
                    // Set image
                    Bitmap image = LibraryManager.getInstance(getApplicationContext()).getImage(monument.getImagePath());
                    mImage.setImageBitmap(image);
                    // Set description
                    mDescription.setText(monument.getDescription());
                }
            }

            @Override
            public void onFailure(Throwable e) {
                mProgressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
