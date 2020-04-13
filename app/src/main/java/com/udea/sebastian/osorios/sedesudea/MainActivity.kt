package com.udea.sebastian.osorios.sedesudea

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.location.LocationManager
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : FragmentActivity(), OnMapReadyCallback{

    lateinit var spinnerSedes : Spinner
    private lateinit var mMap: GoogleMap
    private lateinit var sedeName : String
    companion object{
        private const val LOCATION_PERMIOSSION_REQUEST_CODE = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        spinnerSedes = findViewById(R.id.spinnerSedes)
        val sedesUdeA: SedesUdeA = SedesUdeA()
        var sedes: Array<String> = sedesUdeA.getNameLocation()
        var arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(this, R.layout.spinner_locations, sedes)
        spinnerSedes.setAdapter(arrayAdapter)
        sedeName = "UdeA Ciudad Universitaria"

        spinnerSedes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                Toast.makeText(this@MainActivity,getString(R.string.seleccionar_sede) + " "+spinnerSedes.getSelectedItem().toString(),Toast.LENGTH_SHORT).show()
                sedeName = spinnerSedes.getSelectedItem().toString()
                mMap.clear()
                onMapReady(mMap)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        var locationManager : LocationManager
        var sedesUdeA : SedesUdeA = SedesUdeA()
        var positions : Array<Double> = sedesUdeA.getPosition(sedeName)
        val udea = LatLng(positions.get(0),positions.get(1))
        mMap.addMarker(MarkerOptions().position(udea).title(sedeName))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(udea))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(udea,15F))
        if(ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) !=  PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMIOSSION_REQUEST_CODE
            )
            return
        }
        mMap.isMyLocationEnabled = true
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var location : Location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        var lat = location.latitude
        var long = location.longitude
        val myPosition = LatLng(lat,long)
        mMap.addMarker(MarkerOptions().position(myPosition).title("YO"))
        mMap.addPolyline( PolylineOptions()
            .add(LatLng(lat,long),LatLng(positions.get(0),positions.get(1)))
            .width(5F).color(
            Color.BLUE)
        )



    }
}