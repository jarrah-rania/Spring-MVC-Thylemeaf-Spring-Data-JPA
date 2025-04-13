package ma.enset.hopital.web;

import lombok.AllArgsConstructor;
import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
@GetMapping("/index")
//si MVC n'a pas défini @RequestParam il fallait utiliser httpServletRequest et recuperer page via getParameter
//+il faut passer par caste ParseInt
    public String index(Model model,
                        @RequestParam(name = "page",defaultValue = "0") int page,
                        @RequestParam(name = "size",defaultValue = "4") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String kw)
{
    Page<Patient> pagePatient=patientRepository.findByNomContains(kw,PageRequest.of(page,size));
   model.addAttribute("ListPatients",pagePatient.getContent());
   model.addAttribute("pages",new int[pagePatient.getTotalPages()]);
   model.addAttribute("currentPage",page);
   model.addAttribute("keyword",kw);
   return "patients";
}
@GetMapping("/delete")
public String delete(Long id,String keyword,int page)
{
    patientRepository.deleteById(id);
    return "redirect:/index?page="+page+"&keyword="+keyword; //la redirection afin d'afficher la nouvelle liste aprés la suppression
}
    @GetMapping("/")
    public String home()
    {
        return "redirect:/index";
    }
}
