package control;

import daos.ChatDAO;
import daos.UsuariosDAO;
import entidades.Chat;
import entidades.Mensajes;
import entidades.Usuarios;
import guis.FrameChat;
import guis.FrameNuevoChat;
import guis.FramePerfil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;

public class ControlChat {

	private UsuariosDAO usuariosDAO;
	private ChatDAO chatDAO;
	private FrameChat formChat;
	private FrameNuevoChat formNC;
	private FramePerfil formPerfil;
	private DefaultListModel modelo;

	public ControlChat(UsuariosDAO usuariosDAO, ChatDAO chatDAO, FrameChat formChat, FrameNuevoChat formNC, FramePerfil formPerfil) {
		this.usuariosDAO = usuariosDAO;
		this.chatDAO = chatDAO;
		this.formChat = formChat;
		this.formNC = formNC;
		this.formPerfil = formPerfil;
		modelo = new DefaultListModel();
	}

	public void cargarModelo() {
		this.formChat.jlContactos.setModel(modelo);
	}

	public void borrarContactos() {
		this.modelo.removeAllElements();
	}

	public void nuevoChat() {
		this.formNC.setVisible(true);
		Usuarios usuarioLog = this.formChat.getUsuarioLog();
		this.formNC.cbUsuarios.removeAllItems();
		List<Usuarios> listaUsuarios = usuariosDAO.consultarTodo();
		for (Usuarios us : listaUsuarios) {
			if (!usuarioLog.getId().toHexString().equalsIgnoreCase(us.getId().toHexString())) {
				this.formNC.cbUsuarios.addItem(us);
			}
		}
	}

	public void cargarContactosChat() {
		this.cargarModelo();
		Usuarios usuarioLog = this.formChat.getUsuarioLog();
		List<Chat> ch = new ArrayList<>();
		for (int i = 0; i < usuarioLog.getListaChat().size(); i++) {
			ch.add(chatDAO.consultar(usuarioLog.getListaChat().get(i).toHexString()));
		}
		if (!ch.isEmpty()) {
			for (int i = 0; i < ch.size(); i++) {
				if (usuarioLog.getId().toHexString().equalsIgnoreCase(ch.get(i).getReceptor().toHexString())) {
					Usuarios us = usuariosDAO.consultar(ch.get(i).getEmisor().toHexString());
					modelo.addElement(us);
				} else {
					Usuarios us = usuariosDAO.consultar(ch.get(i).getReceptor().toHexString());
					modelo.addElement(us);
				}
			}
		}
	}

	public void aceptarNuevoChat() {
		this.cargarModelo();
		List<Usuarios> lista = new ArrayList<>();
		Usuarios us = (Usuarios) this.formNC.cbUsuarios.getSelectedItem();
		boolean agregar = true;
		for (int i = 0; i < modelo.size(); i++) {
			Usuarios us2 = (Usuarios) modelo.get(i);
			lista.add(us2);
			if (us.getId().toHexString().equalsIgnoreCase(us2.getId().toHexString())) {
				agregar = false;
				JOptionPane.showMessageDialog(null, "Este usuario ya esta en la lista", "Informacion", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		if (agregar == true) {
			modelo.addElement(us);
			Chat chat = new Chat();
			List<Mensajes> listaMensajes = new ArrayList<>();
			Usuarios usuarioLog = this.formChat.getUsuarioLog();
			chat.setEmisor(usuarioLog.getId());
			chat.setReceptor(us.getId());
			chat.setHistorial(listaMensajes);
			chatDAO.agregar(chat);
			List<ObjectId> emisor = new ArrayList<>();
			List<ObjectId> receptor = new ArrayList<>();

			emisor = usuarioLog.getListaChat();
			receptor = us.getListaChat();

			emisor.add(chat.getId());
			receptor.add(chat.getId());

			usuarioLog.setListaChat(emisor);
			us.setListaChat(receptor);

			usuariosDAO.actualizar(us);
			usuariosDAO.actualizar(usuarioLog);
			this.formNC.setVisible(false);
		}
	}

	public void borrarHistorial() {
		this.formChat.txtChat.setText("");
		this.formChat.txtEscribir.setText("");
	}

	public void cargarHistorialChat() {
		this.borrarHistorial();
		if (this.formChat.jlContactos.getSelectedValue() != null) {
			List<Chat> chatUsuarioLog = new ArrayList<>();
			Usuarios usuarioLog = this.formChat.getUsuarioLog();
			for (int i = 0; i < usuarioLog.getListaChat().size(); i++) {
				chatUsuarioLog.add(chatDAO.consultar(usuarioLog.getListaChat().get(i).toHexString()));
			}
			Usuarios usuarioSeleccionado = this.formChat.jlContactos.getSelectedValue();

			for (int i = 0; i < chatUsuarioLog.size(); i++) {
				if (usuarioSeleccionado.getId().toHexString().equalsIgnoreCase(chatUsuarioLog.get(i).getEmisor().toHexString())
				|| usuarioSeleccionado.getId().toHexString().equalsIgnoreCase(chatUsuarioLog.get(i).getReceptor().toHexString())) {
					List<Mensajes> listaMensajes = chatUsuarioLog.get(i).getHistorial();
					for (int j = 0; j < listaMensajes.size(); j++) {
						String mensaje = listaMensajes.get(j).getMensaje();
						String fecha = listaMensajes.get(j).getFecha();
						Usuarios emisor = usuariosDAO.consultar(listaMensajes.get(j).getEmisor().toHexString());
//                    this.formChat.txtChat.setText("Mensaje de: "+emisor.getNombre()+" enviado el: "+ fecha +"\n"+ mensaje+"\n");
						this.formChat.txtChat.append("Mensaje de: " + emisor.getNombre() + " enviado el: " + fecha + "\n" + mensaje + "\n");
					}
				}
			}
		}
	}

	public void enviarMensaje() {
		if (this.formChat.jlContactos.getSelectedValue() == null) {
			JOptionPane.showMessageDialog(null, "Seleccione un chat", "Información", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Usuarios emisor = this.formChat.getUsuarioLog();
			Usuarios receptor = this.formChat.jlContactos.getSelectedValue();
			ObjectId receptorId = receptor.getId();
			ObjectId emisorId = emisor.getId();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm");
			Date fecha = new Date();
			String strDate = dateFormat.format(fecha);
			String chat = this.formChat.txtEscribir.getText();
			Mensajes mensaje = new Mensajes(emisorId, strDate, chat);
			List<Chat> listaChatReceptor = new ArrayList<>();

			for (ObjectId idChat : receptor.getListaChat()) {
				listaChatReceptor.add(chatDAO.consultar(idChat.toHexString()));
			}

			for (int i = 0; i < listaChatReceptor.size(); i++) {
				if ((listaChatReceptor.get(i).getEmisor().toHexString().equalsIgnoreCase(emisorId.toHexString())
				|| listaChatReceptor.get(i).getReceptor().toHexString().equalsIgnoreCase(emisorId.toHexString()))
				&& (listaChatReceptor.get(i).getEmisor().toHexString().equalsIgnoreCase(receptorId.toHexString())
				|| listaChatReceptor.get(i).getReceptor().toHexString().equalsIgnoreCase(receptorId.toHexString()))) {

					Chat chat2 = listaChatReceptor.get(i);
					List<Mensajes> listaMensajeNueva = chat2.getHistorial();
					listaMensajeNueva.add(mensaje);
					chat2.setHistorial(listaMensajeNueva);
					chat2.setEmisor(emisorId);
					chat2.setReceptor(receptorId);
					chatDAO.actualizar(chat2);
				} else {
//                    Chat chatNuevo = new Chat(receptorId,emisorId);
//                    chatNuevo.setHistorial(Arrays.asList(mensaje));
//                    this.chatDAO.agregar(chatNuevo);
//            
//                    List<ObjectId> listachatEmisor= new ArrayList<>();
//                    List<ObjectId> listachatReceptor= new ArrayList<>();
//             
//                    listachatEmisor=emisor.getListaChat();
//                    listachatReceptor=receptor.getListaChat();
//             
//                    listachatEmisor.add(chatNuevo.getId());
//                    listachatReceptor.add(chatNuevo.getId());
//             
//                    emisor.setListaChat(listachatEmisor);
//                    receptor.setListaChat(listachatReceptor);
//             
//                    this.usuariosDAO.actualizar(emisor);
//                    this.usuariosDAO.actualizar(receptor);
				}
			}
			this.cargarHistorialChat();
		}
	}

	public void editarUsuario() {
		this.formPerfil.txtNombre.setEditable(true);
		this.formPerfil.txtSexo.setEditable(true);
		this.formPerfil.txtEdad.setEditable(true);
		this.formPerfil.txtContraseña.setEditable(true);
		this.formPerfil.txtCorreo.setEditable(true);

	}

	public void aceptarPerfil() {
		Usuarios usuLog = this.formChat.getUsuarioLog();
		usuLog.setNombre(this.formPerfil.txtNombre.getText());
		usuLog.setSexo(this.formPerfil.txtSexo.getText());
		usuLog.setEdad(Integer.valueOf(this.formPerfil.txtEdad.getText()));
		usuLog.setCorreo(this.formPerfil.txtCorreo.getText());
		usuLog.setContraseña(this.formPerfil.txtContraseña.getText());

		usuariosDAO.actualizar(usuLog);
		this.formPerfil.txtNombre.setEditable(false);
		this.formPerfil.txtSexo.setEditable(false);
		this.formPerfil.txtEdad.setEditable(false);
		this.formPerfil.txtContraseña.setEditable(false);
		this.formPerfil.txtCorreo.setEditable(false);
		this.formPerfil.setVisible(false);

	}

	public void mostrarPerfil() {
		this.formPerfil.setVisible(true);
		this.formPerfil.txtNombre.setEditable(false);
		this.formPerfil.txtSexo.setEditable(false);
		this.formPerfil.txtEdad.setEditable(false);
		this.formPerfil.txtContraseña.setEditable(false);
		this.formPerfil.txtCorreo.setEditable(false);
		Usuarios usuLog = this.formChat.getUsuarioLog();
		this.formPerfil.txtNombre.setText(usuLog.getNombre());
		this.formPerfil.txtSexo.setText(usuLog.getSexo());
		this.formPerfil.txtEdad.setText(String.valueOf(usuLog.getEdad()));
		this.formPerfil.txtCorreo.setText(usuLog.getCorreo());
		this.formPerfil.txtContraseña.setText(usuLog.getContraseña());
	}
}
