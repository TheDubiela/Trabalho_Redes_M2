import classes.MensagemDTO;
import classes.SocketDTO;

import java.io.IOException;
import java.util.Scanner;

public class Client {
    public Client(String ip, int porta, String nome) throws IOException, ClassNotFoundException {
        MensagemDTO mensagemDTO = MensagemDTO.builder().ip(ip).porta(porta).nome(nome).build();
        SocketDTO socketDTO = new SocketDTO(mensagemDTO,0);

        Scanner in = new Scanner(System.in);
        boolean continua = true;
        do{
            socketDTO.getMensagem().setMensagem("");
            System.out.print(socketDTO.getMensagem().toSTring());
            socketDTO.getMensagem().setMensagem(in.nextLine());
            socketDTO.write();
            System.out.println(socketDTO.read());
            if(socketDTO.getMensagem().getMensagem().equals("end"))
                continua = false;
        }while(continua);

        socketDTO.close();
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Client client = new Client("localhost", 5000, "josé");
    }
}
