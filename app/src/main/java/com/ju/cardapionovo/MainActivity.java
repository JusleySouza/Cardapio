package com.ju.cardapionovo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.ju.cardapionovo.BDHelper.CardapioBd;
import com.ju.cardapionovo.model.Cardapio;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    CardapioBd bdHelper;
    ArrayList<Cardapio> listview_Cardapio;
    Cardapio cardapio;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //botao cadastrar
        Button btnCadastrar = (Button) findViewById(R.id.btn_Cadastrar);
        btnCadastrar.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FormularioCardapio.class);
                startActivity(intent);
            }
        });

        lista = (ListView) findViewById(R.id.listview_Cardapio);
        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long l) {
                Cardapio cardapioEscolhido = (Cardapio) adapter.getItemAtPosition(position);
                Intent i = new Intent(MainActivity.this, FormularioCardapio.class);
                i.putExtra("cardapio-escolhido", cardapioEscolhido);
                startActivity(i);
            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                cardapio = (Cardapio) adapterView.getItemAtPosition(i);
                return false;
            }
        });
    }

            @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem menuDelete = menu.add("Deletar esse Item");
        menuDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                bdHelper = new CardapioBd(MainActivity.this);
                bdHelper.deletarCardapio(cardapio);
                bdHelper.close();
                carregarCardapio();
                return true;
            }
        });
    }


    protected void onResume(){
        super.onResume();
        carregarCardapio();
    }

    public void carregarCardapio(){
        bdHelper = new CardapioBd(MainActivity.this);
        listview_Cardapio = bdHelper.getLista();
        bdHelper.close();

        if(listview_Cardapio != null){
            adapter = new ArrayAdapter<Cardapio>(MainActivity.this, android.R.layout.simple_list_item_1, listview_Cardapio);
            lista.setAdapter(adapter);
        }
       // finish();

    }

}