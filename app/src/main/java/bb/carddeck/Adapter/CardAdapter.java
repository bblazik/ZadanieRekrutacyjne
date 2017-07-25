package bb.carddeck.Adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import bb.carddeck.R;
import bb.carddeck.ViewModel.CardItemViewModel;
import bb.carddeck.databinding.CardRowBinding;
import bb.carddeck.model.Card;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {
    private  List<Card> list = new ArrayList<>();

    public void setCardList(List<Card> peopleList) {
        this.list = peopleList;
        notifyDataSetChanged();
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardRowBinding cardRowBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_row,
                        parent, false);
        return new CardViewHolder(cardRowBinding);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.bindCard(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        CardRowBinding mCardRowBinding;

        public CardViewHolder(CardRowBinding cardRowBinding) {
            super(cardRowBinding.cardRow);
            this.mCardRowBinding = cardRowBinding;
        }

        void bindCard(Card card) {
            if (mCardRowBinding.getViewModel() == null) {
                mCardRowBinding.setViewModel(
                        new CardItemViewModel(card, itemView.getContext()));
            } else {
                mCardRowBinding.getViewModel().setCard(card);
            }
        }
    }
}
