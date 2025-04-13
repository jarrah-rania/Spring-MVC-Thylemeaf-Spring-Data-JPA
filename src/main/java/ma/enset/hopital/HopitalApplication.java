package ma.enset.hopital;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {

    private PatientRepository patientRepository;
    public static void main(String[] args) {
        SpringApplication.run(HopitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //methode1
    /* Patient patient=new Patient();
     patient.setId(null);
     patient.setNom("Rania");
     patient.setDateNaissance((new Date(2003,11,20)));
     patient.setMalade(false);
     patient.setScore(21);
      //methode 2
     Patient patient2=new Patient(null,"Yassine",new Date(),false,120);

      //methode 3 avec builder
      Patient patient3=Patient.builder()
              .nom("Aymane")
              .dateNaissance(new Date())
              .score(37)
              .malade(true)
              .build();*/
        patientRepository.save(new Patient (null,"Rania",new Date(),false,87));
        patientRepository.save(new Patient (null,"Amira",new Date(),false,117));
        patientRepository.save(new Patient (null,"Hicham",new Date(),true,145));

    }
}
