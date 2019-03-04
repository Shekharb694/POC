package com.google.firebase.samples.apps.mlkit.java.barcodescanning;

import android.content.Context;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class FetchBarcodeDetails extends AsyncTask<Void ,Void,String> {

    Context context ;
    String baseURL ;
    String APIkey ;
    String barcode ;

    public FetchBarcodeDetails (Context context , String barcode)
    {
        this.baseURL = "https://api.barcodelookup.com/v2/products?barcode="+barcode+"&formatted=y&key=iu4g5txs8llwrsrjtwilfl0nkl9ve8" ;
        this.context = context ;
        this.barcode = barcode ;
    }

    @Override
    protected String doInBackground(Void... voids) {
        final String responseString = "null" ;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseURL ;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Display the first 500 characters of the response string.
                        String productName = "" ;
                        try
                        {
                            productName = response.getJSONArray("products").getJSONObject(0).getString("product_name");
                        }
                        catch (Exception e)
                        {
                            e.getMessage();
                        }

                        Toast.makeText(context , productName , Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context , error.toString() , Toast.LENGTH_LONG).show();
                    }
                });

        queue.add(jsonObjectRequest);

        return responseString ;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
        //response.showResponse(result);
    }
}
