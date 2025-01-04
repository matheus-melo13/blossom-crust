import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPizza;

    private String sabor;
    private Float preco;
    private String descricao;
}
