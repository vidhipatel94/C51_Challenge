package com.c51.c51challenge;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.c51.c51challenge.dao.OffersDao;
import com.c51.c51challenge.databinding.ActivityMainBinding;
import com.c51.c51challenge.model.Offer;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private OffersAdapter adapter;
    private List<Offer> offers = new ArrayList<>();

    protected CompositeDisposable subscriptions = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        fetchOffers();

        initView();
    }

    private void fetchOffers() {
        subscriptions.add(OffersDao.getOffers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    if (response.getOffers() != null) {
                        offers.addAll(response.getOffers());
                        if (adapter != null) {
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        showMessage(binding.getRoot(), getString(R.string.msg_empty_offers));
                    }
                }, throwable -> {
                    binding.progressBar.setVisibility(View.INVISIBLE);
                    showMessage(binding.getRoot(), getString(R.string.msg_api_error));
                }));
    }

    private void showMessage(View rootView, String message) {
        Snackbar snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    private void initView() {
        adapter = new OffersAdapter(offers);
        binding.offers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.offers.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_sort_by_name) {
            sortByName();
            return true;
        } else if (item.getItemId() == R.id.action_sort_by_cashback) {
            sortByCashBack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortByName() {
        Collections.sort(offers, new Comparator<Offer>() {
            @Override
            public int compare(Offer offer1, Offer offer2) {
                return offer1.getName().compareTo(offer2.getName());
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void sortByCashBack() {
        Collections.sort(offers, new Comparator<Offer>() {
            @Override
            public int compare(Offer offer1, Offer offer2) {
                return (int) offer1.getCashBack() - (int) offer2.getCashBack();
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        subscriptions.clear();
        super.onDestroy();
    }
}