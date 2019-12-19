import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class llvm {
    private final List<Token> tokens;
    public int varTemp;
    public int varIf;
    public List<String> listaVars;
    public List<String> listaTipos;

    llvm(List<Token> tokens) {
        this.tokens = tokens;
        this.varTemp = 1;
        this.varIf = 1;
        this.listaVars = listaVars;
        this.listaTipos = listaTipos;
    }

    public void gerarCodigoIntermediario() throws IOException {
        listaVars = new ArrayList<String>();
        listaTipos = new ArrayList<String>();
        Pilha blocos = new Pilha(1000);
        Pilha numBlocos = new Pilha(1000);

        String comandoIR = "";
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter("codigoIr.ir"));

        buffWrite.append("define i32 @main(){\n");
        for (int i = 0; i < tokens.size(); i++) {
            //Instanciacao
            if (tokens.get(i).getValor().equals("int")) {
                if (tokens.get(i + 1).getAtributo().equals("var")) {
                    comandoIR = "%" + tokens.get(i + 1).getValor() + " = alloca i32";
                    buffWrite.append(comandoIR + "\n");
                    listaVars.add(tokens.get(i + 1).getValor());
                    listaTipos.add("int");
                }
            } else if (tokens.get(i).getValor().equals("bool")) {
                if (tokens.get(i + 1).getAtributo().equals("var")) {
                    comandoIR = "%" + tokens.get(i + 1).getValor() + " = alloca i1";
                    buffWrite.append(comandoIR + "\n");
                    listaVars.add(tokens.get(i + 1).getValor());
                    listaTipos.add("bool");
                }
            } else if (tokens.get(i).getValor().equals("float")) {
                if (tokens.get(i + 1).getAtributo().equals("var")) {
                    comandoIR = "%" + tokens.get(i + 1).getValor() + " = alloca double";
                    buffWrite.append(comandoIR + "\n");
                    listaVars.add(tokens.get(i + 1).getValor());
                    listaTipos.add("double");
                }
            }

            //Atribuicao
            if (tokens.get(i).getValor().equals("=")) {
                if (tokens.get(i + 1).getAtributo().equals("int")) {
                    comandoIR = "store i32 " + tokens.get(i + 1).getValor() + ", i32 * %" + tokens.get(i - 1).getValor();
                    buffWrite.append(comandoIR + "\n");

                } else if (tokens.get(i + 1).getAtributo().equals("float")) {
                    comandoIR = "store double " + tokens.get(i + 1).getValor() + ", double * %" + tokens.get(i - 1).getValor();
                    buffWrite.append(comandoIR + "\n");

                } else if (tokens.get(i + 1).getValor().equals("verdadeiro") || tokens.get(i + 1).getValor().equals("falso")) {
                    if (tokens.get(i + 1).getValor().equals("verdadeiro")) {
                        comandoIR = "store i1 1, i1 * %" + tokens.get(i - 1).getValor();
                        buffWrite.append(comandoIR + "\n");
                    } else {
                        comandoIR = "store i1 0, i1 * %" + tokens.get(i - 1).getValor();
                        buffWrite.append(comandoIR + "\n");
                    }
                } else if (tokens.get(i).getAtributo().equals("var")) {
                    //trato dps
                }
            }

            //expressoes
            if (tokens.get(i).getValor().equals("=")) {
                if (!tokens.get(i + 2).getValor().equals(";")) {
                    if (tokens.get(i + 2).getAtributo().equals("Op. aritimético")) {
                        int indice = listaVars.indexOf(tokens.get(i - 1).getValor());

                        if (listaTipos.get(indice).equals("int")) {
                            if (tokens.get(i + 1).getAtributo().equals("var")) {
                                comandoIR = "%varTempIntA" + varTemp + " = load i32, i32* %" + tokens.get(i + 1).getValor();
                                buffWrite.append(comandoIR + "\n");
                            } else {
                                comandoIR = "%auxA" + varTemp + " = alloca i32";
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store i32 " + tokens.get(i + 1).getValor() + ", i32 * %auxA" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "%varTempIntA" + varTemp + " = load i32, i32* %auxA" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                            }
                            if (tokens.get(i + 3).getAtributo().equals("var")) {
                                comandoIR = "%varTempIntB" + varTemp + " = load i32, i32* %" + tokens.get(i + 3).getValor();
                                buffWrite.append(comandoIR + "\n");
                            } else {
                                comandoIR = "%auxB" + varTemp + " = alloca i32";
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store i32 " + tokens.get(i + 3).getValor() + ", i32 * %auxB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "%varTempIntB" + varTemp + " = load i32, i32* %auxB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                            }
                        } else if (listaTipos.get(indice).equals("double")) {
                            if (tokens.get(i + 1).getAtributo().equals("var")) {
                                comandoIR = "%varTempFloatA" + varTemp + " = load double, double* %" + tokens.get(i + 1).getValor();
                                buffWrite.append(comandoIR + "\n");
                            } else {
                                comandoIR = "%auxA" + varTemp + " = alloca double";
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store double " + tokens.get(i + 1).getValor() + ", double * %auxA" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "%varTempFloatA" + varTemp + " = load double, double* %auxA" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                            }
                            if (tokens.get(i + 3).getAtributo().equals("var")) {
                                comandoIR = "%varTempFloatB" + varTemp + " = load double, double* %" + tokens.get(i + 3).getValor();
                                buffWrite.append(comandoIR + "\n");
                            } else {
                                comandoIR = "%auxB" + varTemp + " = alloca double";
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store double " + tokens.get(i + 3).getValor() + ", double * %auxB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "%varTempFloatB" + varTemp + " = load double, double* %auxB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                            }
                        }
                        if (tokens.get(i + 2).getValor().equals("+")) {
                            if (listaTipos.get(indice).equals("int")) {

                                comandoIR = "%add" + varTemp + " = add i32 " + "%varTempIntA" + varTemp + ", %varTempIntB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store i32 %add" + varTemp + ", i32 * %" + tokens.get(i - 1).getValor();
                                buffWrite.append(comandoIR + "\n");
                            } else if (listaTipos.get(indice).equals("double")) {
                                comandoIR = "%add" + varTemp + " = fadd double " + "%varTempFloatA" + varTemp + ", %varTempFloatB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store double %add" + varTemp + ", double * %" + tokens.get(i - 1).getValor();
                                buffWrite.append(comandoIR + "\n");
                            }
                        } else if (tokens.get(i + 2).getValor().equals("-")) {
                            if (listaTipos.get(indice).equals("int")) {
                                comandoIR = "%sub" + varTemp + " = sub i32 " + "%varTempIntA" + varTemp + ", %varTempIntB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store i32 %sub" + varTemp + ", i32 * %" + tokens.get(i - 1).getValor();
                                buffWrite.append(comandoIR + "\n");
                            } else if (listaTipos.get(indice).equals("double")) {
                                comandoIR = "%sub" + varTemp + " = fusb double " + "%varTempFloatA" + varTemp + ", %varTempFloatB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store double %sub" + varTemp + ", double * %" + tokens.get(i - 1).getValor();
                                buffWrite.append(comandoIR + "\n");
                            }
                        } else if (tokens.get(i + 2).getValor().equals("*")) {
                            if (listaTipos.get(indice).equals("int")) {
                                comandoIR = "%mul" + varTemp + " = mul i32 " + "%varTempIntA" + varTemp + ", %varTempIntB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store i32 %mul" + varTemp + ", i32 * %" + tokens.get(i - 1).getValor();
                                buffWrite.append(comandoIR + "\n");
                            } else if (listaTipos.get(indice).equals("double")) {
                                comandoIR = "%mul" + varTemp + " = fmul double " + "%varTempFloatA" + varTemp + ", %varTempFloatB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store double %mul" + varTemp + ", double * %" + tokens.get(i - 1).getValor();
                                buffWrite.append(comandoIR + "\n");
                            }
                        } else if (tokens.get(i + 2).getValor().equals("/")) {
                            if (listaTipos.get(indice).equals("int")) {
                                comandoIR = "%div" + varTemp + " = udiv i32 " + "%varTempIntA" + varTemp + ", %varTempIntB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store i32 %div" + varTemp + ", i32 * %" + tokens.get(i - 1).getValor();
                                buffWrite.append(comandoIR + "\n");
                            } else if (listaTipos.get(indice).equals("double")) {
                                comandoIR = "%div" + varTemp + " = fdiv double " + "%varTempFloatA" + varTemp + ", %varTempFloatB" + varTemp;
                                buffWrite.append(comandoIR + "\n");
                                comandoIR = "store double %div" + varTemp + ", double * %" + tokens.get(i - 1).getValor();
                                buffWrite.append(comandoIR + "\n");
                            }
                        }
                    } else if (tokens.get(i + 2).getAtributo().equals("Op. relacional")) {

                    } else if (tokens.get(i + 2).getAtributo().equals("Op. lógico")) {
                        //tratar
                    }
                }

            }

            //if else
            if (tokens.get(i).getValor().equals("if")) {
                blocos.push("if");
                numBlocos.push(Integer.toString(varIf));
                if (tokens.get(i + 2).getValor().equals("verdadeiro")) {

                } else if (tokens.get(i + 2).getValor().equals("falso")) {

                } else if (tokens.get(i + 3).getAtributo().equals("Op. relacional")) {
                    if (tokens.get(i + 2).getAtributo().equals("var")) {
                        comandoIR = "%cmpA" + varTemp + " = load i32, i32* %" + tokens.get(i + 2).getValor();
                        buffWrite.append(comandoIR + "\n");
                    } else if (tokens.get(i + 2).getAtributo().equals("int")) {
                        comandoIR = "%auxA" + varTemp + " = alloca i32";
                        buffWrite.append(comandoIR + "\n");
                        comandoIR = "store i32 %auxA" + varTemp + ", i32 * %cmpA" + varTemp;
                        buffWrite.append(comandoIR + "\n");
                        comandoIR = "%cmpA" + varTemp + " = load i32, i32* %" + tokens.get(i + 2).getValor();
                        buffWrite.append(comandoIR + "\n");
                    }

                    if (tokens.get(i + 4).getAtributo().equals("var")) {
                        comandoIR = "%cmpB" + varTemp + " = load i32, i32* %" + tokens.get(i + 4).getValor();
                        buffWrite.append(comandoIR + "\n");
                    } else if (tokens.get(i + 4).getAtributo().equals("int")) {
                        comandoIR = "%auxB" + varTemp + " = alloca i32";
                        buffWrite.append(comandoIR + "\n");
                        comandoIR = "store i32 %auxB" + varTemp + ", i32 * %cmpB" + varTemp;
                        buffWrite.append(comandoIR + "\n");
                        comandoIR = "%cmpB" + varTemp + " = load i32, i32* %" + tokens.get(i + 4).getValor();
                        buffWrite.append(comandoIR + "\n");
                    }

                    if (tokens.get(i + 3).getAtributo().equals("<")) {
                        comandoIR ="%resIf"+varIf+ " = icmp slt i32 %cmpA" + varIf + " %cmpB" + varIf;
                        buffWrite.append(comandoIR + "\n");
                    } else if (tokens.get(i + 3).getAtributo().equals(">")) {
                        comandoIR = "%resIf"+varIf+ " = icmp sgt i32 %cmpA" + varIf + " %cmpB" + varIf;
                        buffWrite.append(comandoIR + "\n");
                    } else if (tokens.get(i + 3).getAtributo().equals("<=")) {
                        comandoIR = "%resIf"+varIf+" = icmp sle i32 %cmpA" + varIf + " %cmpB" + varIf;
                        buffWrite.append(comandoIR + "\n");
                    } else if (tokens.get(i + 3).getAtributo().equals(">=")) {
                        comandoIR = "%resIf"+varIf+" = icmp sge i32 %cmpA" + varIf + " %cmpB" + varIf;
                        buffWrite.append(comandoIR + "\n");
                    } else if (tokens.get(i + 3).getAtributo().equals("==")) {
                        comandoIR = "%resIf"+varIf+" = icmp eq i32 %cmpA" + varIf + " %cmpB" + varIf;
                        buffWrite.append(comandoIR + "\n");
                    } else if (tokens.get(i + 3).getAtributo().equals("!=")) {
                        comandoIR = "%resIf"+varIf+" = icmp ne i32 %cmpA" + varIf + " %cmpB" + varIf;
                        buffWrite.append(comandoIR + "\n");
                    }
                }
                varIf++;
                comandoIR = "br i1 %resIf"+varIf+",label %trueIf"+varIf+", label %falseIf"+varIf;
                buffWrite.append(comandoIR + "\n");
                comandoIR = "trueIf"+numBlocos.retornaTopo()+":";
                buffWrite.append(comandoIR + "\n");
            }

            if (tokens.get(i).getValor().equals("}")) {
                System.out.println(tokens.size());
                System.out.println(i+1);
                if (tokens.size() <= (i+1) ){
                    comandoIR = "falseIf"+numBlocos.retornaTopo()+":";
                    buffWrite.append(comandoIR + "\n");
                    comandoIR = "br label %endIf"+varIf;
                    buffWrite.append(comandoIR + "\n");
                    comandoIR = "endIf"+numBlocos.retornaTopo()+":";
                    buffWrite.append(comandoIR + "\n");
                    blocos.pop();
                    numBlocos.pop();
                }else if(tokens.get(i + 1).getValor().equals("else")) {

                } else if (blocos.retornaTopo().equals("if")) {
                    comandoIR = "falseIf"+numBlocos.retornaTopo()+":";
                    buffWrite.append(comandoIR + "\n");
                    blocos.pop();
                    numBlocos.pop();
                }else if(blocos.retornaTopo().equals("while")){

                }else if(blocos.retornaTopo().equals("for")){

                }
            }
            varTemp++;
        }
        System.out.println(listaVars);
        System.out.println(listaTipos);
        buffWrite.append("ret i32 0\n}");
        buffWrite.close();
    }
}
