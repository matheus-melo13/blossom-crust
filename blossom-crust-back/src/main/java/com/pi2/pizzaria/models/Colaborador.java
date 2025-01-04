import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idColaborador;

    private String email;
    private String nome;
    private String senha;
    private Integer tipoColaborador;
}
