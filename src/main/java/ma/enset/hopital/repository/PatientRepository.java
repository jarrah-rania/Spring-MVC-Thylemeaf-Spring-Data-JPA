package ma.enset.hopital.repository;

import ma.enset.hopital.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {

   // @Query("select page from Patient where  page.nom like :x")
    //Pageable de spring sert pour la pagination de telle sorte je peux transmettre la page ou size
    Page<Patient> findByNomContains(String keyword, PageRequest pageable);
}
