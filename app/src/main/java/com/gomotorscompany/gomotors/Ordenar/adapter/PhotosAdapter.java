package com.gomotorscompany.gomotors.Ordenar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.gomotorscompany.gomotors.Ordenar.model.Banners;
import com.gomotorscompany.gomotors.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class PhotosAdapter  extends PagerAdapter {
private List<Banners> banners;
private Context context;
private LayoutInflater layoutInflater;
private int mposition;


    public PhotosAdapter(FragmentManager childFragmentManager, Context context) {//(FragmentManager childFragmentManager, List<Banners> banners, Context context) {
        this.context= context;
      //  this.banners=banners;
        layoutInflater=(LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView=layoutInflater.inflate(R.layout.itembanners,container,false);
        this.mposition=position;
      /**colocar glide images*/
       ImageView iv=itemView.findViewById(R.id.iviewP);
        Glide.with(context)
                .load(R.drawable.promo) // .load(banners.get(position).getUrls())
                //.diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(iv);
        container.addView(itemView);


        return itemView;

    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager)container;
        View view = (View)object;
        vp.removeView(view);
    }


}
