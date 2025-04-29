import com.google.gson.JsonObject;

public class Moeda {
    private String tipo;
    private double valor;


    public Moeda(String tipo, JsonObject rates) {
        this.tipo = tipo.toUpperCase();
        if (rates.has(this.tipo)) {
            this.valor = rates.get(this.tipo).getAsDouble();
        } else {
            throw new IllegalArgumentException("Moeda não encontrada: " + tipo);
        }
    }

        public String getTipo(){
            return tipo;
        }
        public double getValor(){
            return valor;
        }
    }

