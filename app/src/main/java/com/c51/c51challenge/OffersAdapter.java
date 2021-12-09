package com.c51.c51challenge;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.c51.c51challenge.databinding.ListItemOfferBinding;
import com.c51.c51challenge.model.Offer;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.ViewHolder> {

    private final List<Offer> offers;

    public OffersAdapter(List<Offer> offers) {
        this.offers = offers;
    }

    @NotNull
    @Override
    public OffersAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        return new ViewHolder(ListItemOfferBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NotNull OffersAdapter.ViewHolder holder, int position) {
        Offer offer = offers.get(position);

        holder.binding.setOffer(offer);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return offers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ListItemOfferBinding binding;

        public ViewHolder(@NotNull ListItemOfferBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
