package com.example.car_service_wecarcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class lcMainAdapter extends FirebaseRecyclerAdapter<lcMainModel,lcMainAdapter.myViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public lcMainAdapter(@NonNull @NotNull FirebaseRecyclerOptions<lcMainModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull myViewHolder holder, int position, @NonNull @NotNull lcMainModel model) {
        holder.sertype.setText(model.getSertype());
        holder.cartype.setText(model.getCartype());
        holder.fuelexpert.setText(model.getFuelexpert());
        holder.picktime.setText(model.getPicktime());
        holder.delitime.setText(model.getDelitime());
        holder.extrachar.setText(model.getExtrachar());

        Glide.with(holder.img.getContext())
                .load(model.getSurl())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

    }

    @NonNull
    @NotNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lcmain_item,parent,false);
        return new myViewHolder(view);
    }



    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView sertype, cartype, fuelexpert, picktime, delitime, extrachar, surl;

        public myViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            img = (CircleImageView)itemView.findViewById(R.id.img1);
            sertype = (TextView)itemView.findViewById(R.id.sertypetext);
            cartype = (TextView)itemView.findViewById(R.id.cartypetext);
            fuelexpert = (TextView)itemView.findViewById(R.id.fuelexperttext);
            picktime = (TextView)itemView.findViewById(R.id.picktimetext);
            delitime = (TextView)itemView.findViewById(R.id.delitimetext);
            extrachar = (TextView)itemView.findViewById(R.id.extrachartext);


        }
    }
}




















