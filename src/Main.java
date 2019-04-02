import java.io.File;
import java.io.IOException;
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
                    "while", "scan", "print","case", "switch","break");
            List<String> simbolos = Arrays.asList("(",")","{","}","=",":",";",",");
            List<String> operadores = Arrays.asList("&&","||","==","<=","<",">=",">","!=","++","--");

            AnalisadorLexico lexico = new AnalisadorLexico(arquivo, palavrasReservadas, simbolos, operadores);
            List<Token> tokens = lexico.analisar();

            for (int i=0;i < tokens.size(); i++){
                System.out.println("Atributo: "+tokens.get(i).getAtributo());
                System.out.println("Valor: "+tokens.get(i).getValor());
                System.out.println("Linha: "+tokens.get(i).getLinha());
                if(tokens.get(i).getErro() != ""){
                    System.out.println("Erro msg: "+tokens.get(i).getErro());
                }
                System.out.println();
            }

        }
    }
}
