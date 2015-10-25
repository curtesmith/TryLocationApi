package ca.brocku.cosc3p97.cs97aa.trylocationapi;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity implements LocationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupLocationManager();
    }


    protected void setupLocationManager() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

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

    private void setTextView(int id, String text) {
        TextView textView = (TextView) findViewById(id);
        textView.setText(text);
    }

    @Override
    public void onLocationChanged(Location location) {
        setTextView(R.id.latitude, "latitude: " + Double.toString(location.getLatitude()));
        setTextView(R.id.longitude, "longitude: " + Double.toString(location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        setTextView(R.id.status, "Provider:" + provider + ", status=" + Integer.toString(status));
    }

    @Override
    public void onProviderEnabled(String provider) {
        setTextView(R.id.providerEnabled, "Provider:" + provider + " is enabled");
    }

    @Override
    public void onProviderDisabled(String provider) {
        setTextView(R.id.providerEnabled, "Provider:" + provider + " is disabled");
    }
}
