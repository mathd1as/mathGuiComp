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

                //scan

                if(tokens.get(i).getValor().equals("scan")){
                    if((tokens.get(i+3).getAtributo().equals("var"))){
                        if(!varDeclaradasValor.contains((tokens.get(i+3).getValor()))){
                            return "ERRO - A variavel "+tokens.get(i+3).getValor()+" nao foi declarada.";
                        }
                        int index = varDeclaradasValor.indexOf(tokens.get(i+3).getValor());
                        if(tokens.get(i+2).getValor().equals(varDeclaradasValor.get(index))){
                            //tudo certo
                        }else{
                            return "ERRO - A variavel "+tokens.get(i+3).getValor()+" nao corresponde a tipagem correta.";
                        }
                    }else{
                        return "ERRO - O token "+tokens.get(i+3).getValor()+" nao corresponde a um var.\nEra esperando um var para" +
                                " salvar o valor lido.";
                    }
                }

                //while
                if(tokens.get(i).getValor().equals("while") || tokens.get(i).getValor().equals("if") || tokens.get(i).getValor().equals("else if")){

                    if((tokens.get(i+2).getAtributo().equals("var") || tokens.get(i+2).getAtributo().equals("int")) ||
                            (tokens.get(i+2).getAtributo().equals("float"))){

                        if((tokens.get(i+3).getAtributo().equals("Op. relacional"))){
                            if((!tokens.get(i+4).getAtributo().equals("var") && !tokens.get(i+4).getAtributo().equals("int")) &&
                                    (!tokens.get(i+4).getAtributo().equals("float"))){
                                return "ERRO - Operacao relacional incompativel.";
                            }
                        }else{
                            return "ERRO - Era esperado uma expressao ou um boolean dentro do while.";
                        }

                    }else if(tokens.get(i+2).getValor().equals("verdadeiro") || tokens.get(i+2).getValor().equals("falso") ){
                        if((tokens.get(i+4).getAtributo().equals("var") || tokens.get(i+4).getAtributo().equals("int")) ||
                                (tokens.get(i+4).getAtributo().equals("float"))){
                            return "ERRO - Operacao relacional incompativel.";
                        }else if(tokens.get(i+4).getValor().equals("verdadeiro") || tokens.get(i+4).getValor().equals("falso")){
                            return "ERRO - Operacao relacional incompativel.";
                        }
                    }else{
                        return "erro";
                    }
                }

                //FOR
                if(tokens.get(i).getValor().equals("for")){
                    if(tokens.get(i+2).getAtributo().equals("var")){
                        if(varDeclaradasValor.contains(tokens.get(i+2).getValor())){
                            int index = varDeclaradasValor.indexOf(tokens.get(i+2).getValor());
                            if(varDeclaradasTipo.get(index).equals("int")){
                                //here
                                if(tokens.get(i+6).getAtributo().equals("var") || tokens.get(i+6).getAtributo().equals("int") || tokens.get(i+6).getAtributo().equals("float")){

                                    if(varDeclaradasValor.contains(tokens.get(i+6).getValor()) || tokens.get(i+6).getAtributo().equals("int") || tokens.get(i+6).getAtributo().equals("float")){
                                        if(tokens.get(i+7).getAtributo().equals("Op. relacional")){
                                            if(tokens.get(i+8).getAtributo().equals("var") || tokens.get(i+8).getAtributo().equals("int") || tokens.get(i+8).getAtributo().equals("float")){

                                            }else{
                                                return "ERRO - A expressao relacional deve conter um int um flot ou uma var";
                                            }
                                        }else{
                                            return "ERRO - O operador deve ser um operador relacional.";
                                        }
                                    }else{
                                        return "ERRO - A variavel nao foi declarada. 2";
                                    }
                                }else{
                                    return "ERRO - Era esperado um var dentro do for 5";
                                }

                            }else{
                                return "ERRO - A varivel do for precisa ser um int";
                            }
                        }else{
                            return "ERRO - A variavel nao foi declarada.";
                        }
                    }else{
                        return "ERRO - Era esperado um var dentro do for";
                    }
                }

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
                                    if(tokens.get(i - 1).getAtributo().equals("bool")){
                                        System.out.println("here 1");
                                        if(tokens.get(i + 1).getAtributo().equals("bool") || tokens.get(i + 1).getValor().equals("var")){
                                            if(tokens.get(i + 1).getValor().equals("var")){
                                                if(varDeclaradasValor.contains(tokens.get(i + 1).getValor())){
                                                    if(tokens.get(i + 2).getAtributo().equals("Op. relacional")){
                                                        if(tokens.get(i + 3).getAtributo().equals("var") || tokens.get(i + 3).getAtributo().equals("bool")){

                                                        }else{
                                                            return "ERRO - Era esperado um valor do tipo bool";
                                                        }
                                                    }else{
                                                        return "ERRO - Era esperado um operador relacional.";
                                                    }
                                                }else{
                                                    return "ERRO - A variavel " + tokens.get(i + 1).getValor() + " nao foi declarada.";
                                                }
                                            }else{
                                                if(tokens.get(i + 2).getAtributo().equals("Op. relacional")){
                                                    if(tokens.get(i + 3).getAtributo().equals("var") || tokens.get(i + 3).getAtributo().equals("bool")){

                                                    }else{
                                                        return "ERRO - Era esperado um valor do tipo bool";
                                                    }
                                                }else{
                                                    return "ERRO - Era esperado um operador relacional.";
                                                }
                                            }
                                        }else{
                                            return "ERRO -";
                                        }
                                    }else if(tokens.get(i - 1).getAtributo().equals("int")){
                                        System.out.println("here");
                                        if(tokens.get(i + 1).getAtributo().equals("int") || tokens.get(i + 1).getValor().equals("var")){
                                            if(tokens.get(i + 1).getValor().equals("var")){
                                                if(varDeclaradasValor.contains(tokens.get(i + 1).getValor())){
                                                    if(tokens.get(i + 2).getAtributo().equals("Op. aritimético")){
                                                        if(tokens.get(i + 3).getAtributo().equals("var") || tokens.get(i + 3).getAtributo().equals("int")){

                                                        }else{
                                                            return "ERRO - Era esperado um valor do tipo bool";
                                                        }
                                                    }else{
                                                        return "ERRO - Era esperado um operador relacional.";
                                                    }
                                                }else{
                                                    return "ERRO - A variavel " + tokens.get(i + 1).getValor() + " nao foi declarada.";
                                                }
                                            }else{
                                                if(tokens.get(i + 2).getAtributo().equals("Op. ")){
                                                    if(tokens.get(i + 3).getAtributo().equals("var") || tokens.get(i + 3).getAtributo().equals("bool")){

                                                    }else{
                                                        return "ERRO - Era esperado um valor do tipo bool";
                                                    }
                                                }else{
                                                    return "ERRO - Era esperado um operador relacional.";
                                                }
                                            }
                                        }else{
                                            return "ERRO - 2";
                                        }
                                    }
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
