package es.upm.grise.profundizacion.file;

import java.util.ArrayList;
import java.util.List;

import es.upm.grise.profundizacion.exceptions.InvalidContentException;
import es.upm.grise.profundizacion.exceptions.WrongFileTypeException;

public class File {

    private FileType type;
    private List<Character> content;

    /*
     * Constructor
     */
    public File() {
        // content debe estar vacío pero no null
        this.content = new ArrayList<Character>();
    }

    /*
     * Method to test
     */
    public void addProperty(char[] newcontent)
            throws InvalidContentException, WrongFileTypeException {

        if (newcontent == null) {
            throw new InvalidContentException();
        }

        if (type == FileType.IMAGE) {
            throw new WrongFileTypeException();
        }

        for (char c : newcontent) {
            this.content.add(c);
        }
    }

    /*
     * Method to test
     */
    public long getCRC32() {

        // calculateCRC32 no admite arrays vacíos
        if (content.isEmpty()) {
            return 0L;
        }

        // Conversión ArrayList<Character> → byte[]
        // Se usa solo el byte menos significativo (0–255)
        byte[] bytes = new byte[content.size()];
        for (int i = 0; i < content.size(); i++) {
            bytes[i] = (byte) (content.get(i) & 0xFF);
        }

        return new FileUtils().calculateCRC32(bytes);
    }

    /*
     * Setters / getters
     */
    public void setType(FileType type) {
        this.type = type;
    }

    public List<Character> getContent() {
        return content;
    }
}
