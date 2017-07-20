package bb.carddeck.Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import bb.carddeck.API.DataManager;
import bb.carddeck.Adapter.CardAdapter;
import bb.carddeck.CardDeckApplication;
import bb.carddeck.Logic.Composition;
import bb.carddeck.Logic.InternetState;
import bb.carddeck.R;
import bb.carddeck.ViewModel.CardViewModel;
import bb.carddeck.databinding.DeckDashboardBinding;
import bb.carddeck.model.Card;
import bb.carddeck.model.CardList;
import bb.carddeck.model.Deck;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;


public class DeckDashboard extends ListActivity{

    @BindView(R.id.NumberOfRemainingCards)
    TextView NumOfRemCards;
    @BindView(R.id.NumDecks) TextView NumOfDecks;
    @BindView(R.id.ReshuffleButton) Button reshuffleButton;
    @BindView(R.id.Composition) TextView composition;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    CardViewModel cardViewModel;
    DeckDashboardBinding deckDashboardBinding;
    CardAdapter mCardAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deck_dashboard);
        ButterKnife.bind(this);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setupAdapter();
        initDataBinding();

    }
    private void initDataBinding() {
        deckDashboardBinding = DataBindingUtil.setContentView(this, R.layout.deck_dashboard);
        cardViewModel = new CardViewModel(this, mCardAdapter); // TODO Inject adapter to ViewModel.
        deckDashboardBinding.setViewModel(cardViewModel);
    }

    private void setupAdapter(){
        mCardAdapter = new CardAdapter(this, new ArrayList<Card>());
        setListAdapter(mCardAdapter);
    }


}
