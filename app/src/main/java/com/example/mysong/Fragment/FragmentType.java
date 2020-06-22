package com.example.mysong.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.mysong.Activity.SongListActivity;
import com.example.mysong.Model.MType;
import com.example.mysong.R;
import com.example.mysong.Service.APIService;
import com.example.mysong.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentType extends Fragment {
    private HorizontalScrollView typescrollview;
    private ArrayList<MType> mTypeArrayList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.type_layout, container, false);
        typescrollview = view.findViewById(R.id.typescrollview);
        TextView typeviewmore = view.findViewById(R.id.tvtypeviewmore);
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<MType>> callback = dataService.GetMusicType();
        callback.enqueue(new Callback<List<MType>>() {
            @Override
            public void onResponse(Call<List<MType>> call, Response<List<MType>> response) {
                mTypeArrayList = (ArrayList<MType>) response.body();
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580,250);
                layoutParams.setMargins(10,20,10,30);

                for(int i = 0; i < mTypeArrayList.size(); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (mTypeArrayList.get(i).getTypePhoto() != null){
                        Picasso.with(getActivity()).load(mTypeArrayList.get(i).getTypePhoto()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), SongListActivity.class);
                            intent.putExtra("idtype", mTypeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                typescrollview.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<List<MType>> call, Throwable t) {

            }
        });
    }
}
