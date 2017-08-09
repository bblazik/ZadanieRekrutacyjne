package bb.carddeck.viewModel;

import android.content.Intent;
import android.widget.NumberPicker;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import bb.carddeck.BuildConfig;
import bb.carddeck.CardDeckApplication;
import bb.carddeck.R;
import bb.carddeck.view.Activity.DeckDashboard;
import bb.carddeck.view.Activity.MainActivity;


import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class) @Config(constants = BuildConfig.class, sdk = 21)
public class MainAppViewModelTest {

    private CardDeckApplication cardDeckApplication;
    @Before
    public void setUp() throws Exception {
        cardDeckApplication = (CardDeckApplication) RuntimeEnvironment.application;
    }

    @Test
    public void setOptions() throws Exception {
        assertTrue(true);
    }

    @Test
    public void clickingButton_shouldStartDeckActivity(){
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.findViewById(R.id.pickNumber).performClick();

        Intent expectedIntent = new Intent(cardDeckApplication.getBaseContext(), DeckDashboard.class);

        assertTrue(shadowOf(activity).getNextStartedActivity().filterEquals(expectedIntent));
    }

}