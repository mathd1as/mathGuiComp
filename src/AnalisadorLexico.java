import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class AnalisadorLexico {

    private BufferedInputStream bufferInputStream;
    //private int row;
    private List<String> palavrasReservadas, simbolos, operadores;

    AnalisadorLexico(File arquivo, List<String> palavrasReservadas, List<String> simbolos, List<String> operadores)
            throws FileNotFoundException {
        this.palavrasReservadas = palavrasReservadas;
        this. simbolos = simbolos;
        this.operadores = operadores;
        this.bufferInputStream = new BufferedInputStream(new FileInputStream(arquivo));
    }



}
