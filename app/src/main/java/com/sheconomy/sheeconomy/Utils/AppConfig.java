package com.sheconomy.sheeconomy.Utils;
import android.content.Context;
import com.sheconomy.sheeconomy.Models.AppSettings;
import com.sheconomy.sheeconomy.Presentation.ui.activities.impl.MainActivity;

import java.text.DecimalFormat;


public class AppConfig  {
    public static AppSettings appSettings;

//    public static String BASE_URL = "https://sheconomy.in/api/v1/";
//    public static String ASSET_URL = "https://sheconomy.in/public/";

    public static String BASE_URL = "http://172.15.5.53/api/v1/";
    public static String ASSET_URL = "http://172.15.5.53/public/";

//    public static String BASE_URL = "http://192.168.0.112/api/v1/";
//    public static String ASSET_URL = "http://192.168.0.112/public/";

//    public static String BASE_URL = "http://172.15.5.169/api/v1/";
//    public static String ASSET_URL = "http://172.15.5.169/public/";

//    public static String BASE_URL = "http://192.168.0.186/api/v1/";
//    public static String ASSET_URL = "http://192.168.0.186/public/";

//    public static String BASE_URL = "http://192.168.0.129/shop/api/v1/";
//    public static String ASSET_URL = "http://192.168.0.129/shop/public/";

    public static String STRIPE_KEY = "pk_test_c6VvBEbwHFdulFZ62q1IQrar";
    public static String BRAINTREE_KEY = "sandbox_pghddbzc_h44cx45wt7g27wmc";
    public static String RAZORPAY_KEY = "rzp_live_geFvStRedSs5WV";
    public static boolean CASH_ON_DELIVERY = true;
    public static boolean WALLET_USE = true;

    public static String convertPrice(Context context, Double price) {
//        String country = "India";
//        if(country == "India") {
//            appSettings = new UserPrefs(context).getAppSettingsPreferenceObjectJson("app_settings_response").getData().get(0);
//            return "Rs" + new DecimalFormat("#,###.00").format(Double.parseDouble(String.valueOf(price * 73)));
//        }else{
            appSettings = new UserPrefs(context).getAppSettingsPreferenceObjectJson("app_settings_response").getData().get(0);
            return appSettings.getCurrency().getSymbol() + new DecimalFormat("#,###.00").format(Double.parseDouble(String.valueOf(price * appSettings.getCurrency().getExchangeRate())));
//        }
    }

    public static AppSettings getAppSettings(Context context){
        return new UserPrefs(context).getAppSettingsPreferenceObjectJson("app_settings_response").getData().get(0);
    }



    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

}


//    Geocoder geocoder = new Geocoder(context, Locale.getDefault());
//    List<Address> addresses  = geocoder.getFromLocation(location.latitude,location.longitude, 1);
//    String address = addresses.get(0).getAddressLine(0);
//    String city = addresses.get(0).getLocality();
//    String state = addresses.get(0).getAdminArea();
//    String zip = addresses.get(0).getPostalCode();
//    String country = addresses.get(0).getCountryName();