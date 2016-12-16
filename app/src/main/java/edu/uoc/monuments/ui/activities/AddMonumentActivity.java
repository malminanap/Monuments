package edu.uoc.monuments.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.uoc.library.LibraryManager;
import edu.uoc.library.calback.SaveCallback;
import edu.uoc.library.model.Monument;
import edu.uoc.monuments.R;

public class AddMonumentActivity extends AppCompatActivity {

    //Variable
    static final int REQUEST_IMAGE_CAPTURE = 1;

    //Views
    private TextView mName;
    private TextView mCity;
    private TextView mDescription;
    private ImageView mFoto;
    private Button selectImage;
    private Button insertMonument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_monument);

        //Define views
        selectImage = (Button) findViewById(R.id.select_image);
        insertMonument = (Button) findViewById(R.id.insert);
        mName = (TextView) findViewById(R.id.monument_name);
        mCity = (TextView) findViewById(R.id.monument_city);
        mDescription = (TextView) findViewById(R.id.monument_description);
        mFoto = (ImageView) findViewById(R.id.monument_Foto);

        //Onclick button image call function takepicture
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        //Onclick button insert call function insert
        insertMonument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMonument();
            }
        });

    }

    //Function to up the image
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    //Function to insert a monument
    private void insertMonument() {

        //Get the value of the Views and Image
        String mNameInsert = mName.getText().toString();
        String mCityInsert = mCity.getText().toString();
        String mDescriptionInsert = mDescription.getText().toString();
        Bitmap mFotoInsert = ((BitmapDrawable) mFoto.getDrawable()).getBitmap();

        //Insert the monument
        LibraryManager.getInstance(getApplicationContext()).
                saveMonumentInBackground(mNameInsert, mCityInsert, mDescriptionInsert, mFotoInsert,
                        new SaveCallback() {
                            @Override
                            public void onSuccess(Monument monument) {

                                //Define message to show
                                CharSequence message = "Monument insert succesfully!";
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                                //Generate monument to send
                                Intent oMonument = new Intent();
                                oMonument.putExtra("monumentId", monument.getId());

                                //Define result
                                setResult(RESULT_OK, oMonument);

                                //Finish the activity
                                finish();
                            }

                            @Override
                            public void onFailure(Throwable e) {
                                //Define message
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                setResult(RESULT_CANCELED);
                                finish();
                            }
                        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mFoto.setImageBitmap(imageBitmap);
        }
    }
}
