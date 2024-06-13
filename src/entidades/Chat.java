package entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bson.types.ObjectId;

public class Chat {

	private ObjectId _id;
	private ObjectId receptor;
	private ObjectId emisor;
	private List<Mensajes> historial;

	public Chat() {
		this._id = new ObjectId();
	}

	public Chat(ObjectId receptor, ObjectId emisor) {
		this._id = new ObjectId();
		this.receptor = receptor;
		this.emisor = emisor;
		this.historial = new ArrayList<>();
	}

	public Chat(ObjectId _id, ObjectId receptor, ObjectId emisor, List<Mensajes> historial) {
		this._id = _id;
		this.receptor = receptor;
		this.emisor = emisor;
		this.historial = historial;
	}

	public ObjectId getId() {
		return _id;
	}

	public void setId(ObjectId _id) {
		this._id = _id;
	}

	public ObjectId getReceptor() {
		return receptor;
	}

	public void setReceptor(ObjectId receptor) {
		this.receptor = receptor;
	}

	public ObjectId getEmisor() {
		return emisor;
	}

	public void setEmisor(ObjectId emisor) {
		this.emisor = emisor;
	}

	public List<Mensajes> getHistorial() {
		return historial;
	}

	public void setHistorial(List<Mensajes> historial) {
		this.historial = historial;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 41 * hash + Objects.hashCode(this._id);
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
		final Chat other = (Chat) obj;
		if (!Objects.equals(this._id, other._id)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Chat{" + "_id=" + _id.toHexString() + ", receptor=" + receptor.toHexString() + ", emisor=" + emisor.toHexString() + '}';
	}
}
