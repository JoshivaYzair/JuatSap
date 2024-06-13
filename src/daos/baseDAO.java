package daos;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public abstract class baseDAO<T> {

	private final String HOST = "localhost";
	private final int PUERTO = 27017;
	private final String BASE_DATO = "Sistema_chat";

	protected MongoDatabase getDataBase() {
		try {
			CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());

			CodecRegistry codeRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

			ConnectionString cadenaConexion = new ConnectionString("mongodb://" + HOST + "/" + PUERTO);

			MongoClientSettings clientSettings = MongoClientSettings.builder().applyConnectionString(cadenaConexion)
			.codecRegistry(codeRegistry)
			.build();

			MongoClient clienteMongo = MongoClients.create(clientSettings);

			MongoDatabase baseDatos = clienteMongo.getDatabase(BASE_DATO);
			return baseDatos;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	protected abstract MongoCollection<T> getCollecion();

	public abstract void agregar(T entidad);

	public abstract void actualizar(T entidad);

	public abstract void eliminar(String idEntidad);

	public abstract T consultar(String idEntidad);

	public abstract List<T> consultarTodo();
}
