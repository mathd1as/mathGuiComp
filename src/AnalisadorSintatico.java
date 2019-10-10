import java.io.IOException;
import java.util.List;

public class AnalisadorSintatico {
    private List<Token> tokens;

    public AnalisadorSintatico(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void an_sintatico() throws IOException {
        Pilha pilha = new Pilha(1000);
        Pilha entrada = new Pilha(tokens.size()+1);

        pilha.push("$");
        pilha.push("<program>");

        entrada.push("$");
        //Erro ta aqui...
        for (int i = tokens.size() -1  ; i >= 0; i--) {
            System.out.println(entrada.retornaTopo());
            System.out.println(tokens.get(i).getValor());

            entrada.push(tokens.get(i).getValor());
        }

        System.out.println("ultimo token: "+tokens.get(0).getValor());
        System.out.println("Entrada topo: "+entrada.retornaTopo());

        /*while(!entrada.pilhaVazia()){
            System.out.println(entrada.retornaTopo());
            entrada.pop();
        }*/


        TabelaPreditiva tabela = new TabelaPreditiva();
        tabela.inicializaTabela();

        while (true) {
            if (pilha.retornaTopo() == "$" && entrada.retornaTopo() == "$") {
                System.out.println("Sentenca Aceita");
                break;
            }

            if (pilha.retornaTopo() == entrada.retornaTopo()) {
                pilha.pop();
                entrada.pop();
            } else {
                String sentenca = tabela.buscaSentenca(pilha.retornaTopo(), entrada.retornaTopo());

                if (sentenca.equals("ERRO_Deu_pau.txt_socorro")) {
                    System.out.println(sentenca);
                    break;
                } else {
                    pilha.pop();
                    int tam = 0;

                    for (int i = 0; i < sentenca.length(); i++) {
                        char ch = sentenca.charAt(i);
                        String x1 = String.valueOf(ch);

                        if (x1.equalsIgnoreCase(" ")) {
                            tam++;
                        }
                    }
                    if (tam == 0) {
                        pilha.push(sentenca);
                    } else {
                        String[] aux = new String[tam];
                        aux = sentenca.split(" ");

                        for (int i = tam - 1; i >= 0; i--) {
                           pilha.push(aux[i]);
                            System.out.println(aux[i]+" ");
                        }
                    }
                }
            }
            break;
        }

    }
}
