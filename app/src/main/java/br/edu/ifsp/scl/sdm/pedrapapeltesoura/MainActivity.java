package br.edu.ifsp.scl.sdm.pedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Random;

import br.edu.ifsp.scl.sdm.pedrapapeltesoura.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.twoPlayersBt.setOnClickListener((buttonView) -> {
            activityMainBinding.playLl.setVisibility(buttonView.isClickable() ? View.VISIBLE : View.VISIBLE);
            activityMainBinding.selecionarJogadoresLl.setVisibility(buttonView.isClickable() ? View.GONE : View.GONE);
            activityMainBinding.playerPcDoisLl.setVisibility(buttonView.isClickable() ? View.GONE : View.GONE);
        });
        activityMainBinding.threePlayersBt.setOnClickListener((buttonView) -> {
            activityMainBinding.playLl.setVisibility(buttonView.isClickable() ? View.VISIBLE : View.VISIBLE);
            activityMainBinding.selecionarJogadoresLl.setVisibility(buttonView.isClickable() ? View.GONE : View.GONE);
            activityMainBinding.playerPcDoisLl.setVisibility(buttonView.isClickable() ? View.VISIBLE : View.VISIBLE);
        });
    }

    public void onClick(View view) {
        String jogada = null;
        switch (view.getId()) {
            case R.id.pedraIb:
                jogada = "pedra";
                break;
            case R.id.papelIb:
                jogada = "papel";
                break;
            case R.id.tesouraIb:
                jogada = "tesoura";
                break;
            default:
                break;
        }
        jogarPedraPapelTesoura(jogada);
    }

    private void jogarPedraPapelTesoura(String jogada) {
        Random random = new Random(System.currentTimeMillis());
        int jogadaComputadorUm = random.nextInt(3);
        int jogadaComputadorDois = random.nextInt(3);
        String jogadaPcUm = null;
        String jogadaPcDois = null;

        // Setando imagem na jogada do computador
        int imagemJogadaComputadorUmId = -1;
        switch (jogadaComputadorUm) {
            case 0:
                jogadaPcUm = "pedra";
                imagemJogadaComputadorUmId = R.mipmap.pedra;
                break;
            case 1:
                jogadaPcUm = "papel";
                imagemJogadaComputadorUmId = R.mipmap.papel;
                break;
            case 2:
                jogadaPcUm = "tesoura";
                imagemJogadaComputadorUmId = R.mipmap.tesoura;
                break;
            default:
                break;
        }

        int imagemJogadaComputadorDoisId = -1;
        switch (jogadaComputadorDois) {
            case 0:
                jogadaPcDois = "pedra";
                imagemJogadaComputadorDoisId = R.mipmap.pedra;
                break;
            case 1:
                jogadaPcDois = "papel";
                imagemJogadaComputadorDoisId = R.mipmap.papel;
                break;
            case 2:
                jogadaPcDois = "tesoura";
                imagemJogadaComputadorDoisId = R.mipmap.tesoura;
                break;
            default:
                break;
        }
        activityMainBinding.jogadaComputadorUmIv.setImageResource(imagemJogadaComputadorUmId);

        StringBuilder resultadoSb = new StringBuilder();
        resultadoSb.append("Sua jogada: ");
        resultadoSb.append(jogada);
        resultadoSb.append(", ");

        resultadoSb.append(", Computador 1: ");
        resultadoSb.append(jogadaPcUm);
        resultadoSb.append(", ");

        if (activityMainBinding.jogadaComputadorDoisIv.isShown()){
            activityMainBinding.jogadaComputadorDoisIv.setImageResource(imagemJogadaComputadorDoisId);
            resultadoSb.append("Computador 2: ");
            resultadoSb.append(jogadaPcDois);
            resultadoSb.append("\n");

            if (activityMainBinding.pedraIb.isPressed()){
                resultadoSb.append(jogadaPcUm.equals("tesoura") && jogadaPcDois.equals("tesoura") ? "VOCÊ GANHOU"
                        : (jogadaPcUm.equals("papel") && jogadaPcDois.equals("papel")) ? "VOCÊ PERDEU"
                        : "DEU EMPATE");
            } else if (activityMainBinding.papelIb.isPressed()){
                resultadoSb.append((jogadaPcUm.equals("pedra") && jogadaPcDois.equals("pedra")) ? "VOCÊ GANHOU"
                        : (jogadaPcUm.equals("tesoura") && jogadaPcDois.equals("tesoura")) ? "VOCÊ PERDEU"
                        : "DEU EMPATE");
            } else if (activityMainBinding.tesouraIb.isPressed()){
                resultadoSb.append((jogadaPcUm.equals("papel") && jogadaPcDois.equals("papel")) ? "VOCÊ GANHOU"
                        : (jogadaPcUm.equals("pedra") && jogadaPcDois.equals("pedra")) ? "DEU PERDEU"
                        : "DEU EMPATE");
            }
        }

        if (!activityMainBinding.jogadaComputadorDoisIv.isShown()){
            if (activityMainBinding.pedraIb.isPressed()){
                resultadoSb.append(jogadaPcUm.equals("tesoura") ? "VOCÊ GANHOU" : jogadaPcUm.equals("pedra") ? "DEU EMPATE" : "VOCÊ PERDEU");
            } else if (activityMainBinding.papelIb.isPressed()){
                resultadoSb.append(jogadaPcUm.equals("pedra") ? "VOCÊ GANHOU" : jogadaPcUm.equals("papel") ? "DEU EMPATE" : "VOCÊ PERDEU");
            } else if (activityMainBinding.tesouraIb.isPressed()){
                resultadoSb.append(jogadaPcUm.equals("papel") ? "VOCÊ GANHOU" : jogadaPcUm.equals("tesoura") ? "DEU EMPATE" : "VOCÊ PERDEU");
            }
        }


        activityMainBinding.resultadoTv.setText(resultadoSb.toString());
    }

    public void resetGame(View view){
        activityMainBinding.resetGameBt.setOnClickListener((buttonView) -> {
            activityMainBinding.jogadaComputadorUmIv.setImageResource(0);
            activityMainBinding.jogadaComputadorDoisIv.setImageResource(0);
            activityMainBinding.resultadoTv.setText("");
            activityMainBinding.playLl.setVisibility(buttonView.isClickable() ? View.GONE : View.VISIBLE);
            activityMainBinding.selecionarJogadoresLl.setVisibility(buttonView.isClickable() ? View.VISIBLE : View.GONE);
        });
    }
}