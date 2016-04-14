package br.com.final_restaurante;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //CHAMANDO A TELA DE MONTAR CARDAPIO
    //NESTA TELA SÃO CADASTRADOS OS PRODUTOS
    public void montarCardapio(View v){
        Intent it = new Intent(this, Activity_montar_cardapio.class);
        startActivity(it);
    }

    //CHAMANDO A TELA DE GERAR PEDIDO
    //NESTA TELA SÃO EXIBIDOS OS ITENS
    public void gerarPedido(View v){
        Intent it = new Intent(this, Activity_gerar_pedido.class);
        startActivity(it);
    }
}
