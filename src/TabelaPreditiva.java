import java.io.IOException;

public class TabelaPreditiva {
    private String[][] tabela;

    public TabelaPreditiva() {
        this.tabela = new String[20][25];
    }

    public void inicializaTabela() throws IOException {

        this.tabela[0][0] = "null";

        this.tabela[1][0] = "<program>";
        this.tabela[2][0] = "<stmt_list>";
        this.tabela[3][0] = "<stmt_list>_'";
        this.tabela[4][0] = "<stmt>";
        this.tabela[5][0] = "<exp_logica>";
        this.tabela[6][0] = "<op_logico>";
        this.tabela[7][0] = "<exp>";
        this.tabela[8][0] = "<exp_'>";
        this.tabela[9][0] = "<op_aritmetico>";
        this.tabela[10][0] = "<declara_var>";
        this.tabela[11][0] = "<declara_var>_'";
        this.tabela[12][0] = "<opt>";
        this.tabela[13][0] = "<atribuicao>";
        this.tabela[14][0] = "<atr_bool>";
        this.tabela[15][0] = "<atr_bool>_'";
        this.tabela[16][0] = "<if_stmt>";
        this.tabela[17][0] = "<cmd_else>";
        this.tabela[18][0] = "<for_stmt>";
        this.tabela[19][0] = "<while_stmt>";

        this.tabela[0][1] = "id";
        this.tabela[0][2] = "var";
        this.tabela[0][3] = "int";
        this.tabela[0][4] = "float";
        this.tabela[0][5] = "bool";
        this.tabela[0][6] = "if";
        this.tabela[0][7] = "else";
        this.tabela[0][8] = "for";
        this.tabela[0][9] = "while";
        this.tabela[0][10] = "verdadeiro";
        this.tabela[0][11] = "falso";
        this.tabela[0][12] = ",";
        this.tabela[0][13] = ";";
        this.tabela[0][14] = "(";
        this.tabela[0][15] = ")";
        this.tabela[0][16] = "{";
        this.tabela[0][17] = "}";
        this.tabela[0][18] = "+";
        this.tabela[0][19] = "-";
        this.tabela[0][20] = "*";
        this.tabela[0][21] = "/";
        this.tabela[0][22] = "==";
        this.tabela[0][23] = "!=";
        this.tabela[0][24] = "$";

        //<program>
        this.tabela[1][3] = "<stmt_list>";
        this.tabela[1][4] = "<stmt_list>";
        this.tabela[1][5] = "<stmt_list>";
        this.tabela[1][6] = "<stmt_list>";
        this.tabela[1][8] = "<stmt_list>";
        this.tabela[1][9] = "<stmt_list>";
        this.tabela[1][9] = "<stmt_list>";

        //<<stmt_list>>
        this.tabela[2][3] = "<stmt> ; <stmt_list>_'";
        this.tabela[2][4] = "<stmt> ; <stmt_list>_'";
        this.tabela[2][5] = "<stmt> ; <stmt_list>_'";
        this.tabela[2][6] = "<stmt> ; <stmt_list>_'";
        this.tabela[2][8] = "<stmt> ; <stmt_list>_'";
        this.tabela[2][9] = "<stmt> ; <stmt_list>_'";

        //<stmt_list>_'
        this.tabela[3][3] = "<stmt_list>";
        this.tabela[3][4] = "<stmt_list>";
        this.tabela[3][5] = "<stmt_list>";
        this.tabela[3][6] = "<stmt_list>";
        this.tabela[3][8] = "<stmt_list>";
        this.tabela[3][9] = "<stmt_list>";
        this.tabela[3][9] = "$";

        //<stmt>
        this.tabela[4][3] = "<declara_var>";
        this.tabela[4][4] = "<declara_var>";
        this.tabela[4][5] = "<declara_var>";
        this.tabela[4][6] = "<if_stmt>";
        this.tabela[4][8] = "<for_stmt>";
        this.tabela[4][9] = "<while_stmt>";

        //<exp_logica>
        this.tabela[5][1] = "<id> <op_logico> <id>";

        //<op_logico>
        this.tabela[6][23] = "!=";

        //<exp>
        this.tabela[7][1] = "<id> <exp_'>";

        //<exp_'>
        this.tabela[8][12] = "ε";
        this.tabela[8][13] = "ε";
        this.tabela[8][15] = "ε";
        this.tabela[8][18] = "<op_aritmetico> <exp>";
        this.tabela[8][19] = "<op_aritmetico> <exp>";
        this.tabela[8][20] = "<op_aritmetico> <exp>";
        this.tabela[8][21] = "<op_aritmetico> <exp>";
        this.tabela[8][22] = "<op_aritmetico> <exp>";

        //<op_aritmetico>
        this.tabela[9][18] = "+";
        this.tabela[9][19] = "-";
        this.tabela[9][20] = "*";
        this.tabela[9][21] = "/";

        //<declara_var>
        this.tabela[10][3] = "int <declara_var>_'";
        this.tabela[10][4] = "float <declara_var>_'";
        this.tabela[10][5] = "bool <declara_var>_'";

        //<declara_var>_'
        this.tabela[11][2] = "<var> ;";

        //<opt>
        this.tabela[12][12] = ", <atribuicao> <opt>";
        this.tabela[12][13] = "ε";

        //<atribuicao>
        this.tabela[13][2] = "<var> <atribuicao_'>";

        //<atr_bool>
        this.tabela[14][2] = "<var> = <atr_bool>_'";

        //<atr_bool>
        this.tabela[15][10] = "verdadeiro";
        this.tabela[15][11] = "falso";

        //<if_stmt>
        this.tabela[16][6] = "if ( <exp_logica> ) { <stmt> } <cmd_else>";

        //<cmd_else>
        this.tabela[17][7] = "else { <stmt> }";
        this.tabela[17][13] = "ε";
        this.tabela[17][17] = "ε";

        //<for_stmt>
        this.tabela[18][8] = "for ( <var> = <id> ; <exp_logica> ; <exp> ) { <stmt> }";

        //<while_stmt>
        this.tabela[19][9] = "while ( <exp_logica> ) { <stmt> }";

    }

    public String buscaSentenca(String pilha, String entrada){

        for(int i = 0; i < 20; i++){
            if(this.tabela[i][0].equals(pilha)){
                for(int j = 0; j < 25; j++){
                    if(this.tabela[0][j].equals(entrada)){
                        return this.tabela[i][j];
                    }
                }
            }
        }
        return "ERRO_Deu_pau.txt_socorro";
    }
}
