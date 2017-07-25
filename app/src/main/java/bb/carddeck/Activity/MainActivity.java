package bb.carddeck.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import bb.carddeck.R;
import bb.carddeck.ViewModel.MainAppViewModel;
import bb.carddeck.databinding.ActivityMainBinding;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainActivityBinding;
    MainAppViewModel mainAppViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
    }

    public void initDataBinding(){
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainAppViewModel = new MainAppViewModel(this);
        mainActivityBinding.setViewModel(mainAppViewModel);
    }
}
