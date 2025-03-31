package Models;

public class Veiculo {
    String placa;
    String modelo;
    int anoFabricacao;
    double quilometragem;

    public Veiculo(String placa, String modelo, int anoFabricacao, double quilometragem) {
        this.placa = placa;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
        this.quilometragem = quilometragem;
    }

    public void exibirDetalhes() {
        String detalhesVeiculo = "\nDetalhes do Veículo:\n"
                + "Placa: " + placa + "\n"
                + "Modelo: " + modelo + "\n"
                + "Ano de Fabricação: " + anoFabricacao + "\n"
                + "Quilometragem: " + String.format("%.1f", quilometragem) + " km";

        System.out.println(detalhesVeiculo);
    }

    public String registrarViagem(double km) {
        if (km > 0) {
            this.quilometragem += km;
            return "Viagem de " + km + " km registrada para o veículo " + this.placa;
        }

        return "Quilometragem inválida! A viagem não foi registrada.";

    }
}
