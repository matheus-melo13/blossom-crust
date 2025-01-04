import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPedido;

    private LocalDateTime data;
    private String status;
    private Float total;

    @ManyToOne
    @JoinColumn(name = "Cliente_idClient")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoPizza> pedidoPizzas;
}
