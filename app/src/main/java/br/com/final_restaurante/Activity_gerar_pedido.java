package br.com.final_restaurante;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import br.com.adpter.ProdutoAdpter;
import br.com.dao.ProdutoDAO;
import br.com.model.Produto;


public class Activity_gerar_pedido extends ListActivity {

    final int MENU_CONCLUIR_PEDIDO = 1;
    final int MENU_VOLTAR_INICIO = 2;

    ProdutoAdpter adpter;
    List<Produto> produtos;
    ProdutoDAO dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerar_pedido);


        //CAHMANDO O DAO
        dao = new ProdutoDAO(this);
        produtos = dao.listar();

        //PASSANDO PARA O ADAPTER AS INFORMAÇÕES
        adpter = new ProdutoAdpter(this, R.layout.activity_item_produto, produtos);
        setListAdapter(adpter);

        registerForContextMenu(getListView());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        atualizaLista();
    }
    //ATUALIZANDO A LISTA
    private void atualizaLista() {
        List<Produto> c = dao.listar();
        produtos.clear();
        produtos.addAll(c);
        adpter.notifyDataSetChanged();

    }

    //ADICIONAL OS CONTEXT AOS ITENS

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0, "ADICIONAR AO PEDIDO");
        menu.add(0,1,0, "CANCELAR");
    }

    //AÇÕES DO CONTEXT
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                Toast.makeText(this, "FALTA CONCLUIR - ADICIONA AO PEDIDO", Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(this, "CANCELADO COM SUCESSO", Toast.LENGTH_LONG).show();
                break;
        }
        return true;
    }

    //MEMUS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_CONCLUIR_PEDIDO, 0, "CONCLUIR PEDIDO");
        menu.add(0, MENU_VOLTAR_INICIO, 0, "VOLTAR PARA TELA INICIAL");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case MENU_CONCLUIR_PEDIDO:
                Intent it = new Intent(this, Activity_pedido_pronto.class);
                startActivity(it);
                finish();
                break;
            case MENU_VOLTAR_INICIO:
                Intent iti = new Intent(this, MainActivity.class);
                startActivity(iti);
                finish();
                break;
        }

        return true;
    }
}
