package control;

import daos.UsuariosDAO;
import entidades.Usuarios;
import guis.FrameChat;
import guis.FrameInicio;
import guis.FrameRegistrarCuenta;
import java.util.List;
import javax.swing.JOptionPane;

public class ControlInicio {
    private FrameInicio formInicio;
    private FrameRegistrarCuenta formRegistro;
    private FrameChat formChat;
    private UsuariosDAO usuariosDAO;

    public ControlInicio(FrameInicio formInicio, FrameRegistrarCuenta formRegistro, FrameChat formChat,UsuariosDAO usuariosDAO) {
        this.formInicio = formInicio;
        this.formRegistro = formRegistro;
        this.formChat = formChat;
        this.usuariosDAO = usuariosDAO;
    }
    
    public void iniciarSesion(){
        List<Usuarios> lis = usuariosDAO.consultarTodo();
        Boolean ingreso=false;
        for (Usuarios usuario:lis) {
            if (this.formInicio.txtCorreo.getText().equalsIgnoreCase(usuario.getCorreo() )&& 
                this.formInicio.txtContraseña.getText().equalsIgnoreCase(usuario.getContraseña())) {
                ingreso=true;
                this.cancelarInicio();
                this.formChat.setVisible(true);
                this.formChat.setUsuarioLog(usuario);
                this.formChat.borrarContactos();
                this.formChat.borrarHistorial();
                this.formChat.cargar();
            }
        }
        if (ingreso==false) {
                JOptionPane.showMessageDialog(null, "Ingreso de sesion fallido", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } 
    }
    
    public void registrarCuenta(){
        if (this.formRegistro.txtNombre.getText().isEmpty() && 
            this.formRegistro.txtEdad.getText().isEmpty() &&
            this.formRegistro.txtSexo.getText().isEmpty() &&
            this.formRegistro.txtCorreo.getText().isEmpty() &&
            this.formRegistro.txtContraseña.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Faltan datos", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }else{
            boolean registrar=true;
            List<Usuarios> listaUsu = usuariosDAO.consultarTodo();
            for (Usuarios us:listaUsu) {
                if (us.getCorreo().equalsIgnoreCase(this.formRegistro.txtCorreo.getText())) {
                    registrar=false;
                    JOptionPane.showMessageDialog(null, "Este correo ya esta registrado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            if (registrar==true) {
                Usuarios usuario = new Usuarios();
                usuario.setNombre(this.formRegistro.txtNombre.getText());
                usuario.setSexo(this.formRegistro.txtSexo.getText());
                usuario.setEdad(Integer.parseInt(this.formRegistro.txtEdad.getText()));
                usuario.setCorreo(this.formRegistro.txtCorreo.getText());
                usuario.setContraseña(this.formRegistro.txtContraseña.getText());
                usuariosDAO.agregar(usuario);
                this.cancelarRegistro();
            }
        }
    }
    
    public void cancelarRegistro(){
        this.formRegistro.txtNombre.setText("Nombre completo");
        this.formRegistro.txtCorreo.setText("Correo electronico");
        this.formRegistro.txtContraseña.setText("Contraseña");
        this.formRegistro.txtEdad.setText("Edad");
        this.formRegistro.txtSexo.setText("Sexo");
    }
    
    public void cancelarInicio(){
        this.formInicio.txtCorreo.setText("");
        this.formInicio.txtContraseña.setText("");
    }
}
