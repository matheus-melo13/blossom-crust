import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class PedidoPizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer quantidadePizzas;

    @ManyToOne
    @JoinColumn(name = "Pedido_idPedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "Pizza_idPizza")
    private Pizza pizza;
}
