package mx.edu.itl.c85360673.u3listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListaTextoImagenActivity extends AppCompatActivity {

    private ListView lstvClubes;

    private final String [] clubes        = { "Eevee", "Pikachu", "Clefable", "Beedrill",
                                              "Butterfree" };
    private final String [] descripciones = { "Pokemon tipo Normal" ,
                                            "Pokemon tipo Electrico",
                                            "Pokemon tipo Hada",
                                            "Pokemon tipo Bicho/Veneno",
                                            "Pokemon tipo Bicho/Volador"
                                          };
    private final int [] logos = { R.drawable.eevee,
                                   R.drawable.pikachu,
                                   R.drawable.clefa,
                                   R.drawable.beedrill,
                                   R.drawable.butter
                                 };

    //----------------------------------------------------------------------------------------------

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_texto_imagen);

        lstvClubes = findViewById  ( R.id.lstvClubes );

        // Establecemos el Adapter para el ListView, el adapter será un objeto MiAdaptador
        MiAdaptador adaptador = new MiAdaptador ( this, clubes, descripciones, logos );
        lstvClubes.setAdapter( adaptador );


        // Establecemos un listener para el evento onItemClick del ListView
        lstvClubes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListaTextoImagenActivity.this, descripciones [ i ],
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    //----------------------------------------------------------------------------------------------

    class MiAdaptador extends ArrayAdapter {
        private  Context context;
        private String [] clubes;
        private String [] descripciones;
        private int    [] logos;

        public  MiAdaptador ( Context c, String [] clubes, String [] descripciones, int [] logos ) {
            super( c, R.layout.list_fila_texto_imagen, R.id.txtvTitulo, clubes );
            context = c;
            this.clubes = clubes;
            this.descripciones = descripciones;
            this.logos = logos;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if ( convertView == null ) {
                LayoutInflater layoutInflater =
                        ( LayoutInflater ) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE );
                //LayoutInflater layoutInflater = LayoutInflater.from ( context );

                convertView = layoutInflater.inflate ( R.layout.list_fila_texto_imagen, parent, false );

            }

            ImageView logo = convertView.findViewById ( R.id.imgvLogo );
            TextView titulo = convertView.findViewById ( R.id.txtvTitulo );
            TextView subtitulo = convertView.findViewById ( R.id.txtvSubtitulo );

            logo.setImageResource( logos [ position ] );
            titulo.setText( clubes [ position ] );
            subtitulo.setText( descripciones [ position ] );

            return convertView;
        }
    }
}
