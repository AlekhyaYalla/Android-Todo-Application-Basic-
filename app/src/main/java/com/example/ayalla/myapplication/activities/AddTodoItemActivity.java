package com.example.ayalla.myapplication.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ayalla.myapplication.R;
import com.example.ayalla.myapplication.constants.Constants;

import java.io.ByteArrayOutputStream;

import static com.example.ayalla.myapplication.constants.Constants.CAMERA_REQUEST_CODE;

class AddTodoItemActivity extends Activity {
    private Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_todo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            ImageView capturedImage = (ImageView) findViewById(R.id.capturedImage);
            capturedImage.setImageBitmap(photo);
        }
    }

    public void addTodoItem(View view) {
        String title = ((EditText) findViewById(R.id.title)).getText().toString();
        String description = ((EditText) findViewById(R.id.description)).getText().toString();

        if (title.isEmpty())
            setResult(RESULT_CANCELED);
        else
            setResult(RESULT_OK, getIntentForResult(title,description));
        finish();
    }

    private Intent getIntentForResult(String title, String description) {
        Intent addIntent = new Intent();
        addIntent.putExtra(Constants.TITLE_KEY, title);
        addIntent.putExtra(Constants.DESCRIPTION_KEY, description);
        if(photo == null)
            addIntent.putExtra(Constants.IMAGE_KEY,"");
        else
            addIntent.putExtra(Constants.IMAGE_KEY,BitMapToString(photo));
        return addIntent;
    }

    public void captureImage(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}
