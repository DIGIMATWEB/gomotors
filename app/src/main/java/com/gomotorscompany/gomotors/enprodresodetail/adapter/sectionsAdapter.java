package com.gomotorscompany.gomotors.enprodresodetail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gomotorscompany.gomotors.R;

import java.util.List;

public class sectionsAdapter  extends RecyclerView.Adapter<sectionsAdapter.ViewHolder>{// PagerAdapter {

    private Context context;
    private  int sizedots;
    private QuestionsAdapter adapter;

    public sectionsAdapter( Context context) {//(FragmentManager childFragmentManager, List<Banners> banners, Context context) {
        this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pager_questions, viewGroup, false);
        return new sectionsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull sectionsAdapter.ViewHolder holder, int position) {
        adapter=new QuestionsAdapter( context);//TODO cambiar esto por el iterador
        holder.rv.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView rv;
        public ViewHolder(View view) {
            super(view);
            rv=itemView.findViewById(R.id.rvQuestions);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            rv.setLayoutManager(layoutManager);
        }
    }
}