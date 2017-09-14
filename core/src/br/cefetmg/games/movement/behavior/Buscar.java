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
public class Buscar extends AlgoritmoMovimentacao {

    private static final char NOME = 's';

    public Buscar(float maxVelocidade) {
        this(NOME, maxVelocidade);
    }

    protected Buscar(char nome, float maxVelocidade) {
        super(nome);
        this.maxVelocidade = maxVelocidade;
    }

    @Override
    public Direcionamento guiar(Pose agente) {
        
        
        Direcionamento output = new Direcionamento();
        float x,y,z;
        x = super.alvo.getObjetivo().x - agente.posicao.x;
        y = super.alvo.getObjetivo().y - agente.posicao.y;
        z = super.alvo.getObjetivo().z - agente.posicao.z;
        
        Vector3 velocity = new Vector3(x,y,z);
        velocity.nor();
        velocity.scl(maxVelocidade);
        output.velocidade = velocity;
        agente.olharNaDirecaoDaVelocidade(velocity);
        output.rotacao=0;
        
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
        return Keys.S;
    }
}
