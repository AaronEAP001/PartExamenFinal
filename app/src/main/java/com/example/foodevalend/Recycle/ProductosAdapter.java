package com.example.foodevalend.Recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodevalend.R;

import java.util.List;

import VO.ProductoVO;

public class ProductosAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private List<ProductoVO> productoVO;

    public ProductosAdapter(List<ProductoVO> productoVO) {
        this.productoVO = productoVO;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto,parent,false);
        return new CardViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        if (productoVO != null & position<productoVO.size()){
            ProductoVO vo = productoVO.get(position);
            holder.prodName.setText(vo.getNombre());
            holder.prodCategory.setText(vo.getCategoria());
            holder.prodPreci.setText("s/ "+String.valueOf(vo.getPrecio()));
            holder.prodDes.setText(vo.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return productoVO.size();
    }
}
