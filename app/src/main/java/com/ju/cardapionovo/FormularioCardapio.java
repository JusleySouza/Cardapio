package com.ju.cardapionovo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ju.cardapionovo.BDHelper.CardapioBd;
import com.ju.cardapionovo.model.Cardapio;

public class FormularioCardapio extends AppCompatActivity {

    EditText editText_Nome, editText_Tipo, editText_Valor, editText_Descricao, editText_Rendimento;
    Button btn_Polimorf;
    Cardapio editarCardapio, cardapio;
    CardapioBd bdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cardapio);

        cardapio = new Cardapio();
        bdHelper = new CardapioBd(FormularioCardapio.this);

        Intent intent = getIntent();
        editarCardapio = (Cardapio) intent.getSerializableExtra("cardapio-escolhido");

        editText_Nome = (EditText) findViewById(R.id.editText_Nome);
        editText_Tipo = (EditText) findViewById(R.id.editText_Tipo);
        editText_Valor = (EditText) findViewById(R.id.editText_Valor);
        editText_Descricao = (EditText) findViewById(R.id.editText_Descricao);
        editText_Rendimento = (EditText) findViewById(R.id.editText_Rendimento);

        btn_Polimorf = (Button) findViewById(R.id.btn_Polimorf);

        if(editarCardapio !=null){
            btn_Polimorf.setText("Modificar");

            editText_Nome.setText(editarCardapio.getNome());
            editText_Tipo.setText(editarCardapio.getTipo());
            editText_Valor.setText(editarCardapio.getValor()+"");
            editText_Descricao.setText(editarCardapio.getDescricao());
            editText_Rendimento.setText(editarCardapio.getRendimento()+"");

            cardapio.setId(editarCardapio.getId());

        }
        else{
            btn_Polimorf.setText("Cadastrar");
        }

        btn_Polimorf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardapio.setNome(editText_Nome.getText().toString());
                cardapio.setTipo(editText_Tipo.getText().toString());
                cardapio.setValor(Double.parseDouble(editText_Valor.getText().toString()));
                cardapio.setDescricao(editText_Descricao.getText().toString());
                cardapio.setRendimento(Integer.parseInt(editText_Rendimento.getText().toString()));

                if(btn_Polimorf.getText().toString().equals("Cadastrar")){
                    bdHelper.salvarCardapio(cardapio);
                    bdHelper.close();

                }
                else{
                    bdHelper.alterarCardapio(cardapio);
                    bdHelper.close();
                }
                Intent intent = new Intent(FormularioCardapio.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }
}