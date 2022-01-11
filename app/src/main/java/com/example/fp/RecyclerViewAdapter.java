package com.example.fp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fp.Activity.RegisterBank;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Rekening> dataList = new ArrayList<>();
    private DataListListener listener;

    public void setData(List<Rekening> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            Rekening data = dataList.get(i);
            int position = findPosition(data);
            if (position == -1) {
                this.dataList.add(data);
                notifyItemInserted(this.dataList.size() - 1);
            } else {
                this.dataList.remove(position);
                this.dataList.add(position, data);
                notifyItemChanged(position);
            }
        }
    }

    private int findPosition(Rekening data) {
        int position = -1;
        if (!this.dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                if (this.dataList.get(i).getId() == data.getId()) {
                    position = i;
                }
            }
        }

        return position;
    }
    public void removeData(Rekening data) {
        if (this.dataList.isEmpty()) {
            return;
        }

        for (int i = 0; i < dataList.size(); i++) {
            if (this.dataList.get(i).getId() == data.getId()) {
                this.dataList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public void setRemoveListener(DataListListener listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rek_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position),listener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tvNama, tvNorek, tvKota, tvUmur;
        private ImageView btnHapus;
        private Rekening rekening;
        private DataListListener listener;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama = itemView.findViewById(R.id.tvNama);
            tvNorek = itemView.findViewById(R.id.tvNorek);
            tvKota = itemView.findViewById(R.id.tvKota);
            tvUmur = itemView.findViewById(R.id.tvUmur);
            btnHapus = itemView.findViewById(R.id.btn_hapus);

            btnHapus.setOnClickListener(this);
            itemView.setOnClickListener(this);

        }
        public void bind(Rekening rekening, DataListListener listener) {
            this.rekening = rekening;
            this.listener = listener;

            tvNama.setText(rekening.getNama());
            tvNorek.setText(String.valueOf(rekening.getNorek()));
            tvKota.setText(rekening.getKota());
            tvUmur.setText(rekening.getUmur());
        }
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_hapus) {

                AlertDialog.Builder builder = new AlertDialog.Builder(btnHapus.getContext());
                builder.setTitle("Peringatan !!! ")
                        .setMessage("Apakah Anda Ingin Menghapus Data Ini ? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                MyApp.getInstance().getDb().rekDAO().delete(rekening);
                                listener.onRemoveClick(rekening);
                                Toast.makeText(itemView.getContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
            } else if (view.getId() == R.id.item_list) {

                Intent intent = new Intent(itemView.getContext(), RegisterBank.class);
                intent.putExtra(RegisterBank.TAG_DATA_INTENT, rekening.getId());
                itemView.getContext().startActivity(intent);

            }

        }


    }
}
