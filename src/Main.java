import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if(args[0].equals("mathgui")) {
            String nomeArquivo = args[1];

            File arquivo = new File(nomeArquivo);

            if (!arquivo.exists()) {
                System.out.println("O arquivo específicado não existe");
                return;
            }


        }
    }
}
