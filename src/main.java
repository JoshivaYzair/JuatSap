
import daos.ChatDAO;
import daos.UsuariosDAO;
import entidades.Chat;
import entidades.Mensajes;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author joshi
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ChatDAO ch = new ChatDAO();
        List<Chat> lista = ch.consultarTodo();
        for (int i = 0; i < ch.consultarTodo().size(); i++) {
            System.out.println(lista.get(i));
            
            for (int j = 0; j < lista.get(i).getHistorial().size(); j++) {
                System.out.println(lista.get(i).getHistorial().get(i));
            }
            System.out.println("asdadsa");
        }
        
        Chat chat = new Chat();
        chat = ch.consultar("61abc3ba74efbd225cd25f75");
        System.out.println(chat.getHistorial().get(0));
    }
    
    
}
