package br.com.yaw.spgae.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.yaw.spgae.dao.AlunoDAO;

@RequestMapping(value="/")
@Controller
public class AlunoController {
	
	@Autowired
	private AlunoDAO dao;
	
	@RequestMapping(value = "listaPresenca", method = RequestMethod.GET)
	public String exibirListaDePresenca(Model uiModel) {
		uiModel.addAttribute("active", "lista_presenca");
		uiModel.addAttribute("alunos", dao.getAll());
		return "listaPresenca";
	}
	
	@RequestMapping(value = "listaAlunos", method = RequestMethod.GET)
	public String exibirListaAlunos(Model uiModel) {
		//TODO - Carregar Presencas
		uiModel.addAttribute("active", "lista_alunos");
		uiModel.addAttribute("alunos", dao.getAll());
		return "listaAlunos";
	}
	
	@RequestMapping(value = "listaAlunosSalvarPresenca", method = RequestMethod.POST)
	public String salvarPresenca(Model uiModel, HttpServletRequest request) {
		//TODO - Salvar presenca para aluno
		
		String[] teste = request.getParameterValues("presente");
		for (int i = 0; (teste!=null) && (i < teste.length); i++) {
			System.out.println(teste[i]);
		}
		
		return "redirect:"+exibirListaDePresenca(uiModel);
	}
}
