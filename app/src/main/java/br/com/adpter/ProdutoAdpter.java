package br.com.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.final_restaurante.R;
import br.com.model.Produto;

/**
 * Created by Fernando on 18/10/2015.
 */
public class ProdutoAdpter extends ArrayAdapter<Produto>{

    //ESTA CLASSE E RESPONSAVEL POR MOSTRAR OS PRODUTOS QUE EXISTEM NO BANCO
    Context context;
    int layout;
    List<Produto> produtos;

    //CONSTRUTOR
    public ProdutoAdpter(Context context, int layout, List<Produto> produtos){
        super(context, layout, produtos);
        this.context = context;
        this.layout = layout;
        this.produtos = produtos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, null);

        TextView tvDescricao = (TextView) view.findViewById(R.id.tv_item_descricao);
        TextView tvTipoProduto = (TextView) view.findViewById(R.id.tv_item_tipoProduto);
        TextView tvValor = (TextView) view.findViewById(R.id.tv_item_valor);

        Produto produto = produtos.get(position);
        tvDescricao.setText(produto.getDescricao());
        tvTipoProduto.setText(produto.getTipoProduto());
        tvValor.setText(produto.getValor());

        return  view;
    }


}
