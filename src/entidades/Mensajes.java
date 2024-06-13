package entidades;

import org.bson.types.ObjectId;

public class Mensajes {

	private ObjectId emisor;
	private String fecha;
	private String mensaje;

	public Mensajes() {
	}

	public Mensajes(ObjectId emisor, String fecha, String mensaje) {
		this.emisor = emisor;
		this.fecha = fecha;
		this.mensaje = mensaje;
	}

	public ObjectId getEmisor() {
		return emisor;
	}

	public void setEmisor(ObjectId emisor) {
		this.emisor = emisor;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Mensajes{" + "emisor=" + emisor + ", fecha=" + fecha + ", mensaje=" + mensaje + '}';
	}

}
