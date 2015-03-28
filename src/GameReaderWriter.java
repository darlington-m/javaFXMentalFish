import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Used to read and write Game Score array values to score.ser file.
 * 
 * 
 * @author Darlington Moyo
 *
 */
public class GameReaderWriter {

	private String fileName;

	/**
	 * @param fileName
	 */
	public GameReaderWriter(String fileName) {
		super();
		this.fileName = fileName;
	}

	/**
	 * @param library
	 * @throws IOException
	 */
	public void write(ScoreLibrary library) throws IOException {
		OutputStream file = new FileOutputStream(fileName);
		OutputStream buffer = new BufferedOutputStream(file);
		ObjectOutput output = new ObjectOutputStream(buffer);
		output.writeObject(library);
		output.close();
		buffer.close();
		file.close();
	}

	/**
	 * @return
	 * @throws MyIOException
	 * @throws ClassNotFoundException
	 */
	public ScoreLibrary read() throws Exception {

		ScoreLibrary readInventory = null;

		try {
			InputStream file = new FileInputStream(fileName);
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			readInventory = (ScoreLibrary) input.readObject();
			input.close();
			buffer.close();
			file.close();
			return readInventory;
		} catch (IOException e) {

		}
		return readInventory;
	}
}
