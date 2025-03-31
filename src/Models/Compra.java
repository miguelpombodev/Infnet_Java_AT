package Models;

public class Compra {
    public Compra (String nome, int quantidadeCompra, float precoUnitarioCompra) {
        produto = nome;
        quantidade = quantidadeCompra;
        precoUnitario = precoUnitarioCompra;
    }
    public String produto;
    public int quantidade;
    public float precoUnitario;

    public float calcularTotal() {
     return precoUnitario * quantidade;
    }
}
