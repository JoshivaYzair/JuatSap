package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;

public class Usuarios {

	private ObjectId _id;
	private String nombre;
	private String correo;
	private String contraseña;
	private String sexo;
	private int edad;
	private List<ObjectId> listaChat;

	public Usuarios() {
		this.listaChat = new ArrayList<>();
	}

	public Usuarios(String nombre, String correo, String contraseña, String sexo, int edad) {
		this._id = new ObjectId();
		this.nombre = nombre;
		this.correo = correo;
		this.contraseña = contraseña;
		this.sexo = sexo;
		this.edad = edad;
		this.listaChat = new ArrayList<>();
	}

	public Usuarios(ObjectId _id, String nombre, String correo, String contraseña, String sexo, int edad) {
		this._id = _id;
		this.nombre = nombre;
		this.correo = correo;
		this.contraseña = contraseña;
		this.sexo = sexo;
		this.edad = edad;
		this.listaChat = new ArrayList<>();
	}

	public ObjectId getId() {
		return _id;
	}

	public void setId(ObjectId _id) {
		this._id = _id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public List<ObjectId> getListaChat() {
		return listaChat;
	}

	public void setListaChat(List<ObjectId> listaChat) {
		this.listaChat = listaChat;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + Objects.hashCode(this._id);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Usuarios other = (Usuarios) obj;
		if (!Objects.equals(this._id, other._id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}

}
