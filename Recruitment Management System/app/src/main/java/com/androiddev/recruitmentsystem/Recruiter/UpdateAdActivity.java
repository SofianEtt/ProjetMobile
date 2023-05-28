package com.androiddev.recruitmentsystem.Recruiter;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.androiddev.recruitmentsystem.Modules.PostModule;
import com.androiddev.recruitmentsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UpdateAdActivity extends AppCompatActivity {

    EditText name_box, timing_box, salary_box, skills_box,phone_box,location_box,education_box, desc_box, cregis_box;
    FrameLayout post_btn, addImage_btn;

    public static ImageView imageView;
    private static final int GET_IMAGE_REQUEST_CODE = 100;
    Uri imageFilePath;
    Bitmap imageToStore;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;
    private StorageReference reference = FirebaseStorage.getInstance().getReference();


    private ProgressBar progressBar;

    DatabaseReference databaseReference;
    ImageView back_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ad);

        name_box = findViewById(R.id.title_box);
        timing_box = findViewById(R.id.timing_box);
        salary_box = findViewById(R.id.salary_box);
        skills_box = findViewById(R.id.skills_box);
        education_box = findViewById(R.id.education_box);
        post_btn = findViewById(R.id.post_btn);
        phone_box = findViewById(R.id.phone_box);
        location_box = findViewById(R.id.location_box);
        desc_box = findViewById(R.id.descr_box);
        cregis_box = findViewById(R.id.cregisPost_box);
        addImage_btn = findViewById(R.id.addImage_layout);
        imageView = findViewById(R.id.crtImage_iv);
        progressBar = findViewById(R.id.progressbar);
        back_btn = findViewById(R.id.back_btn);


//
//        intent.putExtra("id",module.getId());
//        intent.putExtra("name", module.getName());
//        intent.putExtra("location",module.getLocation());
//        intent.putExtra("skills",module.getSubject());
//        intent.putExtra("education",module.getEducation());
//        intent.putExtra("salary",module.getPrice());
//        intent.putExtra("timing",module.getTime());
//        intent.putExtra("desc",module.getDescription());
//        intent.putExtra("cregis",module.getCregis());
//        intent.putExtra("image",module.getImage());

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        name_box.setText(intent.getStringExtra("name"));
        location_box.setText(intent.getStringExtra("location"));
        skills_box.setText(intent.getStringExtra("skills"));
        education_box.setText(intent.getStringExtra("education"));
        salary_box.setText(intent.getStringExtra("salary"));
        timing_box.setText(intent.getStringExtra("timing"));
        desc_box.setText(intent.getStringExtra("desc"));
        cregis_box.setText(intent.getStringExtra("cregis"));
        String imgg = intent.getStringExtra("image");

//        Picasso.with(UpdateAdActivity.this).load(imgg).placeholder(R.drawable.round_logo).into(imageView);




        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateAdActivity.super.onBackPressed();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference();

        addImage_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //code to Select image form the gallery of the phone
                // and set on the imageview
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, GET_IMAGE_REQUEST_CODE);
            }
        });

        SharedPreferences preferences = getSharedPreferences("USER",MODE_PRIVATE);
        String phone = preferences.getString("phone","");
        String name_s = preferences.getString("name","");
        phone_box.setText(phone);
//        name_box.setText(name_s);




        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = name_box.getText().toString();
                String location = location_box.getText().toString();
                String skills = skills_box.getText().toString();
                String education = education_box.getText().toString();
                String salary = salary_box.getText().toString();
                String timing = timing_box.getText().toString();
                String desc = desc_box.getText().toString();
                String cid = cregis_box.getText().toString();





                if (name.equals("")){
                    Toast.makeText(UpdateAdActivity.this, "Please Enter Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (skills.equals("")){
                    Toast.makeText(UpdateAdActivity.this, "Please Enter Subject", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (location.equals("")){
                    Toast.makeText(UpdateAdActivity.this, "Please Enter Location", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (education.equals("")){
                    Toast.makeText(UpdateAdActivity.this, "Please Enter Education", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (salary.equals("")){
                    Toast.makeText(UpdateAdActivity.this, "Please Enter Pricing", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (timing.equals("")){
                    Toast.makeText(UpdateAdActivity.this, "Please Enter Time", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (desc.equals("")){
                    Toast.makeText(UpdateAdActivity.this, "Please Enter Description", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (cid.equals("")){
                    Toast.makeText(UpdateAdActivity.this, "Please Enter Registration Number", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (imageView.getDrawable() == null) {
                    Toast.makeText(UpdateAdActivity.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                    return;
                }




                // code to store image in the Firebase Storage database
                final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(imageFilePath));
                fileRef.putFile(imageFilePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {




                                SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy HH:mm");
                                String currentTime = df.format(Calendar.getInstance().getTime());
                                // code to store ad data in the node of Firebase database
                                PostModule postModule = new PostModule(name,phone,skills,location,education,timing,salary,id,uri.toString(),currentTime,cid,desc,0,0);
                                databaseReference.child("ADS").child(id).setValue(postModule).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        Toast.makeText(UpdateAdActivity.this, "Posted Successfully", Toast.LENGTH_SHORT).show();
                                        progressBar.setVisibility(View.GONE);
                                        post_btn.setVisibility(View.VISIBLE);
                                        finish();

                                    }
                                });





                            }
                        });
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        progressBar.setVisibility(View.VISIBLE);
                        post_btn.setVisibility(View.GONE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        post_btn.setVisibility(View.VISIBLE);
                        Toast.makeText(UpdateAdActivity.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GET_IMAGE_REQUEST_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageFilePath = data.getData();
            try {
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                imageView.setImageBitmap(imageToStore);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private String getFileExtension(Uri mUri) {

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }
}