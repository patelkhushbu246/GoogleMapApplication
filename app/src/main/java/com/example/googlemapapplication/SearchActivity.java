package com.example.googlemapapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class SearchActivity extends FragmentActivity implements OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    SearchView search_View;
    GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_View=findViewById(R.id.searchview);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.search_loc);
        search_View.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String findlocation=search_View.getQuery().toString();
                List<Address> list=null;
                if (findlocation!=null || !findlocation.equals(""))
                {
                    Geocoder geocoder=new Geocoder(SearchActivity.this);
                    try {
                        list=geocoder.getFromLocationName(findlocation,1);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                    Address address=list.get(0);
                    LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(findlocation));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

    }
}