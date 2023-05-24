package com.example.myguideapp;

import android.Manifest;


import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.app.Activity;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.location.Location;
import com.yandex.mapkit.location.LocationListener;
import com.yandex.mapkit.location.LocationManager;
import com.yandex.mapkit.location.LocationStatus;
import com.yandex.mapkit.map.CameraPosition;

import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//  (Сделано) чтобы при нажатии на кнопку назад было реально назад и найти почему у меня пока это не так
// (Сделано)сделать избранные в достопримечательностях
// (Сделано)ресайклер вью список избранных
// (Сделано)при нажатии на конпку раскрылась карта на полный экран
// (сделано)маркер увеличить
// (Сделано)добавить фоточки под текст ,
//  (невозможно сделать)попытаться в дравабле создать папку с картинками
// можно сделать парселабле
// вывести достопримечательности в радиусе
// местоположение



public class MapActivity extends Activity {

    private static final int PERMISSIONS_REQUEST_LOCATION = 1;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private final String MAPKIT_API_KEY = "e5ac0605-740b-48df-bb53-7810e4ced9d8";
    private Point TARGET_LOCATION;

    private MapView mapView;
    private ImageView imageView1;
    private ImageView imageView2;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private MapObjectCollection mapObjects;
    private EditText editKilometres;
    private Location myLocation;
    private Sight[] allsights;
    private List<PlacemarkMapObject> nearSightsMarkers;
    private Sight ourSight;



    private CardView buttonScreen;

    private static int count = 0;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Разрешения предоставлены, выполнение операций с местоположением
            } else {
                // Разрешения не предоставлены, обработка ситуации без доступа к местоположению
            }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        if (count == 0){
            MapKitFactory.setApiKey(MAPKIT_API_KEY);
            MapKitFactory.initialize(this);
        }
        count ++;
        nearSightsMarkers = new ArrayList<>();

        Intent intent = getIntent();
        ourSight = (Sight)intent.getSerializableExtra("sight");
        allsights = (Sight[])intent.getSerializableExtra("allsights");
        TARGET_LOCATION = new Point(ourSight.getCoordinates1(), ourSight.getCoordinates2());
        // Проверка наличия разрешения
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Если разрешение не предоставлено, запросите его у пользователя


            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_REQUEST_CODE);
        } else {

        }


        editKilometres = findViewById(R.id.setKilometres);
        // Создание MapView.
        setContentView(R.layout.activity_map);

        mapView = (MapView)findViewById(R.id.mapview);

        mapObjects = mapView.getMap().getMapObjects().addCollection();
        PlacemarkMapObject placemark = mapObjects.addPlacemark(new Point(
                ourSight.getCoordinates1(), ourSight.getCoordinates2()));
        placemark.setOpacity(20.5f);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.markkk);
        ImageProvider imageProvider = ImageProvider.fromBitmap(bitmap);
        placemark.setIcon(imageProvider);
        //float scaleFactor = 1.5f;
        IconStyle iconStyle = new IconStyle();
        //iconStyle.setScale(scaleFactor);
        placemark.setIconStyle(iconStyle);
        requestLocation();
        mapView.getMap().move(
                new CameraPosition(TARGET_LOCATION, 17.0f, 0.0f, 2.0f),
                new Animation(Animation.Type.SMOOTH, 2),
                null);
        TextView twName = findViewById(R.id.name);
        TextView twHours = findViewById(R.id.workHours);
        TextView twDesc = findViewById(R.id.description);
        twName.setText(ourSight.getName());
        twHours.setText("Время работы: " + ourSight.getWorkHours());
        twDesc.setText(ourSight.getDescription());

        buttonScreen = findViewById(R.id.hideshow);
        buttonScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFullScreen();;

            }
        });
        imageView1 = findViewById(R.id.image1);
        imageView2 = findViewById(R.id.image2);
        //imageView1.setImageResource(R.drawable.drawable1.);
        displayImageFromFolder(ourSight.getImagesDir());

        locationManager = MapKitFactory.getInstance().createLocationManager();
        // Запрос единичного обновления местоположения
        locationManager.requestSingleUpdate(new LocationListener() {
            @Override
            public void onLocationUpdated(@NonNull Location location) {
                // Обработка обновления местоположения
                myLocation = location;
                double latitude = location.getPosition().getLatitude();
                double longitude = location.getPosition().getLongitude();
                // Создание метки для местоположения пользователя
                Point userLocation = new Point(latitude, longitude);
                PlacemarkMapObject userPlacemark = mapObjects.addPlacemark(userLocation);
                System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");


                // Настройка отображения метки местоположения пользователя
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fullscreenmapp);
                ImageProvider imageProvider = ImageProvider.fromBitmap(bitmap);
                userPlacemark.setIcon(imageProvider);

                // Используйте полученные координаты
            }

            @Override
            public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {
                // Обработка статуса местоположения, если необходимо
            }

        });

        requestLocation();
        requestLocation();








    }
    public void displayImageFromFolder(String name) {
        // Получаем идентификатор ресурса папки
        ImageView myImageView = findViewById(R.id.image1);
        String imageName = name + "1"; // Здесь укажите желаемое название картинки без расширения файла
        int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        myImageView.setImageResource(resourceId);
        myImageView = findViewById(R.id.image2);
        imageName = name + "2"; // Здесь укажите желаемое название картинки без расширения файла
        resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        myImageView.setImageResource(resourceId);

    }




    private void setFullScreen(){
        ViewGroup.LayoutParams layoutParams = mapView.getLayoutParams();
        if (layoutParams.height == 0) {
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT; // Установите нужную вам высоту в пикселях
        } else {
            layoutParams.height = 0;
        }
        mapView.setLayoutParams(layoutParams);
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();

    }
    public void onBackClick(View view){
        onBackPressed();
    }
    private boolean rightFormat(){
        editKilometres = findViewById(R.id.setKilometres);
        String textKm = editKilometres.getText().toString();
        Pattern pattern = Pattern.compile("^(?:[1-9]\\d{0,2}|1000)$");
        Matcher matcher = pattern.matcher(textKm);
        return matcher.matches();

    }
    private void deMarkOldSights(){
        for (int i = 0; i < nearSightsMarkers.size(); ++i){
            mapView.getMap().getMapObjects().remove(nearSightsMarkers.get(i));
            nearSightsMarkers.remove(nearSightsMarkers.get(i));
            --i;

        }
    }
    private void markNearSights(int dist){
        deMarkOldSights();
        for (Sight s: allsights){
            if (myLocation != null){
                double distance = calculateDistance(myLocation.getPosition().getLatitude(),
                        myLocation.getPosition().getLongitude(), s.getCoordinates1(),
                        s.getCoordinates2());
                if (distance <= dist){
                    PlacemarkMapObject marker = mapView.getMap().getMapObjects().
                            addPlacemark(new Point(s.getCoordinates1(), s.getCoordinates2()));
                    marker.setOpacity(22.5f);
                    nearSightsMarkers.add(marker);
                }
            }
            else{
                Toast.makeText(MapActivity.this,"Ваше местоположение не определено",
                        Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
    public void onOkClick(View view){
        if (!rightFormat()){
            editKilometres.setText("");
            Toast.makeText(MapActivity.this,"Ошибка! Должно быть введено целое" +
                            " число от 1 до 1000",
                    Toast.LENGTH_LONG).show();
            deMarkOldSights();
        }
        else{
            markNearSights(Integer.parseInt(editKilometres.getText().toString()));
            Toast.makeText(MapActivity.this,"В радиусе " +
                            editKilometres.getText().toString() + " километров " +
                            nearSightsMarkers.size() + " достопримечательностей\n Они отмечены синими" +
                            " маркерами" ,
                    Toast.LENGTH_LONG).show();
        }

    }
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Радиус Земли в километрах
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return distance;
    }

    // Функция для запуска запроса местоположения
    private void requestLocation() {
        locationManager = MapKitFactory.getInstance().createLocationManager();
        locationManager.requestSingleUpdate(new LocationListener() {
            @Override
            public void onLocationUpdated(@NonNull Location location) {
                // Обработка обновления местоположения
                myLocation = location;
                double latitude = location.getPosition().getLatitude();
                double longitude = location.getPosition().getLongitude();
                // Создание метки для местоположения пользователя
                Point userLocation = new Point(latitude, longitude);
                PlacemarkMapObject userPlacemark = mapObjects.addPlacemark(userLocation);
                System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");


                // Настройка отображения метки местоположения пользователя
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fullscreenmapp);
                ImageProvider imageProvider = ImageProvider.fromBitmap(bitmap);
                userPlacemark.setIcon(imageProvider);

                // Используйте полученные координаты
            }

            @Override
            public void onLocationStatusUpdated(@NonNull LocationStatus locationStatus) {
                // Обработка статуса местоположения, если необходимо
            }

        });
    }


}
