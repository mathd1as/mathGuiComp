import java.io.IOException;

public class TabelaPreditiva {
    private String[][] tabela;

    public TabelaPreditiva() {
        this.tabela = new String[37][36];
    }

    public void inicializaTabela() throws IOException {

        this.tabela[0][0] = "null";

        //N - terminais
        this.tabela[1][0] = "<program>";
        this.tabela[2][0] = "<stmt_list>";
        this.tabela[3][0] = "<stmt_list_2>";
        this.tabela[4][0] = "<stmt>";
        this.tabela[5][0] = "<declara_var>";
        this.tabela[6][0] = "<instancia_int>";
        this.tabela[7][0] = "<instancia_int_2>";
        this.tabela[8][0] = "<mais_var_int>";
        this.tabela[9][0] = "<instancia_float>";
        this.tabela[10][0] = "<instancia_float_2>";
        this.tabela[11][0] = "<mais_var_float>";
        this.tabela[12][0] = "<instancia_bool>";
        this.tabela[13][0] = "<instancia_bool_2>";
        this.tabela[14][0] = "<mais_var_bool>";
        this.tabela[15][0] = "<if_stmt>";
        this.tabela[16][0] = "<opt>";
        this.tabela[17][0] = "<cmd_elif>";
        this.tabela[18][0] = "<cmd_else>";
        this.tabela[19][0] = "<atribui>";
        this.tabela[20][0] = "<atribui_2>";
        this.tabela[21][0] = "<for_stmt>";
        this.tabela[22][0] = "<while_stmt>";
        this.tabela[23][0] = "<scan>";
        this.tabela[24][0] = "<tipo>";
        this.tabela[25][0] = "<print>";
        this.tabela[26][0] = "<print_stmt>";
        this.tabela[27][0] = "<print_stmt_2>";
        this.tabela[28][0] = "<print_stmt_3>";
        this.tabela[29][0] = "<exp_relacional>";
        this.tabela[30][0] = "<qual>";
        this.tabela[31][0] = "<op_relacional>";
        this.tabela[32][0] = "<op_logico>";
        this.tabela[33][0] = "<exp_aritmetica>";
        this.tabela[34][0] = "<exp>";
        this.tabela[35][0] = "<exp_2>";
        this.tabela[36][0] = "<op_aritmetico>";

        //terminais
        this.tabela[0][1] = "<id>";
        this.tabela[0][2] = "<natural>";
        this.tabela[0][3] = "<real>";
        this.tabela[0][4] = "<var>";
        this.tabela[0][5] = "int";
        this.tabela[0][6] = "float";
        this.tabela[0][7] = "bool";
        this.tabela[0][8] = "if";
        this.tabela[0][9] = "else";
        this.tabela[0][10] = "elif";
        this.tabela[0][11] = "for";
        this.tabela[0][12] = "while";
        this.tabela[0][13] = "scan";
        this.tabela[0][14] = "print";
        this.tabela[0][15] = "verdadeiro";
        this.tabela[0][16] = "falso";
        this.tabela[0][17] = ")";
        this.tabela[0][18] = "}";
        this.tabela[0][19] = ";";
        this.tabela[0][20] = ",";
        this.tabela[0][21] = ">";
        this.tabela[0][22] = "<";
        this.tabela[0][23] = ">=";
        this.tabela[0][24] = "<=";
        this.tabela[0][25] = "==";
        this.tabela[0][26] = "!=";
        this.tabela[0][27] = "=";
        this.tabela[0][28] = "+";
        this.tabela[0][29] = "-";
        this.tabela[0][30] = "*";
        this.tabela[0][31] = "/";
        this.tabela[0][32] = "&&";
        this.tabela[0][33] = "||";
        this.tabela[0][34] = "$";
        this.tabela[0][35] = "\"";

        //<program>
        this.tabela[1][4] = "<stmt_list>";
        this.tabela[1][5] = "<stmt_list>";
        this.tabela[1][6] = "<stmt_list>";
        this.tabela[1][7] = "<stmt_list>";
        this.tabela[1][8] = "<stmt_list>";
        this.tabela[1][11] = "<stmt_list>";
        this.tabela[1][12] = "<stmt_list>";
        this.tabela[1][13] = "<stmt_list>";
        this.tabela[1][14] = "<stmt_list>";

        //<stmt_list>
        this.tabela[2][4] = "<stmt> <stmt_list_2>";
        this.tabela[2][5] = "<stmt> <stmt_list_2>";
        this.tabela[2][6] = "<stmt> <stmt_list_2>";
        this.tabela[2][7] = "<stmt> <stmt_list_2>";
        this.tabela[2][8] = "<stmt> <stmt_list_2>";
        this.tabela[2][11] = "<stmt> <stmt_list_2>";
        this.tabela[2][12] = "<stmt> <stmt_list_2>";
        this.tabela[2][13] = "<stmt> <stmt_list_2>";
        this.tabela[2][14] = "<stmt> <stmt_list_2>";

        //<stmt_list_2>
        this.tabela[3][4] = "<stmt_list>";
        this.tabela[3][5] = "<stmt_list>";
        this.tabela[3][6] = "<stmt_list>";
        this.tabela[3][7] = "<stmt_list>";
        this.tabela[3][8] = "<stmt_list>";
        this.tabela[3][11] = "<stmt_list>";
        this.tabela[3][12] = "<stmt_list>";
        this.tabela[3][13] = "<stmt_list>";
        this.tabela[3][14] = "<stmt_list>";
        this.tabela[3][18] = "ε";
        this.tabela[3][34] = "ε";

        //<stmt>
        this.tabela[4][4] = "<atribui>";
        this.tabela[4][5] = "<declara_var>";
        this.tabela[4][6] = "<declara_var>";
        this.tabela[4][7] = "<declara_var>";
        this.tabela[4][8] = "<if_stmt>";
        this.tabela[4][11] = "<for_stmt>";
        this.tabela[4][12] = "<while_stmt>";
        this.tabela[4][13] = "<scan>";
        this.tabela[4][14] = "<print>";

        //<declara_var>
        this.tabela[5][5] = "int <var> <instancia_int> <exp_2> ;";
        this.tabela[5][6] = "float <var> <instancia_float> <mais_var_float> ;";
        this.tabela[5][7] = "bool <var> <instancia_bool> <mais_var_bool> ;";

        //<instancia_int>
        this.tabela[6][19] = "ε";
        this.tabela[6][20] = "ε";
        this.tabela[6][27] = "= <instancia_int_2>";

        //<instancia_int_2>
        this.tabela[7][2] = "<natural>";
        this.tabela[7][4] = "<var>";

        //<mais_var_int>
        this.tabela[8][19] = "ε";
        this.tabela[8][20] = ", <var> <instancia_int> <mais_var_int>";

        //<instancia_float>
        this.tabela[9][19] = "ε";
        this.tabela[9][27] = "= <instancia_float_2>";

        //<instancia_float_2>
        this.tabela[10][3] = "<real>";
        this.tabela[10][4] = "<var>";

        //<<mais_var_float>>
        this.tabela[11][19] = "ε";
        this.tabela[11][20] = ", <var> <instancia_float> <mais_var_float>";

        //<instancia_bool>
        this.tabela[12][19] = "ε";
        this.tabela[12][27] = "= <instancia_bool_2>";

        //<instancia_bool_2>
        this.tabela[13][4] = "<var>";
        this.tabela[13][15] = "verdadeiro";
        this.tabela[13][16] = "falso";

        //<mais_var_bool>
        this.tabela[14][19] = "ε";
        this.tabela[14][20] = ", <var> <instancia_bool> <mais_var_bool>";

        //<if_stmt>
        this.tabela[15][8] = "if ( <exp_relacional> <opt> ) { <stmt_list> } <cmd_elif> <cmd_else>";

        //<opt>
        this.tabela[16][17] = "ε";
        this.tabela[16][32] = "<op_logico> <exp_relacional> <opt>";
        this.tabela[16][33] = "<op_logico> <exp_relacional> <opt>";

        //<cmd_elif>
        this.tabela[17][4] = "ε";
        this.tabela[17][5] = "ε";
        this.tabela[17][6] = "ε";
        this.tabela[17][7] = "ε";
        this.tabela[17][8] = "ε";
        this.tabela[17][9] = "ε";
        this.tabela[17][11] = "ε";
        this.tabela[17][12] = "ε";
        this.tabela[17][7] = "ε";
        this.tabela[17][13] = "ε";
        this.tabela[17][14] = "ε";
        this.tabela[17][18] = "ε";
        this.tabela[17][10] = "elif ( <exp_relacional> <opt> ) { <stmt_list> } <cmd_elif>";
        this.tabela[17][34] = "ε";

        //<cmd_else>
        this.tabela[18][4] = "ε";
        this.tabela[18][5] = "ε";
        this.tabela[18][6] = "ε";
        this.tabela[18][7] = "ε";
        this.tabela[18][8] = "ε";
        this.tabela[18][9] = "else { <stmt_list> }";
        this.tabela[18][11] = "ε";
        this.tabela[18][12] = "ε";
        this.tabela[18][13] = "ε";
        this.tabela[18][14] = "ε";
        this.tabela[18][18] = "ε";
        this.tabela[18][34] = "ε";

        //<atribui>
        this.tabela[19][4] = "<var> = <atribui_2> <exp_2> ;";

        //<atribui_2>
        this.tabela[20][2] = "<natural>";
        this.tabela[20][3] = "<real>";
        this.tabela[20][4] = "<var>";
        this.tabela[20][15] = "verdadeiro";
        this.tabela[20][16] = "falso";

        //<for_stmt>
        this.tabela[21][11] = "for ( <var> = <instancia_int_2> ; <exp_relacional> ; <exp_aritmetica> ) { <stmt_list> }";

        //<while_stmt>
        this.tabela[22][12] = "while ( <exp_relacional> <opt> ) { <stmt_list> }";

        //<scan>
        this.tabela[23][13] = "scan ( <tipo> <var> ) ;";

        //<tipo>
        this.tabela[24][5] = "int";
        this.tabela[24][6] = "float";
        this.tabela[24][7] = "bool";

        //<print>
        this.tabela[25][14] = "print ( <print_stmt> ) ;";

        //<print_stmt>
        this.tabela[26][2] = "<natural> <print_stmt_2>";
        this.tabela[26][3] = "<real> <print_stmt_2>";
        this.tabela[26][4] = "<var> <print_stmt_2>";
        this.tabela[26][15] = "verdadeiro <print_stmt_2>";
        this.tabela[26][16] = "falso <print_stmt_2>";
        this.tabela[26][35] = "\" <id> \" <print_stmt_2>";

        //<print_stmt_2>
        this.tabela[27][2] = "<natural> <print_stmt_2>";
        this.tabela[26][3] = "<real> <print_stmt_2>";
        this.tabela[27][17] = "ε";
        this.tabela[27][28] = "+ <print_stmt_3>";

        //<print_stmt_3>
        this.tabela[28][4] = "<var> <print_stmt_2>";
        this.tabela[28][2] = "<natural> <print_stmt_2>";
        this.tabela[28][3] = "<real> <print_stmt_2>";
        this.tabela[28][15] = "verdadeiro <print_stmt_2>";
        this.tabela[28][16] = "falso <print_stmt_2>";
        this.tabela[28][35] = "\" <id> \" <print_stmt_2>";

        //<exp_relacional>
        this.tabela[29][1] = "<qual> <op_relacional> <qual>";
        this.tabela[29][4] = "<qual> <op_relacional> <qual>";

        //<qual>
        this.tabela[30][1] = "<id>";
        //Adiconei <natural> pq antes ele so derivava para id, como tratamos os numeros como naturais e neste campo
        //e esperado um int nao e nescessario ter um id que siguinifica qlq coisa
        this.tabela[30][2] = "<natural>";
        this.tabela[30][3] = "<real>";
        this.tabela[30][4] = "<var>";

        //<op_relacional>
        this.tabela[31][21] = ">";
        this.tabela[31][22] = "<";
        this.tabela[31][23] = ">=";
        this.tabela[31][24] = "<=";
        this.tabela[31][25] = "==";
        this.tabela[31][26] = "!=";

        //<op_logico>
        this.tabela[32][32] = "&&";
        this.tabela[32][33] = "||";

        //<exp_aritmetica>
        this.tabela[33][4] = "<var> = <exp>";

        //<exp>
        this.tabela[34][1] = "<qual> <exp_2>";
        this.tabela[34][2] = "<qual> <exp_2>";
        this.tabela[34][3] = "<qual> <exp_2>";
        this.tabela[34][4] = "<qual> <exp_2>";

        //<exp_2>
        this.tabela[35][4] = "ε";
        this.tabela[35][5] = "ε";
        this.tabela[35][6] = "ε";
        this.tabela[35][7] = "ε";
        this.tabela[35][8] = "ε";
        this.tabela[35][11] = "ε";
        this.tabela[35][12] = "ε";
        this.tabela[35][13] = "ε";
        this.tabela[35][14] = "ε";
        this.tabela[35][17] = "ε";
        this.tabela[35][18] = "ε";
        this.tabela[35][19] = "ε";
        this.tabela[35][28] = "<op_aritmetico> <exp>";
        this.tabela[35][29] = "<op_aritmetico> <exp>";
        this.tabela[35][30] = "<op_aritmetico> <exp>";
        this.tabela[35][31] = "<op_aritmetico> <exp>";

        //<op_aritmetico>
        this.tabela[36][28] = "+";
        this.tabela[36][29] = "-";
        this.tabela[36][30] = "*";
        this.tabela[36][31] = "/";

    }

    public String buscaSentenca(String pilha, String entrada){

        for(int i = 0; i < 37; i++){
            if(this.tabela[i][0].equals(pilha)){
                for(int j = 0; j < 36; j++){
                    if(this.tabela[0][j].equals(entrada)){
                        return this.tabela[i][j];
                    }
                }
            }
        }
        return "ERRO_Deu_pau.txt_socorro";
    }
}
