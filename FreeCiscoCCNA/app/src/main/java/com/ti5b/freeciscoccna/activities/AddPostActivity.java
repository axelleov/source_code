package com.ti5b.freeciscoccna.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ti5b.freeciscoccna.R;
import com.ti5b.freeciscoccna.adapters.PostViewAdaptor;
import com.ti5b.freeciscoccna.models.ValueNoData;
import com.ti5b.freeciscoccna.services.APIService;
import com.ti5b.freeciscoccna.utility.Utilities;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPostActivity extends AppCompatActivity {
    private EditText etImageMain, etHeadingMain;
    private EditText etHeading1, etHeading2, etHeading3, etHeading4, etHeading5;
    private EditText etH1Paragraph1, etH2Paragraph1, etH3Paragraph1, etH4Paragraph1, etH5Paragraph1;
    private EditText etH1Paragraph2, etH2Paragraph2, etH3Paragraph2, etH4Paragraph2, etH5Paragraph2;
    private EditText etH1Image, etH2Image, etH3Image, etH4Image, etH5Image;
    private FloatingActionButton fabSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient_statusbar);
        setContentView(R.layout.activity_add_post);

        Toolbar toolbarAdd = findViewById(R.id.toolbar_addpost);
        toolbarAdd.setTitle("Add Content");
        setSupportActionBar(toolbarAdd);

        etImageMain = findViewById(R.id.et_image_main);
        etHeadingMain = findViewById(R.id.et_heading_main);

        etHeading1 = findViewById(R.id.et_heading1);
        etHeading2 = findViewById(R.id.et_heading2);
        etHeading3 = findViewById(R.id.et_heading3);
        etHeading4 = findViewById(R.id.et_heading4);
        etHeading5 = findViewById(R.id.et_heading5);

        etH1Paragraph1 = findViewById(R.id.et_h1_paragraph1);
        etH2Paragraph1 = findViewById(R.id.et_h2_paragraph1);
        etH3Paragraph1 = findViewById(R.id.et_h3_paragraph1);
        etH4Paragraph1 = findViewById(R.id.et_h4_paragraph1);
        etH5Paragraph1 = findViewById(R.id.et_h5_paragraph1);

        etH1Paragraph2 = findViewById(R.id.et_h1_paragraph2);
        etH2Paragraph2 = findViewById(R.id.et_h2_paragraph2);
        etH3Paragraph2 = findViewById(R.id.et_h3_paragraph2);
        etH4Paragraph2 = findViewById(R.id.et_h4_paragraph2);
        etH5Paragraph2 = findViewById(R.id.et_h5_paragraph2);

        etH1Image = findViewById(R.id.et_h1_image);
        etH2Image = findViewById(R.id.et_h2_image);
        etH3Image = findViewById(R.id.et_h3_image);
        etH4Image = findViewById(R.id.et_h4_image);
        etH5Image = findViewById(R.id.et_h5_image);

        fabSave = findViewById(R.id.fab_save);

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String imageMain = etImageMain.getText().toString();
                String headingMain = etHeadingMain.getText().toString();

                String heading1 = etHeading1.getText().toString();
                String heading2 = etHeading2.getText().toString();
                String heading3 = etHeading3.getText().toString();
                String heading4 = etHeading4.getText().toString();
                String heading5 = etHeading5.getText().toString();

                String h1Paragraph1 = etH1Paragraph1.getText().toString();
                String h2Paragraph1 = etH2Paragraph1.getText().toString();
                String h3Paragraph1 = etH3Paragraph1.getText().toString();
                String h4Paragraph1 = etH4Paragraph1.getText().toString();
                String h5Paragraph1 = etH5Paragraph1.getText().toString();

                String h1Paragraph2 = etH1Paragraph2.getText().toString();
                String h2Paragraph2 = etH2Paragraph2.getText().toString();
                String h3Paragraph2 = etH3Paragraph2.getText().toString();
                String h4Paragraph2 = etH4Paragraph2.getText().toString();
                String h5Paragraph2 = etH5Paragraph2.getText().toString();

                String h1Image = etH1Image.getText().toString();
                String h2Image = etH2Image.getText().toString();
                String h3Image = etH3Image.getText().toString();
                String h4Image = etH4Image.getText().toString();
                String h5Image = etH5Image.getText().toString();

                boolean bolehPost = true;

                if (TextUtils.isEmpty(imageMain)) {
                    bolehPost = false;
                    etImageMain.setError("Image main can't be empty !");
                }
                if (TextUtils.isEmpty(headingMain)) {
                    bolehPost = false;
                    etHeadingMain.setError("Heading main can't be empty !");
                }

                if (bolehPost) {
                    addPost(Utilities.getValue(AddPostActivity.this, "xEmail"), imageMain, headingMain,
                            heading1, heading2, heading3, heading4, heading5, h1Paragraph1, h2Paragraph1,
                            h3Paragraph1, h4Paragraph1, h5Paragraph1, h1Paragraph2, h2Paragraph2, h3Paragraph2,
                            h4Paragraph2, h5Paragraph2, h1Image, h2Image, h3Image, h4Image, h5Image);
                }
            }
        });

    }

    private void addPost(String email, String imageMain, String headingMain, String heading1, String heading2, String heading3, String heading4, String heading5, String h1Paragraph1, String h2Paragraph1, String h3Paragraph1, String h4Paragraph1, String h5Paragraph1, String h1Paragraph2, String h2Paragraph2, String h3Paragraph2, String h4Paragraph2, String h5Paragraph2, String h1Image, String h2Image, String h3Image, String h4Image, String h5Image) {
        APIService api = Utilities.getmRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.addPost("freeciscoccna", email, imageMain, headingMain,
                heading1, heading2, heading3, heading4, heading5, h1Paragraph1, h2Paragraph1,
                h3Paragraph1, h4Paragraph1, h5Paragraph1, h1Paragraph2, h2Paragraph2, h3Paragraph2,
                h4Paragraph2, h5Paragraph2, h1Image, h2Image, h3Image, h4Image, h5Image);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        StyleableToast.makeText(AddPostActivity.this, message, R.style.customtoastscucess).show();
//                        Toast.makeText(AddPostActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        StyleableToast.makeText(AddPostActivity.this, message, R.style.customtoastfail).show();
//                        Toast.makeText(AddPostActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    StyleableToast.makeText(AddPostActivity.this, "Response" + response.code(), R.style.customtoastfail).show();
//                    Toast.makeText(AddPostActivity.this, "Response" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                System.out.println("Retrofit Error : " + t.getMessage());
                StyleableToast.makeText(AddPostActivity.this, "Retrofit Error : " + t.getMessage(), R.style.customtoastfail).show();
//                Toast.makeText(AddPostActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}