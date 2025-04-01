package Models;

public class ContaBancaria {
    private String titular;
    private float saldo;

    public ContaBancaria(String titular) {
        this.titular = titular;
        this.saldo = 0.0f;
    }

    public void depositar(float valor) {
        if (valor < 0) {
            System.out.println("Valor de depósito inválido. O valor deve ser positivo.");
            return;
        }

        this.saldo += valor;
        System.out.println("Depósito de R$ " + String.format("%.2f", valor) + " realizado com sucesso.");
        exibirSaldo();
    }

    public void sacar(float valor) {
        if (valor < 0) {
            System.out.println("Valor de saque inválido. O valor deve ser positivo.");
            return;
        }
        if (valor >= this.saldo) {
            System.out.println("Saldo insuficiente para realizar o saque.");
            exibirSaldo();
            return;
        }

        this.saldo -= valor;
        System.out.println("Saque de R$ " + String.format("%.2f", valor) + " realizado com sucesso.");
        exibirSaldo();
    }

    public void exibirSaldo() {
        System.out.println("Titular: " + this.titular);
        System.out.println("Saldo atual: R$ " + String.format("%.2f", this.saldo));
    }
}
