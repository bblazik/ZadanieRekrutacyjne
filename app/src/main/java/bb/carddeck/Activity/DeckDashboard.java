package bb.carddeck.Activity;

import android.app.ListActivity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import bb.carddeck.Adapter.CardAdapter;
import bb.carddeck.R;
import bb.carddeck.ViewModel.CardViewModel;
import bb.carddeck.databinding.DeckDashboardBinding;
import bb.carddeck.model.Card;



public class DeckDashboard extends AppCompatActivity{

    CardViewModel cardViewModel;
    DeckDashboardBinding deckDashboardBinding;
    CardAdapter mCardAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.deck_dashboard);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        initDataBinding();


    }
    private void initDataBinding() {
        deckDashboardBinding = DataBindingUtil.setContentView(this, R.layout.deck_dashboard);
        setupAdapter();
        cardViewModel = new CardViewModel(this, mCardAdapter); // TODO Inject adapter to ViewModel.
        deckDashboardBinding.setViewModel(cardViewModel);
    }

    private void setupAdapter(){
        mCardAdapter= new CardAdapter();
        deckDashboardBinding.list.setAdapter(mCardAdapter);
        deckDashboardBinding.list.setLayoutManager(new LinearLayoutManager(this));
    }


}
