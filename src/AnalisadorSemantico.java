import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

public class AnalisadorSemantico {
    private List<Token> tokens;
    private List<Token> varDeclaradas;

    AnalisadorSemantico(List<Token> tokens) {
        this.tokens = tokens;
        this.varDeclaradas = varDeclaradas;
    }

    public String an_semantico() {

        List<Token> varDeclaradas = new ArrayList<Token>();

        for (int i = 0; i < this.tokens.size() - 1; i++) {
            switch (this.tokens.get(i).getValor()) {
                case "bool":
                    if (this.tokens.get(i + 2).getValor().equals(";")) {
                        if (varDeclaradas.contains(this.tokens.get(i + 1))) {
                            return "Erro - Variavel ja declarada";
                        } else {
                            if (this.tokens.get(i + 1).getTipo().equals("nulo")) {
                                this.tokens.get(i + 1).setTipo("bool");
                            }
                            varDeclaradas.add(this.tokens.get(i + 1));
                        }
                    } else if (this.tokens.get(i + 3).equals("verdadeiro") || this.tokens.get(i + 3).equals("falso")) {
                        if (varDeclaradas.contains(this.tokens.get(i + 1))) {
                            return "Erro - Variavel ja declarada";
                        } else {
                            if (this.tokens.get(i + 1).getTipo().equals("nulo")) {
                                this.tokens.get(i + 1).setTipo("bool");
                            }
                            varDeclaradas.add(this.tokens.get(i + 1));
                        }
                    } else {
                        return "Erro - Era esperado uma variavel do tipo bool";
                    }
                    break;
                case "int":
                    if (this.tokens.get(i + 2).getValor().equals(";")) {
                        if (varDeclaradas.contains(this.tokens.get(i + 1))) {
                            return "Erro - Variavel ja declarada";
                        } else {
                            if (this.tokens.get(i + 1).equals("nulo")) {
                                this.tokens.get(i + 1).setTipo("int");
                            }
                            varDeclaradas.add(this.tokens.get(i + 1));
                        }
                    } else if (this.tokens.get(i + 3).getAtributo().equals("int")) {
                        if (varDeclaradas.contains(this.tokens.get(i + 1))) {
                            return "Erro - Variavel ja declarada";
                        } else {
                            if (this.tokens.get(i + 1).getTipo().equals("nulo")) {
                                this.tokens.get(i + 1).setTipo("int");
                            }
                            varDeclaradas.add(this.tokens.get(i + 1));
                        }
                    } else {
                        return "Erro - Era esperado uma variavel do tipo int";
                    }
                    break;
                case "float":
                    if (this.tokens.get(i + 2).getValor().equals(";")) {
                        if (varDeclaradas.contains(this.tokens.get(i + 1).getValor())) {
                            return "Erro - Variavel ja declarada";
                        } else {
                            if (this.tokens.get(i + 1).equals("nulo")) {
                                this.tokens.get(i + 1).setTipo("float");
                            }
                            varDeclaradas.add(this.tokens.get(i + 1));
                        }
                    } else if (this.tokens.get(i + 3).getAtributo().equals("float")) {
                        if (varDeclaradas.contains(this.tokens.get(i + 1).getValor())) {
                            return "Erro - Variavel ja declarada";
                        } else {
                            if (this.tokens.get(i + 1).getTipo().equals("nulo")) {
                                this.tokens.get(i + 1).setTipo("float");
                            }
                            varDeclaradas.add(this.tokens.get(i + 1));
                        }
                    } else {
                        return "Erro - Era esperado uma variavel do tipo float";
                    }
                    break;
                default:
                    //System.out.println("Nao caiu em lugar nenhum");
                    break;
            }

           /*for(int j=0; j<varDeclaradas.size(); j++){
               System.out.println("Tipo "+varDeclaradas.get(j).getTipo());
               System.out.println("Valor "+varDeclaradas.get(j).getValor());
           }
            System.out.println();*/


            if (this.tokens.get(i).getAtributo().equals("var")) {

                System.out.println();
                if ((!this.tokens.get(i - 1).getAtributo().equals("int") ||
                        !this.tokens.get(i - 1).getAtributo().equals("float") ||
                        !this.tokens.get(i - 1).getAtributo().equals("bool"))) {
                    System.out.println("token valor " + this.tokens.get(i - 1).getValor());
                    //System.out.println("token "+this.tokens.get(i).getValor());
                    for (int j = 0; j < varDeclaradas.size(); j++) {
                        if (!varDeclaradas.get(j).getValor().equals(this.tokens.get(i).getValor())) {
                            return "Erro - A variavel nao foi declarada";
                        } else {
                            this.tokens.get(i).setTipo(varDeclaradas.get(j).getTipo());
                            if (!this.tokens.get(i).getTipo().equals(this.tokens.get(i + 2).getAtributo())) {
                                return "Erro - Era eseprado um valor do tipo " + this.tokens.get(i).getTipo() +
                                        " mas foi encontrado um valor do tipo " + this.tokens.get(i + 2).getAtributo();
                            } else {
                                break;
                            }
                        }
                    }
                }

            }
        }
        return "Sentenca semantica aceita";
    }


}
