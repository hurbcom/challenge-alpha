package com.example.desafioalphahurb.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafioalphahurb.R;
import com.example.desafioalphahurb.activitites.DetalheHotelActivity;
import com.example.desafioalphahurb.layout.RecyclerViewAdapter;
import com.example.desafioalphahurb.model.Hotel;
import com.example.desafioalphahurb.task.HotelAsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HoteisFragment extends Fragment {

    List<Hotel> Hotels = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Hotel> listHoteis;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_hoteis, container, false);

        carregarJsonHoteis();

        Log.d("Hotels", String.valueOf(Hotels.size()));

        listHoteis = new ArrayList<>();

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        recyclerViewAdapter = new RecyclerViewAdapter(listHoteis, getContext());

        //TRATA O ERRRO CASO ALGUM DADO SEJA NULO
        try {
                carregarHoteis();
        } catch (Exception e) {
            Log.d("Erro", e.getMessage());
        }

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new ClickListener() {

            Intent intent = new Intent(getContext(), DetalheHotelActivity.class);
            Bundle params = new Bundle();

            @Override
            public void onClick(View view, final int position) {

                params.putInt("index", listHoteis.get(position).getIndex());
                intent.putExtras(params);
                startActivity(intent);

                ImageView image = (ImageView)view.findViewById(R.id.image);
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {
                //Long press on position
            }
        }));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);

        Log.d("JSON HOTEIS", listHoteis.toString());

        return root;
    }

    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(HoteisFragment hoteisFragment, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(hoteisFragment.getContext(), new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private void carregarHoteis(){

        Hotel hotel;

        carregarJsonHoteis();

        for(int i=0; i <= Hotels.size() - 1; i++) {
            hotel = new Hotel(
                    i,
                    Hotels.get(i).getName(),
                    Hotels.get(i).getCity(),
                    Hotels.get(i).getState(),
                    Hotels.get(i).getPrice(),
                    Hotels.get(i).getStars(),
                    Hotels.get(i).getImage(),
                    Hotels.get(i).getAmenities());

            listHoteis.add(hotel);
        }

        recyclerViewAdapter.notifyDataSetChanged();
    }

    public void carregarJsonHoteis() {
        try {
            Hotels = new HotelAsyncTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}