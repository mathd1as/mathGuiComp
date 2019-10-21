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

            if(tokens.get(i).getAtributo().equals("var")){
                entrada.push("<var>");
            }else if(tokens.get(i).getAtributo().equals("id")){
                entrada.push("<id>");
            }else if(tokens.get(i).getAtributo().equals("int")){
                entrada.push("<natural>");
            }else if(tokens.get(i).getAtributo().equals("float")){
                entrada.push("<real>");
            }else{
                entrada.push(tokens.get(i).getValor());
            }
        }

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

            if(pilha.retornaTopo().equals("ε")){
                System.out.println("desempilhou pilha "+ pilha.retornaTopo());
                pilha.pop();
            }

            if (pilha.retornaTopo().equals(entrada.retornaTopo()) || pilha.retornaTopo().equals(entrada.retornaTopo())) {
                System.out.println("desempilhou pilha "+ pilha.retornaTopo());
                pilha.pop();
                System.out.println("desempilhou entrada "+entrada.retornaTopo());
                entrada.pop();

            } else {
                System.out.println("pilha: "+pilha.retornaTopo()+" entrada: " + entrada.retornaTopo());
                String sentenca = tabela.buscaSentenca(pilha.retornaTopo(), entrada.retornaTopo());

                if (sentenca.equals("ERRO_Deu_pau.txt_socorro")) {
                    System.out.println(sentenca);
                    break;
                } else {
                    System.out.println("desempilhou "+pilha.retornaTopo());
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
                        System.out.println("empilhou pilha "+sentenca);
                        pilha.push(sentenca);
                    } else {
                        String[] aux = new String[tam];
                        aux = sentenca.split(" ");

                        for (int i = tam; i >= 0; i--) {

                            System.out.println("empilhou pilha "+aux[i]);
                            pilha.push(aux[i]);
                        }
                    }
                }
            }
        }

    }
}
