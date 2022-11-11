package com.ju.cardapionovo.BDHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ju.cardapionovo.model.Cardapio;

import java.util.ArrayList;

public class CardapioBd extends SQLiteOpenHelper {

    private static final String DATABASE= "bdcardapios";
    private static final int VERSION = 1;

    public CardapioBd (Context context){
        super(context, DATABASE,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String cardapio = "CREATE TABLE cardapios(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, nomeprato TEXT NOT NULL, tipo  TEXT NOT NULL, valor DOUBLE NOT NULL, descricao TEXT NOT NULL, rendimento INTEGER NOT NULL)";
        sqLiteDatabase.execSQL(cardapio);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String cardapio = "DROP TABLE IF EXISTS cardapios";
        sqLiteDatabase.execSQL(cardapio);
    }

    //salvar
    public void salvarCardapio(Cardapio cardapio){
        ContentValues values = new ContentValues();

        values.put("nomeprato", cardapio.getNome());
        values.put("tipo", cardapio.getTipo());
        values.put("valor", cardapio.getValor());
        values.put("descricao", cardapio.getDescricao());
        values.put("rendimento", cardapio.getRendimento());

        getWritableDatabase().insert("cardapios",null,values);
    }

    //alterar
    public void alterarCardapio(Cardapio cardapio){
        ContentValues values = new ContentValues();

        values.put("nomeprato", cardapio.getNome());
        values.put("tipo", cardapio.getTipo());
        values.put("valor", cardapio.getValor());
        values.put("descricao", cardapio.getDescricao());
        values.put("rendimento", cardapio.getRendimento());

        String [] args = {cardapio.getId().toString()};
        getWritableDatabase().update("cardapios",values, "id=?", args);
    }

    //deletar
    public void deletarCardapio(Cardapio cardapio){
        String [] args = {cardapio.getId().toString()};
        getWritableDatabase().delete("cardapios", "id=?", args);
    }

    //listar
    public ArrayList<Cardapio> getLista(){
        String [] columns= {"id", "nomeprato", "tipo", "valor", "descricao", "rendimento"};
        Cursor cursor = getWritableDatabase().query("cardapios",columns,null,null,null,null,null,null);
        ArrayList<Cardapio> cardapios = new ArrayList<Cardapio>();

        while(cursor.moveToNext()){
            Cardapio cardapio = new Cardapio();
            cardapio.setId(cursor.getLong(0));
            cardapio.setNome(cursor.getString(1));
            cardapio.setTipo(cursor.getString(2));
            cardapio.setValor(cursor.getDouble(3));
            cardapio.setDescricao(cursor.getString(4));
            cardapio.setRendimento(cursor.getInt(5));

            cardapios.add(cardapio);
        }
        return cardapios;
    }


}
