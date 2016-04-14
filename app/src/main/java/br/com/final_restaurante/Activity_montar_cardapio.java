package br.com.final_restaurante;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.dao.ProdutoDAO;
import br.com.model.Produto;


public class Activity_montar_cardapio extends Activity {

    //PEGANDO AS VARIAVEIS
    EditText txtDescricao, txtTipoproduto, txtvalor;

    //ACESSANDO O BANCO DE DADOS
    ProdutoDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_montar_cardapio);

        //CHAMANDO A CLESSE BANCO
        dao = new ProdutoDAO(this);
        txtDescricao = (EditText) findViewById(R.id.txt_montar_cardapio_descricao);
        txtTipoproduto = (EditText) findViewById(R.id.txt_montar_cardapio_tipo);
        txtvalor = (EditText)findViewById(R.id.txt_montar_cardapio_valor);
    }

    public void inserir(View v){
        //SALVAR O PRODUTO
        Produto produto = new Produto();
        produto.setDescricao(txtDescricao.getText().toString());
        produto.setTipoProduto(txtTipoproduto.getText().toString());
        produto.setValor(txtvalor.getText().toString());
        //SALVAR O PRODUTO
        dao.salvar(produto);
        //MENSAGEM DE SUCESSO
        Toast.makeText(this, "ITEM CADASTRADO COM SUCESSO",Toast.LENGTH_LONG).show();
        //LIMPANDO OS CAMPOS
        txtDescricao.setText("");
        txtTipoproduto.setText("");
        txtvalor.setText("");
    }

    public void cancelar(View v){
        Toast.makeText(this, "ATÊNÇÃO ESTE ITEM NÃO SERÁ ADICIONADO AO CARDAPIO",Toast.LENGTH_LONG).show();
    }

}
