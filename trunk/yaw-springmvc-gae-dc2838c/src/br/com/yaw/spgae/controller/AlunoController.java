package br.com.yaw.spgae.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.yaw.spgae.dao.AlunoDAO;
import br.com.yaw.spgae.dao.AulaDAO;
import br.com.yaw.spgae.dao.UCDAO;
import br.com.yaw.spgae.model.Aluno;
import br.com.yaw.spgae.model.UC;

import com.googlecode.objectify.Key;

@RequestMapping(value="/")
@Controller
public class AlunoController {
	
	@Autowired
	private AlunoDAO alunoDAO;
	@Autowired
	private AulaDAO aulaDAO;
	@Autowired
	private UCDAO ucDAO;
	
	@RequestMapping(value = "listaPresenca", method = RequestMethod.GET)
	public String exibirListaDePresenca(Model uiModel) {
		uiModel.addAttribute("active", "lista_presenca");
		uiModel.addAttribute("alunos", aulaDAO.getAll().get(0).getAlunosDaAula());
		uiModel.addAttribute("alunosPresentes", aulaDAO.getAll().get(0).getAlunosPresentes());
		return "listaPresenca";
	}
	
	@RequestMapping(value = "listaAlunos", method = RequestMethod.GET)
	public String exibirListaAlunos(Model uiModel) {
		uiModel.addAttribute("active", "lista_alunos");
		uiModel.addAttribute("alunos", alunoDAO.getAll());
		return "listaAlunos";
	}
	
	@RequestMapping(value = "formUC", method = RequestMethod.GET)
	public String exibirFormUC(Model uiModel) {
		uiModel.addAttribute("active", "form_uc");
		return "formUC";
	}
	
	@RequestMapping(value = "incluirUC", method = RequestMethod.POST)
	public String incluirUC(Model uiModel, UC novaUC) {
		
		List<Aluno> todosAlunos = alunoDAO.getAll();
		
		for (Aluno aluno : todosAlunos) {
			novaUC.addAlunoNaAula(aluno);
		}
		
		ucDAO.save(novaUC);
		
		return "redirect: listaAlunos";
	}
	
	@RequestMapping(value = "listaAlunosSalvarPresenca", method = RequestMethod.POST)
	public String salvarPresenca(Model uiModel, HttpServletRequest request) {
		//TODO - Salvar presenca para aluno

		ArrayList<Key<Aluno>> alunosPresentesIDs = new ArrayList<Key<Aluno>>();
		
		String[] teste = request.getParameterValues("presente");
		for (int i = 0; (teste!=null) && (i < teste.length); i++) {
			alunosPresentesIDs.add(alunoDAO.findById(Long.parseLong(teste[i])).getKey());
		}
		
		aulaDAO.getAll().get(0).setIdAlunosPresentes(alunosPresentesIDs);
		aulaDAO.save(aulaDAO.getAll().get(0));
		
		return "redirect:"+exibirListaDePresenca(uiModel);
	}
}
