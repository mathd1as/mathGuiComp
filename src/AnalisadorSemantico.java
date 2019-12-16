import java.util.ArrayList;
import java.util.List;

public class AnalisadorSemantico {
    private List<Token> tokens;

    AnalisadorSemantico(List<Token> tokens) {
        this.tokens = tokens;
    }

    public String an_semantico() {
        List<String> varDeclaradasValor = new ArrayList<>();
        List<String> varDeclaradasCadeia = new ArrayList<>();
        List<String> varDeclaradasTipo = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {

            if ((tokens.get(i).getValor().equals("int") ||
                    tokens.get(i).getValor().equals("float") ||
                    tokens.get(i).getValor().equals("bool")) && (tokens.get(i + 2).getValor().equals(";"))) {

                if (!varDeclaradasValor.contains(tokens.get(i + 1).getValor())) {
                    varDeclaradasValor.add(tokens.get(i + 1).getValor());
                    varDeclaradasCadeia.add("");
                    varDeclaradasTipo.add(tokens.get(i).getValor());
                } else {
                    return "ERRO - A variavel " + tokens.get(i + 1).getValor() + " ja foi declarada";
                }

            }

            if (tokens.get(i).getValor().equals("=")) {
                if (this.verifica_token_var(i)) {

                    if (!varDeclaradasValor.contains(tokens.get(i - 1).getValor())) {

                        if (tokens.get(i - 2).getValor().equals("int") && tokens.get(i + 1).getAtributo().equals("int")) {
                            varDeclaradasValor.add(tokens.get(i - 1).getValor());
                            varDeclaradasCadeia.add(tokens.get(i + 1).getValor());
                            varDeclaradasTipo.add(tokens.get(i - 2).getValor());


                        } else if (tokens.get(i - 2).getValor().equals("float") && tokens.get(i + 1).getAtributo().equals("float")) {
                            varDeclaradasValor.add(tokens.get(i - 1).getValor());
                            varDeclaradasCadeia.add(tokens.get(i + 1).getValor());
                            varDeclaradasTipo.add(tokens.get(i - 2).getValor());

                        } else if (tokens.get(i - 2).getValor().equals("bool") && tokens.get(i + 1).getAtributo().equals("Palavra Reservada")) {
                            varDeclaradasValor.add(tokens.get(i - 1).getValor());
                            varDeclaradasCadeia.add(tokens.get(i + 1).getValor());
                            varDeclaradasTipo.add(tokens.get(i - 2).getValor());

                        } else {
                            return "ERRO - A variavel " + tokens.get(i - 1).getValor() + " nao foi declarada ou nao corresponde a tipagem correta.";
                        }
                    } else {
                        if (tokens.get(i - 2).getValor().equals("int") ||
                                tokens.get(i - 2).getValor().equals("float") ||
                                tokens.get(i - 2).getValor().equals("bool")) {

                            return "ERRO - A variavel " + tokens.get(i - 1).getValor() + " ja foi declarada";
                        } else {
                            if (tokens.get(i + 2).getValor().equals(";")) {
                                int indice = 0;

                                if (varDeclaradasValor.contains(tokens.get(i - 1).getValor())) {
                                    indice = varDeclaradasValor.indexOf(tokens.get(i - 1).getValor());
                                } else {
                                    return "ERRO - A variavel " + tokens.get(i - 1).getValor() + " nao foi declarada.";
                                }

                                if (tokens.get(i + 1).getAtributo().equals("int") && varDeclaradasTipo.get(indice).equals("int")) {
                                    varDeclaradasCadeia.add(indice, tokens.get(i + 1).getValor());
                                } else if (tokens.get(i + 1).getAtributo().equals("float") && varDeclaradasTipo.get(indice).equals("float")) {
                                    varDeclaradasCadeia.add(indice, tokens.get(i + 1).getValor());
                                } else if (tokens.get(i + 1).getAtributo().equals("Palavra Reservada") && varDeclaradasTipo.get(indice).equals("bool")) {
                                    if (tokens.get(i + 1).getValor().equals("verdadeiro")) {
                                        varDeclaradasCadeia.add(indice, tokens.get(i + 1).getValor());
                                    } else if (tokens.get(i + 1).getValor().equals("falso")) {
                                        varDeclaradasCadeia.add(indice, tokens.get(i + 1).getValor());
                                    }
                                }else if(tokens.get(i + 1).getAtributo().equals("var")){
                                    int indice2 = 0;

                                    if (varDeclaradasValor.contains(tokens.get(i + 1).getValor())) {
                                        indice2 = varDeclaradasValor.indexOf(tokens.get(i + 1).getValor());
                                    } else {
                                        return "ERRO - A variavel " + tokens.get(i - 1).getValor() + " nao foi declarada.";
                                    }

                                    if(varDeclaradasTipo.get(indice).equals(varDeclaradasTipo.get(indice2))){
                                        //da tudo certo
                                    }else{
                                        return "ERRO - A variavel " + tokens.get(i + 1).getValor() + " nao foi declarada.";
                                    }
                                }else{
                                    return "ERRO - Tipagem incompativel";
                                }
                            }else{
                                //tratar expressoes
                            }
                        }
                    }
                } else {
                    return "ERRO - Era esperado um token do atributo VAR, porem foi passado um token do atributo " +
                            tokens.get(i - 1).getAtributo();
                }
            }
        }
        return "Sentenca semantica aceita";
    }

    public boolean verifica_token_var(int i) {
        if (tokens.get(i - 1).getAtributo().equals("var")) {
            return true;
        } else {
            return false;
        }
    }
}
