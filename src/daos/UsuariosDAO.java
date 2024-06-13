package daos;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import entidades.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.bson.Document;
import org.bson.types.ObjectId;

public class UsuariosDAO extends baseDAO<Usuarios> {

	@Override
	protected MongoCollection<Usuarios> getCollecion() {
		return this.getDataBase().getCollection("usuarios", Usuarios.class);
	}

	@Override
	public void agregar(Usuarios entidad) {
		try {
			MongoCollection<Usuarios> coleccion = this.getCollecion();
			coleccion.insertOne(entidad);
			JOptionPane.showMessageDialog(null, "Se ha guardado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al guardar", "Informacion", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@Override
	public void actualizar(Usuarios entidad) {
		if (entidad.getId() == null) {
			System.out.println("la id del Usuario es invalida");
		} else {
			MongoCollection<Usuarios> coleccion = this.getCollecion();
			Document filtro = new Document();
			filtro.append("_id", entidad.getId());

			Document entidadActualizado = new Document();
			entidadActualizado.append("$set", new Document("nombre", entidad.getNombre())
			.append("correo", entidad.getCorreo())
			.append("sexo", entidad.getSexo())
			.append("edad", entidad.getEdad())
			.append("contraseña", entidad.getContraseña())
			.append("listaChat", entidad.getListaChat()));

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
		MongoCollection<Usuarios> coleccion = this.getCollecion();
		Document filtro = new Document();
		filtro.append("_id", new ObjectId(idEntidad));
		DeleteResult resultado = coleccion.deleteOne(filtro);
		if (resultado.getDeletedCount() == 0) {
			System.out.println("El usuario no existe");
		} else {
			System.out.println("el usuario se elimino");
		}
	}

	@Override
	public Usuarios consultar(String idEntidad) {
		List<Usuarios> listaUsuarios = new ArrayList<>();
		MongoCollection<Usuarios> coleccion = this.getCollecion();
		Document filtro = new Document();
		filtro.append("_id", new ObjectId(idEntidad));
		coleccion.find(filtro).into(listaUsuarios);
		if (listaUsuarios == null) {
			return null;
		} else {
			return listaUsuarios.get(0);
		}
	}

	@Override
	public List<Usuarios> consultarTodo() {
		List<Usuarios> listaUsuarios = new ArrayList<>();
		MongoCollection<Usuarios> coleccion = this.getCollecion();
		coleccion.find().into(listaUsuarios);
		return listaUsuarios;
	}

}
