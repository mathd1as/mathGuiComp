import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnalisadorLexico {
    private BufferedInputStream bufferInputStream;
    private int linha;
    private List<String> palavrasReservadas;
    private List<String> simbolos;
    private List<String> operadores;

    AnalisadorLexico(File arquivo, List<String> palavrasReservadas, List<String> simbolos, List<String> operadores) throws FileNotFoundException {
        this.palavrasReservadas = palavrasReservadas;
        this.simbolos = simbolos;
        this.operadores = operadores;
        this.bufferInputStream = new BufferedInputStream(new FileInputStream(arquivo));
    }

    private char lerCaracter() throws IOException {
        this.bufferInputStream.mark(2);
        char atual = (char)this.bufferInputStream.read();
        if (atual == '\n') {
            ++this.linha;
            return ' ';
        } else {
            return atual;
        }
    }

    private char lerPosicao() throws IOException {
        char posicaoAtual;
        for(posicaoAtual = this.lerCaracter(); posicaoAtual == ' ' || posicaoAtual == '\n'; posicaoAtual = this.lerCaracter()) {
        }

        return posicaoAtual;
    }

    public List<Token> analisar() throws IOException {
        List<Token> tokens = new ArrayList();

        for(Token token = this.reconhecerToken(); token != null; token = this.reconhecerToken()) {
            tokens.add(token);
            if (!token.getErro().equals("")) {
                break;
            }
        }

        return tokens;
    }

    private Token reconhecerToken() throws IOException {
        Token token = null;
        char posicaoAtual = this.lerPosicao();

        while(posicaoAtual == '\r'){
            posicaoAtual = lerPosicao();
        }
        String palavra = String.valueOf(posicaoAtual);

        while(posicaoAtual == '\r') {
            posicaoAtual = this.lerPosicao();
        }

        palavra = String.valueOf(posicaoAtual);
        if (palavra.matches("[0-9]")) {
            this.bufferInputStream.reset();
            token = this.encontrarNumero();
        } else if (palavra.matches("[a-zA-Z]")) {
            this.bufferInputStream.reset();
            token = this.encontrarPalavraReservada();
        } else if (palavra.matches("[+\\-*/]")) {
            this.bufferInputStream.reset();
            token = this.econtrarOperador("Op. aritimético");
        } else if (palavra.matches("[!^|&]")) {
            this.bufferInputStream.reset();
            token = this.econtrarOperador("Op. lógico");
        } else if (palavra.matches("[=<>!]")) {
            this.bufferInputStream.reset();
            token = this.econtrarOperador("Op. relacional");
        } else if (palavra.matches("[(){}:;]")) {
            this.bufferInputStream.reset();
            token = this.encontrarSimbolos();
        } else if (!palavra.equals("\uffff")) {
            token = this.erroStatment(palavra);
        }
        return token;
    }

    private Token erroStatment(String palavra) throws IOException {
        Token erro = new Token("", "", this.linha);
        this.bufferInputStream.reset();
        erro.setErro("Caracter não definido");
        erro.setValor(palavra);
        erro.setLinha(this.linha);
        erro.setAtributo("Erro");
        return erro;
    }

    private Token encontrarSimbolos() throws IOException {
        String proxSimbolo = String.valueOf(this.lerCaracter());
        Token simbolo = null;
        switch(proxSimbolo){
            case "(":
                simbolo = new Token("Abre Parenteses", "", this.linha);
                break;
            case ")":
                simbolo = new Token("Fecha Parenteses", "", this.linha);
                break;
            case "{":
                simbolo = new Token("Abre Chaves", "", this.linha);
                break;
            case "}":
                simbolo = new Token("Fecha Chaves", "", this.linha);
                break;
            case ";":
                simbolo = new Token("Ponto e Virgula", "", this.linha);
                break;
            case ":":
                simbolo = new Token("Doi Pontos", "", this.linha);
                break;
            case ",":
                simbolo = new Token("Virgula", "", this.linha);
                break;
             default:
                 //Algum tratamento de erro aqui posteriormente
        }
        
        if (this.simbolos.contains(proxSimbolo)) {
            simbolo.setValor(proxSimbolo);
        }

        simbolo.setLinha(this.linha);
        return simbolo;
    }

    private Token econtrarOperador(String atributo) throws IOException {
        Token operador = new Token(atributo, "", this.linha);
        String atual = String.valueOf(this.lerCaracter());
        char proxOperador = this.lerCaracter();
        if (this.operadores.contains(String.valueOf(atual + proxOperador))) {
            atual = atual + proxOperador;
        } else {
            this.bufferInputStream.reset();
        }

        if(atual.equals("=")){
            operador.setAtributo("Atribuiçao");
        }

        operador.setValor(String.valueOf(atual));
        operador.setLinha(this.linha);
        return operador;
    }

    private Token encontrarPalavraReservada() throws IOException {
        Token palavraReservada = new Token("", "", this.linha);
        String valor = this.encontrarPadrao("[a-zA-Z0-9]");
        this.bufferInputStream.reset();
        if (this.palavrasReservadas.contains(valor)) {
            palavraReservada.setValor(valor);
            palavraReservada.setAtributo("Palavra Reservada");
        } else {
            palavraReservada.setValor(valor);
            palavraReservada.setAtributo("id");
        }
        return palavraReservada;
    }

    private Token encontrarNumero() throws IOException {
        Token numero = new Token("int", (String)null, this.linha);
        String valor = this.encontrarPadrao("[0-9]");
        this.bufferInputStream.reset();
        numero.setValor(valor);
        if (this.lerCaracter() == '.') {
            valor = valor + '.';
            String validar = this.encontrarPadrao("[0-9]");
            valor = valor + validar;
            numero.setValor(valor);
            numero.setAtributo("float");
            this.bufferInputStream.reset();
            if (validar.equals("")) {
            }
        } else {
            this.bufferInputStream.reset();
        }

        numero.setLinha(this.linha);
        return numero;
    }

    private String encontrarPadrao(String regex) throws IOException {
        String valor;
        char posicaoCorrente;
        for(valor = ""; this.bufferInputStream.available() > 0; valor = valor + posicaoCorrente) {
            posicaoCorrente = this.lerCaracter();
            if (posicaoCorrente == ' ' || posicaoCorrente == '\n' || !String.valueOf(posicaoCorrente).matches(regex)) {
                break;
            }
        }
        return valor;
    }
}
