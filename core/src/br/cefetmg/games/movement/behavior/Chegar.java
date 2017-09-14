package br.cefetmg.games.movement.behavior;

import br.cefetmg.games.movement.AlgoritmoMovimentacao;
import br.cefetmg.games.movement.Direcionamento;
import br.cefetmg.games.movement.Pose;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector3;

/**
 * Guia o agente na direção do alvo.
 *
 * @author Flávio Coutinho <fegemo@cefetmg.br>
 */
public class Chegar extends AlgoritmoMovimentacao {

    private static final char NOME = 'c';
    private final int radius;
    private final float timeToTarget;
    

    public Chegar(float maxVelocidade) {
        this(NOME, maxVelocidade);
    }

    protected Chegar(char nome, float maxVelocidade) {
        super(nome);
        this.maxVelocidade = maxVelocidade;
        this.radius = 10;
        this.timeToTarget = 0.25f;
        
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        
        
        Direcionamento output = new Direcionamento();
        float x,y,z;
        x = super.alvo.getObjetivo().x - agente.posicao.x;
        y = super.alvo.getObjetivo().y - agente.posicao.y;
        z = super.alvo.getObjetivo().z - agente.posicao.z;
        
        Vector3 velocity = new Vector3(x,y,z);
        output.velocidade = velocity;
        agente.olharNaDirecaoDaVelocidade(velocity);
        output.rotacao=0;
        
        if(output.velocidade.len()<radius){
            return output;
        }
        output.velocidade.scl(1/timeToTarget);
        if(output.velocidade.len()>maxVelocidade){
            output.velocidade.nor();
            output.velocidade.scl(maxVelocidade);
        }
        
        
        // calcula que direção tomar (configura um objeto Direcionamento 
        // e o retorna)
        // ...
        // super.alvo já contém a posição do alvo
        // agente (parâmetro) é a pose do agente que estamos guiando
        // ...
        return output;
    }


    @Override
    public int getTeclaParaAtivacao() {
        return Keys.C;
    }
}
