package classes;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

@Getter
@Setter
public class SocketDTO {
    private ObjectOutputStream saida;
    private ObjectInputStream entrada;
    private ServerSocket servidor;
    private Socket conexao;
    private MensagemDTO mensagem;

    public SocketDTO(MensagemDTO mensagemDTO, int bufferLimit) throws IOException {
        if(bufferLimit == 0)
            initClient(mensagemDTO);
        else
            initServer(mensagemDTO,bufferLimit);
    }

    private void initServer(MensagemDTO mensagemDTO, int bufferLimit) throws IOException {
        servidor = new ServerSocket(mensagemDTO.getPorta(), bufferLimit);
        this.mensagem = mensagemDTO;
    }

    public void serverAccept() throws IOException {
        conexao = servidor.accept();
        saida = new ObjectOutputStream(conexao.getOutputStream());
        entrada = new ObjectInputStream(conexao.getInputStream());
    }

    private void initClient(MensagemDTO mensagemDTO) throws IOException {
        conexao = new Socket(mensagemDTO.getIp(), mensagemDTO.getPorta());
        this.mensagem = mensagemDTO;
        saida = new ObjectOutputStream(conexao.getOutputStream());
        saida.flush();
        entrada = new ObjectInputStream(conexao.getInputStream());
    }

    public void write() throws IOException {
        saida.writeObject(mensagem.toSTring());
        saida.flush();
    }

    public String read() throws IOException, ClassNotFoundException {
        return (String) entrada.readObject();
    }

    public void close() throws IOException {
        saida.close();
        entrada.close();
        conexao.close();
    }
}
