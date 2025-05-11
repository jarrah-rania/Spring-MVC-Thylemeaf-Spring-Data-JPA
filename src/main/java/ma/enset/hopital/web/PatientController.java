package ma.enset.hopital.web;

import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@AllArgsConstructor

public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/user/index")
//si MVC n'a pas défini @RequestParam il fallait utiliser httpServletRequest et recuperer page via getParameter
//+il faut passer par caste ParseInt
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw)
{Page<Patient> pagePatient=patientRepository.findByNomContains(kw,PageRequest.of(page,size));
   model.addAttribute("ListPatients",pagePatient.getContent());
   model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
   model.addAttribute("currentPage",page);
   model.addAttribute("keyword",kw);
   return "patients";
}
@PreAuthorize("hasRole('ROLE_ADMIN')")
@GetMapping("/admin/delete")
public String delete(Long id,String keyword,int page)
{
    patientRepository.deleteById(id);
    return "redirect:/admin/index?page="+page+"&keyword="+keyword; //la redirection afin d'afficher la nouvelle liste aprés la suppression
}

    @GetMapping("/patients")
    @ResponseBody
    public List<Patient> listPatients(){
    return patientRepository.findAll();
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/formPatients")
  public String formPatients(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatients";
    }
    @PostMapping(path = "/admin/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String save(Model model , @Valid Patient patient, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "") String keyword) {
        if(bindingResult.hasErrors())return "formPatients";
          patientRepository.save(patient);
        return "redirect:/admin/index?page="+page+"&keyword="+keyword;

    }
    @GetMapping("/admin/editPatient")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editPatient(Model model,Long id,String keyword,int page) {
        Patient patient =patientRepository.findById(id).orElse(null);
         if(patient==null) throw new RuntimeException(("Patient introuvable"));

        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";
    }
    @GetMapping("/")
    public String home()
    {
        return "redirect:/user/index";
    }

}
