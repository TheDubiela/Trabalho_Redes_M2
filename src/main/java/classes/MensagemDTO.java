package classes;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Builder
public class MensagemDTO {

    private int porta;
    private String ip;
    private String nome;
    private String mensagem;
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private String getTime() {
        return new Timestamp(new Date().getTime()).toString();
    }

    public String toSTring() {
        return "[" + getTime() + "] " + getNome() + " : " + getMensagem();
    }
}
