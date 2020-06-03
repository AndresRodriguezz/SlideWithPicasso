package co.and.sliderwithpicasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtTerminar;
    private TextView[] puntosSlide;
    private LinearLayout linearPuntos;
    ViewPager viewPager;
    private String[] imageUrls = new  String[]{
            "https://cdn.pixabay.com/photo/2016/02/19/11/46/night-1209938_960_720.jpg",
            "https://cdn.pixabay.com/photo/2016/05/25/19/45/fish-1415723_960_720.jpg",
            "https://cdn.pixabay.com/photo/2020/01/14/14/41/forest-4765248_960_720.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewPager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(this,imageUrls);
        viewPager.setAdapter(adapter);

        linearPuntos = findViewById(R.id.idLinearPuntos);
        agregarIndicadorPuntos(0);
        viewPager.addOnPageChangeListener(viewListener);

        txtTerminar = findViewById(R.id.txtTerminar);
        txtTerminar.setVisibility(View.INVISIBLE);
        txtTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new  Intent(MainActivity.this,ContenedorActivitiesActivity.class);
                startActivity(miIntent);

            }
        });


    }

    private void agregarIndicadorPuntos(int pos) {
        //Se le asigna el tamapao a nuestro arreglo de tipo TextView no olvidar el casteo
        puntosSlide = new TextView[3];
        //Se limpia el linearPuntos anterior
        linearPuntos.removeAllViews();
        //Se recorre todo el arreglo
        for(int i=0;i<puntosSlide.length;i++){
            //creo los componentes que se van a guardar en el arreglo puntosSlide
            //Creo la instancia y le digo por defecto cual es la instancia
            puntosSlide[i]= new TextView(this);
            //Formato de texto que va utilizar
            puntosSlide[i].setText(Html.fromHtml("&#8226;"));
            puntosSlide[i].setTextSize(35);
            puntosSlide[i].setTextColor(getResources().getColor(R.color.colorBlancoTransparente));
            //Agregamos nuestro conjunto de views en nuestro linearpuntos
            linearPuntos.addView(puntosSlide[i]);
        }
        if (puntosSlide.length>0){
            //la posicion que llegue es la que se va a pintar de blanco
            puntosSlide[pos].setTextColor(getResources().getColor(R.color.colorBlanco));
        }

    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            agregarIndicadorPuntos(position);
            botonTerminar(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void botonTerminar(int position) {
        if(position==2){
            txtTerminar.setVisibility(View.VISIBLE);
        } else{
            txtTerminar.setVisibility(View.INVISIBLE);
        }

    }
}
