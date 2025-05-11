package ma.enset.hopital;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class HopitalApplication implements CommandLineRunner {
@Autowired
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
      /*  CommandLineRunner start(PatientRepository patientRepository){
            return args->{
                patientRepository.save(new Patient((null,"Mohamed",new Date(),false,120));

            };
        }*/
        Patient p1 = new Patient();
        p1.setNom("Rania");
        p1.setDateNaissance(new Date());
        p1.setMalade(false);
        p1.setScore(107);
        patientRepository.save(p1);

        Patient p2 = new Patient();
        p2.setNom("Amira");
        p2.setDateNaissance(new Date());
        p2.setMalade(false);
        p2.setScore(117);
        patientRepository.save(p2);

        Patient p3 = new Patient();
        p3.setNom("Hicham");
        p3.setDateNaissance(new Date());
        p3.setMalade(true);
        p3.setScore(145);
        patientRepository.save(p3);

        patientRepository.findAll().forEach(p -> {
            System.out.println(p.getNom());
        });


}
@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
