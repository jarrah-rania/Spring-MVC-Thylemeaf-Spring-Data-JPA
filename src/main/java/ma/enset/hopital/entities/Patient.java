package ma.enset.hopital.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Data
@AllArgsConstructor
//@NoArgsConstructor @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    @NotEmpty
    @Size(min=4,max=40)
    private String nom;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    private boolean malade;
    @DecimalMin("100")
    private int score;

    public Patient(Long id, String nom, Date dateNaissance, boolean malade, int score) {
        this.id = id;
        this.nom = nom;

        this.dateNaissance = dateNaissance;
        this.malade = malade;
        this.score = score;
    }
  public String getNom()
  {
      return this.nom;
  }
    public Patient()
 {

 }

    }

