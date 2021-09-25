package com.example.car_service_wecarcare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainDsAdapter extends FirebaseRecyclerAdapter<MainDsModel,MainDsAdapter.myViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainDsAdapter(@NonNull FirebaseRecyclerOptions<MainDsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder,final int position, @NonNull MainDsModel model) {

        holder.name.setText(model.getName());
        holder.phoneNo.setText(model.getPhoneNo());
        holder.location.setText(model.getLocation());
        holder.brand.setText(model.getBrand());
        holder.vModel.setText(model.getvModel());
        holder.vMake.setText(model.getvMake());
        holder.fuelType.setText(model.getFuelType());
        holder.date.setText(model.getDate());


       //update

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.ds_update_popup))
                        .setExpanded(true,1600)
                        .create();

                //dialogPlus.show();

                View view = dialogPlus.getHolderView();

                EditText name =view.findViewById(R.id.txtName);
                EditText phoneNo =view.findViewById(R.id.txtPhone);
                EditText location =view.findViewById(R.id.txtLocation);
                EditText brand =view.findViewById(R.id.txtbrand);
                EditText vModel =view.findViewById(R.id.txtvModel);
                EditText vMake =view.findViewById(R.id.txtvMake);
                EditText fuelType =view.findViewById(R.id.txtfuelType);
                EditText date =view.findViewById(R.id.txtdate);


                Button btnUpdate = view.findViewById(R.id.btnUpdate);

                name.setText(model.getName());
                phoneNo.setText(model.getPhoneNo());
                location.setText(model.getLocation());
                brand.setText(model.getBrand());
                vModel.setText(model.getvModel());
                vMake.setText(model.getvMake());
                fuelType.setText(model.getFuelType());
                date.setText(model.getDate());


                dialogPlus.show();


                btnUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map= new HashMap<>();
                        map.put("name",name.getText().toString());
                        map.put("phoneNo",phoneNo.getText().toString());
                        map.put("location",location.getText().toString());
                        map.put("brand",brand.getText().toString());
                        map.put("vModel",vModel.getText().toString());
                        map.put("vMake",vMake.getText().toString());
                        map.put("fuelType",fuelType.getText().toString());
                        map.put("date",date.getText().toString());


                        FirebaseDatabase.getInstance("https://carserviceapp-fb926-default-rtdb.firebaseio.com/").getReference().child("request")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure( Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while Updating ", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();

                                    }
                                });
                    }
                });


            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Delete data can't br undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                        FirebaseDatabase.getInstance("https://carserviceapp-fb926-default-rtdb.firebaseio.com/").getReference().child("request")
                                .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Toast.makeText(holder.name.getContext(), "cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_req,parent, false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name,phoneNo,location,brand,vModel,vMake,fuelType,date;

        Button btnEdit,btnDelete;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView)itemView.findViewById(R.id.nametext);
            phoneNo = (TextView)itemView.findViewById(R.id.phoneNotext);
            location = (TextView)itemView.findViewById(R.id.locationtext);
            brand = (TextView)itemView.findViewById(R.id.brandtext);
            vModel = (TextView)itemView.findViewById(R.id.vModeltext);
            vMake = (TextView)itemView.findViewById(R.id.vMaketext);
            fuelType = (TextView)itemView.findViewById(R.id.fuelTypetext);
            date = (TextView)itemView.findViewById(R.id.datetext);

            btnEdit = (Button)itemView.findViewById(R.id.btnEdit);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);

        }
    }

}
