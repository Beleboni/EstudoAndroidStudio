package br.com.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.banco.BancoDados;
import br.com.model.Produto;

/**
 * Created by Fernando on 18/10/2015.
 */
public class ProdutoDAO {

    SQLiteDatabase db;

    public ProdutoDAO(Context context){
        db = BancoDados.getDB(context);
    }

    public void salvar(Produto produto){

        ContentValues values = new ContentValues();
        values.put("descricao", produto.getDescricao());
        values.put("tipoProduto", produto.getTipoProduto());
        values.put("valor", produto.getValor());

        db.insert("tbl_produto", null, values);
    }

    public List<Produto> listar(){

        String[] colunas = new String[]{"_id","descricao", "tipoProduto", "valor"};
        List<Produto> produtos;
        Cursor c = db.query("tbl_produto", colunas, null, null, null, null, null);

        produtos = new ArrayList<Produto>();
        if (c.moveToFirst()){
            do {
                Produto produto = new Produto();
                produto.setId(c.getLong(c.getColumnIndex("_id")));
                produto.setDescricao(c.getString(c.getColumnIndex("descricao")));
                produto.setTipoProduto(c.getString(c.getColumnIndex("tipoProduto")));
                produto.setValor(c.getString(c.getColumnIndex("valor")));

                produtos.add(produto);
            }while (c.moveToNext());
        }

        c.close();
        return produtos;

    }

    public Produto buscar(String id){
        String[] colunas = new String[]{"_id","descricao", "tipoProduto", "valor"};
        String[] args = new String[]{id};

        Cursor c = db.query("tbl_produto", colunas,"_id = ?", args, null,null,null, null);


        c.moveToFirst();
        Produto produto = new Produto();
        produto.setId(c.getLong(c.getColumnIndex("_id")));
        produto.setDescricao(c.getString(c.getColumnIndex("descricao")));
        produto.setTipoProduto(c.getString(c.getColumnIndex("tipoProduto")));
        produto.setValor(c.getString(c.getColumnIndex("valor")));
        return produto;




    }

    public void alterar(Produto produto){

        ContentValues values = new ContentValues();
        values.put("descricao", produto.getDescricao());
        values.put("tipoProduto",produto.getTipoProduto());
        values.put("valor", produto.getValor());

        String[] args = new String[]{String.valueOf(produto.getId())};
        db.update("tbl_produto", values, "_id = ?", args);


    }
    public void deletar(String id){
        String[] args = new String[]{id};
        db.delete("tbl_produto", "_id = ?", args);
    }

}
