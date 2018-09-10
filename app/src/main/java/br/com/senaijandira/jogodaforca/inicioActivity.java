package br.com.senaijandira.jogodaforca;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class inicioActivity extends Activity {

    String [] palavra = {"OBSERVAR", "OBJETIVO","ORGULHO", "PALMEIRAS", "SENAI", "PARADIGMA", "MURILO" };
    ArrayList<String> letras= new ArrayList<String>();

    int indexPalavra = 0;
    int letrasEncontradas;
    Button btnTmp;
    String letraAtual;
    MediaPlayer mediaPlayer;

    public void btnclick(View v){
        Button btnTmp = (Button) v;

        btnTmp.setEnabled(false);
        btnTmp.setBackgroundColor(Color.parseColor("#892489"));
        letras.add(btnTmp.getText().toString().toUpperCase());
        /* *********** MÚSICA ************** */
        mediaPlayer = MediaPlayer.create(this, R.raw.certo);
        mediaPlayer.start();

//        if(Jogo() == palavra[indexPalavra].length()){
//           btnTmp.setBackgroundColor(Color.parseColor("0ff042"));
//        }else{
//            btnTmp.setBackgroundColor(Color.parseColor("#894292"));
//        }
        Jogo();
    }


    // ******** ALERT **********
    private void gameOver(){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Você Venceu!!!");
        alert.setMessage("Deseja Continuar?");


        //ALERT BOTÃO DE NEGAÇÃO
        alert.setNegativeButton("Finalizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }
        });

        //ALERT BOTÃO DE POSITIVO
        alert.setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //continuar o jogo
                Jogo();
            }
        });
        alert.create().show();
    }



    public boolean ler (String letra){
        for (int i=0; i<letras.size(); i++) {
            if(letra.equals(letras.get(i).toString())){

                return true;
            }

        }
        return false;
    }

    public void Jogo (){
        LinearLayout painel = findViewById(R.id.painelResposta);
        painel.removeAllViews();

        int letrasEncontradas = 0;
       // indexPalavra = 0;
        System.out.println("-----------------------------------------------------------------------------");

        for(int i=0; i<palavra[indexPalavra].length(); i++){
            System.out.println("letra atual: "+ palavra[indexPalavra].substring(i,i+1));
            String letraAtual =  palavra[indexPalavra].substring(i,i+1).toString();



            if (ler(letraAtual.toString())){
                TextView txtTemp = new TextView(this);
                txtTemp.setText(letraAtual);

                txtTemp.setWidth(50);
                txtTemp.setHeight(50);
                txtTemp.setTextSize(15);
                txtTemp.setTextColor(Color.parseColor("#ffffff"));

                //System.out.println("Acheei :"+letraAtual);

                painel.addView(txtTemp);

                letrasEncontradas++;
            }else {
                TextView txtTemp = new TextView(this);
                txtTemp.setText("_");

                txtTemp.setTextColor(Color.parseColor("#ffffff"));
                txtTemp.setWidth(50);
                txtTemp.setHeight(50);
                txtTemp.setTextSize(15);
                //System.out.println("Não achei a :"+letraAtual);
                painel.addView(txtTemp);

                letrasEncontradas--;
            }



        }


            //PRÓXIMA PALAVRA
            if(indexPalavra != 7 && letrasEncontradas == palavra[indexPalavra].length()){

                letras.clear();
                onCreate(null);
                indexPalavra++;

                gameOver();
                Jogo();
            }

            //VOLTAR PARA A PRIMEIRA PALAVRA
            if(indexPalavra == 7 && letrasEncontradas == palavra[indexPalavra].length() ){
                indexPalavra = 0;

                letras.clear();

                onCreate(null);
                gameOver();
                Jogo();
            }

        }


    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_inicio);
        Jogo();
    }

}
