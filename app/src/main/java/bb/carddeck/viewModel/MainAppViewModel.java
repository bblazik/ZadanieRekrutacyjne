package bb.carddeck.viewModel;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.NumberPicker;

import bb.carddeck.view.Activity.DeckDashboard;

public class MainAppViewModel extends BaseObservable{

    Context mContext;
    public final Integer min = 1;
    public final Integer max = 5;
    public static Integer pickerValue = 1;

    public MainAppViewModel(Context context){
        mContext = context;
    }

    @BindingAdapter({"min", "max"})
    public static void setOptions(NumberPicker numberPicker, Integer min, Integer max){
        numberPicker.setMinValue(min);
        numberPicker.setMaxValue(max);
        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                pickerValue = newVal;
            }
        });
    }

    public OnClickListener goToDashboard = new OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, DeckDashboard.class);
            intent.putExtra("numberOfDecks", pickerValue);
            mContext.startActivity(intent);
        }
    };

}
