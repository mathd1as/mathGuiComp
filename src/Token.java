public class Token {
    private String atributo;
    private String valor;
    private String erro = "";
    private int linha;
    private String tipo;

    public Token(String atributo, String valor, int linha) {
        this.atributo = atributo;
        this.valor = valor;
        this.linha = linha;
        this.tipo = "nulo";
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

    public String getTipo() {
        return this.tipo;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
