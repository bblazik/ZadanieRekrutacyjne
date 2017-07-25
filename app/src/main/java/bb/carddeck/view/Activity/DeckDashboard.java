package bb.carddeck.view.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import bb.carddeck.view.Adapter.CardAdapter;
import bb.carddeck.R;
import bb.carddeck.ViewModel.CardViewModel;
import bb.carddeck.databinding.DeckDashboardBinding;


public class DeckDashboard extends AppCompatActivity {

    CardViewModel cardViewModel;
    DeckDashboardBinding deckDashboardBinding;
    CardAdapter mCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }
    private void initDataBinding() {
        deckDashboardBinding = DataBindingUtil.setContentView(this, R.layout.deck_dashboard);
        setupAdapter();
        cardViewModel = new CardViewModel(this,
                mCardAdapter,
                getIntent().getIntExtra("numberOfDecks", 1));
        deckDashboardBinding.setViewModel(cardViewModel);
    }

    private void setupAdapter(){
        mCardAdapter= new CardAdapter();
        deckDashboardBinding.list.setAdapter(mCardAdapter);
        deckDashboardBinding.list.setLayoutManager(new LinearLayoutManager(this));
    }
}
