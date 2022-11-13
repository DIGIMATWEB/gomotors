package com.gomotorscompany.gomotors.enprodresodetail.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;

import java.util.ArrayList;
import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder>{

    private Context context;
    public QuestionsAdapter( Context context) {

        this.context=context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_questions_type, parent, false);
        return new QuestionsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final QuestionsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

    }


    @Override
    public int getItemCount() {
        return 2;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
      //  ConstraintLayout switchanswer,optionanswer;
      //  TextView description1,description2;
//        Spinner spinnerquestionary;
//        Switch switchquestionary;
        private ImageView imagephoto,imagephoto2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            imagephoto2=itemView.findViewById(R.id.imagephoto2);
//            imagephoto=itemView.findViewById(R.id.imagephoto);
//            switchquestionary=itemView.findViewById(R.id.switchquestionary);
//            switchanswer=itemView.findViewById(R.id.switchanswer);
//            optionanswer=itemView.findViewById(R.id.optionanswer);
//            description1=itemView.findViewById(R.id.textopenquestion); //open
//            description2=itemView.findViewById(R.id.textbooleanonly);  //bool
//            spinnerquestionary=itemView.findViewById(R.id.spinnerquestionary);


        }
    }
}