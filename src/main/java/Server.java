import classes.MensagemDTO;
import classes.SocketDTO;

import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

public class Server {
    public Server(int port, int buffer, String nome) throws IOException, ClassNotFoundException {
        MensagemDTO mensagemDTO = MensagemDTO.builder().porta(port).nome(nome).build();
        SocketDTO socketDTO = new SocketDTO(mensagemDTO,buffer);

        socketDTO.serverAccept();

        Scanner in = new Scanner(System.in);
        String mensagen;
        boolean continua = true;
        try {
            do {
                mensagen = socketDTO.read();
                System.out.println(mensagen);

                socketDTO.getMensagem().setMensagem("");
                System.out.print(socketDTO.getMensagem().toSTring());
                socketDTO.getMensagem().setMensagem(in.nextLine());
                socketDTO.write();

                if (mensagen.equals("end"))
                    continua = false;
            } while (continua);

        }catch (EOFException e){
            System.out.println("Chat ended");
        }
        socketDTO.close();
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Server server = new Server(5000, 10, "Ana");
    }
}
