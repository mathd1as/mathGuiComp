import java.io.IOException;
import java.sql.SQLOutput;
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

        for (int i = tokens.size() -1  ; i >= 0; i--) {
            if(tokens.get(i).getAtributo().equals("var")){
                entrada.push("<var>");
            }else if(tokens.get(i).getAtributo().equals("id")){
                entrada.push("<id>");
            }else if(tokens.get(i).getAtributo().equals("int")){
                entrada.push("<natural>");
            }else if(tokens.get(i).getAtributo().equals("float")){
                entrada.push("<real>");
            }else if(tokens.get(i).getValor().equals("print")){
                entrada.push("print");
            }else if(tokens.get(i).getValor().equals("scan")){
                entrada.push("scan");
            } else {
                entrada.push(tokens.get(i).getValor());
            }

        }

        TabelaPreditiva tabela = new TabelaPreditiva();
        tabela.inicializaTabela();
        Token Terro = new Token("teste", "teste", 5);
        System.out.println("teste");
        while (true) {
            //System.out.println("pilha: "+pilha.retornaTopo());
            //System.out.println("entrada: "+entrada.retornaTopo());
            if(pilha.retornaTopo().equals("ε")){
                pilha.pop();

            }

            if (pilha.retornaTopo() == "$" && entrada.retornaTopo() == "$") {
                System.out.println("Sentenca Aceita");
                break;
            }

            if (pilha.retornaTopo().equals(entrada.retornaTopo()) || pilha.retornaTopo().equals(entrada.retornaTopo())) {
                pilha.pop();
                entrada.pop();
                if(!tokens.isEmpty()){
                    Terro = new Token(tokens.get(0).getAtributo(), tokens.get(0).getValor(), tokens.get(0).getLinha());
                    tokens.remove(0);
                }
            } else {
                String sentenca = tabela.buscaSentenca(pilha.retornaTopo(), entrada.retornaTopo());

                if (sentenca == null) {
                    //Tratamento de erros e etc
                    switch (pilha.retornaTopo()){
                        case "<program>":
                            System.out.print("Era esperado um "+pilha.retornaTopo()+" na linha "+0);
                            break;
                        case "<stmt>":
                            System.out.print("Era esperado um stmt proximo a linha ");
                            break;
                        case "<mais_var_bool>":
                            System.out.print("Era esperado um ; na linha ");
                            break;
                        case "<instancia_int>":
                            System.out.print("Era esperado um ="+" na linha ");
                            break;
                        case "<instancia_bool>":
                            System.out.print("Era esperado um ="+" na linha ");
                            break;
                        case "<instancia_int_2>":
                            System.out.print("Era esperado um valor inteiro"+" na linha ");
                            break;
                        case "<instancia_float>":
                            System.out.print("Era esperado um ="+" na linha ");
                            break;
                        case "<instancia_float_2>":
                            System.out.print("Era esperado um valor com ponto flutuante"+" na linha ");
                            break;
                        case "<instancia_bool_2>":
                            System.out.print("Era esperado um \"verdadeiro\" ou \"falso\""+" na linha ");
                            break;
                        case "<cmd_elif>":
                            System.out.print("Era esperado um stmt na linha ");
                            break;
                        case "<cmd_else>":
                            System.out.print("Era esperado um stmt na linha ");
                            break;
                        case "<tipo>":
                            System.out.print("Era esperado um tipo dentro do scan ");
                            break;
                        case "<print_stmt>":
                            System.out.print("Era esperado um print stmt ");
                            break;
                        case "<print_stmt_2>":
                            System.out.print("Era esperado um ) na linha ");
                            break;
                        case "<exp_relacional>":
                            System.out.print("Era esperado uma expressao relacional dentro dos parenteses proximo a linha ");
                            break;
                        case "<exp_aritmetica>":
                            System.out.print("Era esperada uma expressao aritimetica dentro dos parenteses proximo a linha ");
                            break;
                        case "<op_relacional>":
                            System.out.print("Era esperado um operador relacional na linha ");
                            break;
                        case "<exp>":
                            System.out.print("Era esperado uma variavel ou a continuacao de uma expressao dentro dos parenteses proximo a linha ");
                            break;
                        case "<exp_2>":
                            System.out.print("Era esperado a continuacao de uma expressao ou um ; na linha ");
                            break;
                        case "<opt>":
                            System.out.print("Era esperado um ) na linha ");
                            break;
                        case "<qual>":
                            System.out.print("Era esperado uma variavel ou um numero natual na linha ");
                            break;
                        default:
                            System.out.print("Erro na linha ");
                            break;
                    }
                    //System.out.println(pilha.retornaTopo());
                    //System.out.println(Terro.getValor());
                    System.out.println(Terro.getLinha()+1);
                    break;
                }

                if(sentenca.equals("ERRO_Deu_pau.txt_socorro")){
                    System.out.println("if 2");

                    switch (pilha.retornaTopo()){
                        case "<var>":
                            System.out.println("Era esperado uma variavel");
                            break;
                        case "(":
                            System.out.println("Era esperado um ( na linha ");
                            break;
                        case ")":
                            System.out.print("Era esperado um ) na linha ");
                            break;
                        case "{":
                            System.out.print("Era esperado um { na linha ");
                        case "}":
                            System.out.print("Era esperado um } na linha ");
                            break;
                        case ";":
                            System.out.print("Era esperado um ; na linha ");
                            break;
                        case "=":
                            System.out.print("Era esperado um = na linha ");
                            break;
                        case "<opt>":
                            System.out.print("Era esperado um ) na linha ");
                            break;
                        case "<qual>":
                            System.out.print("Era esperado uma variavel ou um numero natual na linha ");
                            break;
                        case "<exp_relacional>":
                            System.out.print("Era esperado uma expressao relacional dentro dos parenteses proximo a linha ");
                            break;
                        default:
                            System.out.print("Erro na linha ");
                            break;
                    }
                    //System.out.println(pilha.retornaTopo());
                    //System.out.println(Terro.getValor());
                    System.out.println(Terro.getLinha()+1);

                    break;
                } else {
                    //System.out.println("desempilhou "+pilha.retornaTopo());
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

                        for (int i = tam; i >= 0; i--) {

                            //System.out.println("empilhou pilha "+aux[i]);
                            pilha.push(aux[i]);
                        }
                    }
                }
            }
        }

    }
}
