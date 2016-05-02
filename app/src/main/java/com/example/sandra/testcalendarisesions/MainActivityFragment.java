package com.example.sandra.testcalendarisesions;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private Firebase sesionRef;
    private  Firebase ref;
    private ListView listSesiones;
    private TextView nom ;
    private TextView data ;
    private TextView monitor;
    private Spinner spinner;
    private ImageView fotsesio;
    ArrayList<Sesion> items;
    public MainActivityFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Firebase.setAndroidContext(getContext());
        ref = new Firebase("https://testgimmapp.firebaseio.com/");
        sesionRef = ref.child("Sesions");
        listSesiones = (ListView)rootView.findViewById(R.id.listSesions);
        spinner = (Spinner)rootView.findViewById(R.id.DatesSesions);
        configuracioLlistaMaquines();
        configuracioSpinner();

        items = new ArrayList<Sesion>();



        return rootView;
    }

    /**
     * Metode per configurar el Spinner de la pantalla.
     */
    private void configuracioSpinner(){

        String[] valores = {"10/05/2016","15/05/2016","19/05/2016","27/05/2016"};

        spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, valores));
    }

    /**
     * Metode per llistar totes les sesions realitzades al centre.
     */
    private void configuracioLlistaMaquines(){
        FirebaseListAdapter adapter = new FirebaseListAdapter<Sesion>(getActivity(), Sesion.class, R.layout.list_sesions_calendari, sesionRef) {
                @Override
            protected void populateView(View v, Sesion info, int position) {
                        nom = (TextView) v.findViewById(R.id.nomSesio);
                        data = (TextView) v.findViewById(R.id.dataHorariSesio);
                        monitor = (TextView) v.findViewById(R.id.NomMonitora);
                        fotsesio = (ImageView) v.findViewById(R.id.fotoSesio);

                        nom.setText(info.getNom());
                        data.setText(info.getData() + " " + info.getHora());
                        monitor.setText(info.getMonitor());
                        if (info.getNom().equals("Body pump")) {
                            Picasso.with(getContext())
                                    .load(R.mipmap.bodypump)
                                    .fit()
                                    .into(fotsesio);
                        } else if (info.getNom().equals("Step")) {
                            Picasso.with(getContext())
                                    .load(R.mipmap.step)
                                    .fit()
                                    .into(fotsesio);
                        } else if (info.getNom().equals("Body Combat")) {
                            Picasso.with(getContext())
                                    .load(R.mipmap.bodycombat)
                                    .fit()
                                    .into(fotsesio);
                        } else if (info.getNom().equals("Zumba")) {
                            Picasso.with(getContext())
                                    .load(R.mipmap.zumba)
                                    .fit()
                                    .into(fotsesio);
                        } else {
                            Picasso.with(getContext())
                                    .load(R.mipmap.fitness)
                                    .fit()
                                    .into(fotsesio);
                        }
            }
        };
        listSesiones.setAdapter(adapter);
    }

    /**
     * Metode per trobar les sesions amb un nom concret
     * @param nomSesio
     */
    private void configuracioLlistaMaquinesSesions(String nomSesio){
        Query queryRef = sesionRef.orderByChild("nom").equalTo(nomSesio);


        queryRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                    Sesion a = snapshot.getValue(Sesion.class);
                    items.add(a);
                    System.out.println("" + items.size() + a.getNom());
                    ArrayListAdapterSesions itemsAdapter = new ArrayListAdapterSesions(getContext(), R.layout.list_sesions_calendari, items);
                    listSesiones.setAdapter(itemsAdapter);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }


        });


    }


}
