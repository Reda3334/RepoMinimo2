package com.example.proyectodsa_android.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectodsa_android.ApiService;
import com.example.proyectodsa_android.R;
import com.example.proyectodsa_android.RetrofitClient;
import com.example.proyectodsa_android.adapter.BadgeAdapter;
import com.example.proyectodsa_android.models.Badge;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BadgesActivity extends AppCompatActivity {
    private BadgeAdapter badgeAdapter;
    private ApiService apiService;
    private Button btnFetchBadges;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_badges);
        setContentView(R.layout.activity_home);


        RecyclerView rvBadges = findViewById(R.id.rvBadges);
        rvBadges.setLayoutManager(new LinearLayoutManager(this));
        badgeAdapter = new BadgeAdapter();
        rvBadges.setAdapter(badgeAdapter);

        apiService = RetrofitClient.getInstance().getApi();

        Button btnFetchBadges = findViewById(R.id.btnFetchBadges);
        btnFetchBadges.setOnClickListener(v -> {
            String userId = getIntent().getStringExtra("username");
            if (userId != null) {
                fetchBadges(userId);
            } else {
                Toast.makeText(this, "User ID not provided", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchBadges(String username) {
        apiService.getUserBadges(username).enqueue(new Callback<List<Badge>>() {
            @Override
            public void onResponse(Call<List<Badge>> call, Response<List<Badge>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Badge> badges = response.body();

                    for (Badge badge : badges) {
                        badge.setImageUrl("https://cdn.pixabay.com/photo/2017/07/11/15/51/kermit-2493979_1280.png");
                    }
                    badgeAdapter.setBadges(badges);
                } else {
                    Toast.makeText(BadgesActivity.this, "Failed to load badges", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<List<Badge>> call, Throwable t) {
                Toast.makeText(BadgesActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}