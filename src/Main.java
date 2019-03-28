import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        if(args[0].equals("mathgui")) {
            String nomeArquivo = args[1];

            File arquivo = new File(nomeArquivo);

            if (!arquivo.exists()) {
                System.out.println("O arquivo específicado não existe");
                return;
            }

            List<String> palavrasReservadas = Arrays.asList("int", "float", "bool", "if", "else", "default", "for",
                    "while", "scan", "print");
            List<String> simbolos = Arrays.asList("(",")","{","}","=",":","=",";");
            List<String> operadores = Arrays.asList("&&","||","==","<=","<",">=",">","!=","++","--"); //Adicionar ++ e -- na especificação

            AnalisadorLexico lexico = new AnalisadorLexico(arquivo, palavrasReservadas, simbolos, operadores);
            List<Token> toknes = lexico.analisar();

            for (int i=0;i < toknes.size(); i++)
                System.out.println(toknes.get(i).getValor());
        }
    }
}
