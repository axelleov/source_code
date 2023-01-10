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
import com.ti5b.freeciscoccna.models.Post;
import com.ti5b.freeciscoccna.models.ValueNoData;
import com.ti5b.freeciscoccna.services.APIService;
import com.ti5b.freeciscoccna.utility.Utilities;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePostActivity extends AppCompatActivity {
    private EditText etImageMain, etHeadingMain;
    private EditText etHeading1, etHeading2, etHeading3, etHeading4, etHeading5;
    private EditText etH1Paragraph1, etH2Paragraph1, etH3Paragraph1, etH4Paragraph1, etH5Paragraph1;
    private EditText etH1Paragraph2, etH2Paragraph2, etH3Paragraph2, etH4Paragraph2, etH5Paragraph2;
    private EditText etH1Image, etH2Image, etH3Image, etH4Image, etH5Image;
    private FloatingActionButton fabUpdate;
    private Post mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient_statusbar);
        setContentView(R.layout.activity_update_post);

        Toolbar toolbarAdd = findViewById(R.id.toolbar_addpost);
        toolbarAdd.setTitle("Update Content");
        setSupportActionBar(toolbarAdd);

        mPost = getIntent().getParcelableExtra("EXTRA_DATA");
        int id = mPost.getId();

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

        fabUpdate = findViewById(R.id.fab_update);

        etImageMain.setText(mPost.getImage_main());
        etHeadingMain.setText(mPost.getHeading_main());

        etHeading1.setText(mPost.getHeading1());
        etHeading2.setText(mPost.getHeading2());
        etHeading3.setText(mPost.getHeading3());
        etHeading4.setText(mPost.getHeading4());
        etHeading5.setText(mPost.getHeading5());

        etH1Paragraph1.setText(mPost.getH1_paragraph1());
        etH5Paragraph1.setText(mPost.getH5_paragraph1());
        etH2Paragraph1.setText(mPost.getH2_paragraph1());
        etH3Paragraph1.setText(mPost.getH3_paragraph1());
        etH4Paragraph1.setText(mPost.getH4_paragraph1());

        etH1Paragraph2.setText(mPost.getH1_paragraph2());
        etH5Paragraph2.setText(mPost.getH5_paragraph2());
        etH2Paragraph2.setText(mPost.getH2_paragraph2());
        etH3Paragraph2.setText(mPost.getH3_paragraph2());
        etH4Paragraph2.setText(mPost.getH4_paragraph2());

        etH1Image.setText(mPost.getH1_image());
        etH2Image.setText(mPost.getH2_image());
        etH3Image.setText(mPost.getH3_image());
        etH4Image.setText(mPost.getH4_image());
        etH5Image.setText(mPost.getH5_image());


        fabUpdate.setOnClickListener(new View.OnClickListener() {
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

                boolean bolehUpdatePost = true;

                if (TextUtils.isEmpty(imageMain)) {
                    bolehUpdatePost = false;
                    etImageMain.setError("Image main can't be empty !");
                }
                if (TextUtils.isEmpty(headingMain)) {
                    bolehUpdatePost = false;
                    etHeadingMain.setError("Heading main can't be empty !");
                }

                if (bolehUpdatePost) {
                    addPost(id, imageMain, headingMain,
                            heading1, heading2, heading3, heading4, heading5, h1Paragraph1, h2Paragraph1,
                            h3Paragraph1, h4Paragraph1, h5Paragraph1, h1Paragraph2, h2Paragraph2, h3Paragraph2,
                            h4Paragraph2, h5Paragraph2, h1Image, h2Image, h3Image, h4Image, h5Image);
                }
            }
        });

    }

    private void addPost(int id, String imageMain, String headingMain, String heading1, String heading2, String heading3, String heading4, String heading5, String h1Paragraph1, String h2Paragraph1, String h3Paragraph1, String h4Paragraph1, String h5Paragraph1, String h1Paragraph2, String h2Paragraph2, String h3Paragraph2, String h4Paragraph2, String h5Paragraph2, String h1Image, String h2Image, String h3Image, String h4Image, String h5Image) {
        APIService api = Utilities.getmRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.updatePost("freeciscoccna", id, imageMain, headingMain,
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
                        StyleableToast.makeText(UpdatePostActivity.this, message, R.style.customtoastscucess).show();
//                        Toast.makeText(UpdatePostActivity.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        StyleableToast.makeText(UpdatePostActivity.this, message, R.style.customtoastfail).show();
//                        Toast.makeText(UpdatePostActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    StyleableToast.makeText(UpdatePostActivity.this, "Response" + response.code(), R.style.customtoastfail).show();
//                    Toast.makeText(UpdatePostActivity.this, "Response" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                System.out.println("Retrofit Error : " + t.getMessage());
                StyleableToast.makeText(UpdatePostActivity.this, "Retrofit Error : " + t.getMessage(), R.style.customtoastfail).show();
//                Toast.makeText(UpdatePostActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}