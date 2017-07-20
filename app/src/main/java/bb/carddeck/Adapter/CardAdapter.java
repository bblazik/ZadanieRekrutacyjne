package bb.carddeck.Adapter;


import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bb.carddeck.R;
import bb.carddeck.ViewModel.CardViewModel;
import bb.carddeck.databinding.DeckDashboardBinding;
import bb.carddeck.model.Card;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.BindingHolder> {
    private List<Card> mCards;
    private Context mContext;

    public CardAdapter(Context context){
        mContext = context;
        mCards = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DeckDashboardBinding cardBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.deck_dashboard,
                parent,
                false
        );
        return new BindingHolder(cardBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        DeckDashboardBinding cardBinding = holder.binding;
        cardBinding.setViewModel(new CardViewModel(mContext, mCards.get(position)));
    }

    @Override
    public int getItemCount() {
        return mCards.size();
    }

    public void setItems(List<Card> cards){
        mCards = cards;
        notifyDataSetChanged();
    }

    public void addItem(Card card) {
        if (!mCards.contains(card)) {
            mCards.add(card);
            notifyItemInserted(mCards.size() - 1);
        } else {
            mCards.set(mCards.indexOf(card), card);
            notifyItemChanged(mCards.indexOf(card));
        }
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private DeckDashboardBinding binding;

        public BindingHolder(DeckDashboardBinding binding) {
            super(binding.list);
            this.binding = binding;
        }
    }
}

