package com.telenor.herokuaapp.view;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.telenor.herokuaapp.R;
import com.telenor.herokuaapp.adapter.FilmsAdapter;
import com.telenor.herokuaapp.controller.RetrofitController;
import com.telenor.herokuaapp.model.Film;
import com.telenor.herokuaapp.utils.AlertOp;
import com.telenor.herokuaapp.utils.NetworkUtils;
import com.telenor.herokuaapp.utils.ToolbarOp;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by nofal bin idrees on 2/17/2019.
 */
public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.srl_films)
    SwipeRefreshLayout srl_films;
    @BindView(R.id.rv_films)
    RecyclerView rv_films;
    Context context;
    private FilmsAdapter filmsAdapter;
    @BindView(R.id.tv_emptyresult)
    TextView tv_emptyresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        context = HomeActivity.this;
        srl_films.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary);
        srl_films.post(new Runnable() {
            @Override
            public void run() {
                fetchFilms();
            }
        });
        srl_films.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchFilms();
            }
        });
        setToolbar();

    }

    private void setToolbar() {

        toolbar.removeAllViews();
        toolbar.setContentInsetsAbsolute(0, 0);
        toolbar.setTitleTextColor(Color.WHITE);
        ToolbarOp.addTitle(toolbar, context, getString(R.string.home_title), ToolbarOp.Theme.Dark, true);
        // toolbar.setBackgroundColor(getResources().getColor(R.color.transparent));

    }

    private void fetchFilms() {
        if (NetworkUtils.isNetworkConnected(context, true)) {
            startRefreshing();
            RetrofitController.getInstance().GetAllFilms().enqueue(new Callback<List<Film>>() {
                @Override
                public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                    if (response.isSuccessful()) {
                        List<Film> filmList = response.body();

                        srl_films.setRefreshing(false);

                        if (filmList.size() > 0) {
                            filmsAdapter = new FilmsAdapter(filmList, context);
                            LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                            rv_films.setLayoutManager(layoutManager);
                            rv_films.setAdapter(filmsAdapter);
                            tv_emptyresult.setVisibility(View.GONE);


                        }else{
                            tv_emptyresult.setVisibility(View.VISIBLE);
                        }
                    } else {
                        showError(response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Film>> call, Throwable t) {
                    stopRefreshing();
                }
            });
        }else{
            stopRefreshing();
        }


    }

    private void showError(String message) {
        AlertOp.showSnackbar(context, message, false);
    }

    private void stopRefreshing() {
        if (srl_films != null && srl_films.isRefreshing()) {
            srl_films.setRefreshing(false);
        }
    }

    private void startRefreshing() {
        if (srl_films != null && srl_films.isRefreshing()) {
            srl_films.setRefreshing(true);
        }
    }

    @Override
    public void onBackPressed() {
        AlertOp.showAlert(context, null, getString(R.string.exit_msg), getString(R.string.yes), getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
    }
}
