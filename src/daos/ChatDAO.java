package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import entidades.Chat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.types.ObjectId;

public class ChatDAO extends baseDAO<Chat> {

	@Override
	protected MongoCollection<Chat> getCollecion() {
		return this.getDataBase().getCollection("chat", Chat.class);
	}

	@Override
	public void agregar(Chat entidad) {
		try {
			MongoCollection<Chat> coleccion = this.getCollecion();
			coleccion.insertOne(entidad);
			JOptionPane.showMessageDialog(null, "Se ha guardado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al guardar", "Informacion", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void actualizar(Chat entidad) {
		if (entidad.getId() == null) {
			System.out.println("la id del Usuario es invalida");
		} else {
			MongoCollection<Chat> coleccion = this.getCollecion();
			Document filtro = new Document();
			filtro.append("_id", entidad.getId());

			Document entidadActualizado = new Document();
			entidadActualizado.append("$set", new Document("receptor", entidad.getReceptor())
			.append("emisor", entidad.getEmisor())
			.append("historial", entidad.getHistorial()));

			UpdateResult resultado = coleccion.updateOne(filtro, entidadActualizado);

			if (resultado.getModifiedCount() == 0) {
				System.out.println("No se ha actualizado");
			} else {
				System.out.println("se ha actualizado");
			}
		}
	}

	@Override
	public void eliminar(String idEntidad) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public Chat consultar(String idEntidad) {
		List<Chat> listaChat = new ArrayList<>();
		MongoCollection<Chat> coleccion = this.getCollecion();
		Document filtro = new Document();
		filtro.append("_id", new ObjectId(idEntidad));
		coleccion.find(filtro).into(listaChat);
		if (listaChat == null) {
			return null;
		} else {
			return listaChat.get(0);
		}
	}

	@Override
	public List<Chat> consultarTodo() {
		List<Chat> listaChat = new ArrayList<>();
		MongoCollection<Chat> coleccion = this.getCollecion();
		coleccion.find().into(listaChat);
		return listaChat;
	}

}
