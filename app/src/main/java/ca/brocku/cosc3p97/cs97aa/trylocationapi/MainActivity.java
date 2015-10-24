package ca.brocku.cosc3p97.cs97aa.trylocationapi;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends Activity implements ConnectionCallbacks, OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        GoogleApiClient.Builder builder = new GoogleApiClient.Builder(this);
        builder.addConnectionCallbacks(this);
        builder.addOnConnectionFailedListener(this);
        builder.addApi(LocationServices.API);
        mGoogleApiClient = builder.build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void makeToast(String text) {
        Toast toast = Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT);
        toast.show();
    }


    @Override
    public void onConnected(Bundle bundle) {
        TextView text = (TextView) findViewById(R.id.mainText);
        text.setText("onConnected called");
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLastLocation != null) {
            String lattitude  = String.valueOf(mLastLocation.getLatitude());
            String longitude = String.valueOf(mLastLocation.getLongitude());
            makeToast("longitude:" + longitude + ", lattitude:" + lattitude);
        } else {
            makeToast("Connected with no location to show");
        }

    }

    @Override
    public void onConnectionSuspended(int i) {
        makeToast("Connection suspended");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        makeToast("Connection failed");
    }
}
