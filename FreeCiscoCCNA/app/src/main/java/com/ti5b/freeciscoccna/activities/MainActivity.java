package com.ti5b.freeciscoccna.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.ti5b.freeciscoccna.models.ValueNoData;
import com.ti5b.freeciscoccna.services.APIService;
import com.ti5b.freeciscoccna.models.Post;
import com.ti5b.freeciscoccna.adapters.PostViewAdaptor;
import com.ti5b.freeciscoccna.R;
import com.ti5b.freeciscoccna.utility.Utilities;
import com.ti5b.freeciscoccna.models.ValueData;

import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PostViewAdaptor.OnItemLongClicklistener {
    private FirebaseAuth firebaseAuth;
    private RecyclerView rvPost;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context mContext;
    private List<Post> data;
    private PostViewAdaptor postViewAdaptor;

    FloatingActionButton fab, fabCreate, fabEdit;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;

    private PostViewAdaptor.OnItemLongClicklistener onItemLongClicklistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setBackgroundDrawableResource(R.drawable.gradient_statusbar);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        if (!Utilities.checkValue(MainActivity.this, "xEmail")) {
            startActivity(new Intent(MainActivity.this, SignInActivity.class));
            finish();
        }

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }


        rvPost = findViewById(R.id.rv_post);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        rvPost.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPost();
                if (data == null) {
                    setRecylerView();
                    return;
                }
            }
        });


        fab = findViewById(R.id.fab_addpost);
        fabCreate = findViewById(R.id.fab_create);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
            }
        });

        fabCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();
                Intent intent = new Intent(MainActivity.this, AddPostActivity.class);
                startActivity(intent);
            }
        });
    }

    private void animateFab(){
        if (isOpen){
            fab.startAnimation(rotateForward);
            fabCreate.startAnimation(fabClose);
            fabCreate.setClickable(false);
            isOpen=false;
        } else {
            fab.startAnimation(rotateBackward);
            fabCreate.startAnimation(fabOpen);
            fabCreate.setClickable(true);
            isOpen=true;
        }
    }

    private void setRecylerView(){
        PostViewAdaptor postViewAdaptor = new PostViewAdaptor(mContext,data, onItemLongClicklistener );
        rvPost.setAdapter(postViewAdaptor);
        rvPost.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPost();
    }

    private void getPost() {
        swipeRefreshLayout.setRefreshing(true);
        APIService api = Utilities.getmRetrofit().create(APIService.class);
        Call<ValueData<Post>> call = api.getPost("freeciscoccna");
        call.enqueue(new Callback<ValueData<Post>>() {
            @Override
            public void onResponse(Call<ValueData<Post>> call, Response<ValueData<Post>> response) {
                if (response.code() == 200) {
                    swipeRefreshLayout.setRefreshing(false);
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {

                        StyleableToast.makeText(MainActivity.this, message, R.style.customtoastscucess).show();
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        data = response.body().getData();
                        postViewAdaptor = new PostViewAdaptor(MainActivity.this, data, MainActivity.this);
                        rvPost.setAdapter(postViewAdaptor);
                    } else {
                        StyleableToast.makeText(MainActivity.this, message, R.style.customtoastfail).show();
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    StyleableToast.makeText(MainActivity.this, "Response" + response.code(), R.style.customtoastfail).show();
//                    Toast.makeText(MainActivity.this, "Response" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<Post>> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                System.out.println("Retrofit Error : " + t.getMessage());
                StyleableToast.makeText(MainActivity.this, "Retrofit Error : " + t.getMessage(), R.style.customtoastfail).show();
//                Toast.makeText(MainActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_signout) {
            firebaseAuth.signOut();
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemLongClick(View v, int position) {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
        popupMenu.inflate(R.menu.menu_popup);
        popupMenu.setGravity(Gravity.RIGHT);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_edit:
                        Intent intent = new Intent(MainActivity.this, UpdatePostActivity.class);
                        intent.putExtra("EXTRA_DATA", data.get(position));
                        startActivity(intent);
                        return true;
                    case  R.id.action_delete:
                        int id = data.get(position).getId();
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialogBuilder.setTitle("Confirm");
                        alertDialogBuilder.setMessage("Are you sure to delete this post " + data.get(position).getHeading_main() + " ?");
                        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deletePost(id);
                            }
                        });
                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }

    private void deletePost(int id) {
        APIService api = Utilities.getmRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.deletePost("freeciscoccna", id);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        StyleableToast.makeText(MainActivity.this, message, R.style.customtoastscucess).show();
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        getPost();
                    } else {
                        StyleableToast.makeText(MainActivity.this, message, R.style.customtoastfail).show();
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    StyleableToast.makeText(MainActivity.this, "Response" + response.code(), R.style.customtoastfail).show();
//                    Toast.makeText(MainActivity.this, "Response" + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                System.out.println("Retrofit Error : " + t.getMessage());
                StyleableToast.makeText(MainActivity.this, "Retrofit Error : " + t.getMessage(), R.style.customtoastfail).show();
//                Toast.makeText(MainActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}