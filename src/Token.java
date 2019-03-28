//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

public class Token {
    private String atributo;
    private String valor;
    private String erro = "";
    private int linha;

    public Token(String atributo, String valor, int linha) {
        this.atributo = atributo;
        this.valor = valor;
        this.linha = linha;
    }

    public String getAtributo() {
        return this.atributo;
    }

    public String getValor() {
        return this.valor;
    }

    public int getLinha() {
        return this.linha;
    }

    public String getErro() {
        return this.erro;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setErro(String erro) {
        this.erro = erro;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }
}
