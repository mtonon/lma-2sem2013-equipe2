package br.com.yaw.spgae.controller;

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
		return "listaPresenca";
	}
	
	@RequestMapping(value = "listaAlunos", method = RequestMethod.GET)
	public String exibirListaAlunos(Model uiModel) {
		uiModel.addAttribute("active", "lista_alunos");
		uiModel.addAttribute("alunos", dao.getAll());
		return "listaAlunos";
	}
}
