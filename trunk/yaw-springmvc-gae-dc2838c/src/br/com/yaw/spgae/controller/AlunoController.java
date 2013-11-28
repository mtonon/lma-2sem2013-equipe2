package br.com.yaw.spgae.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import br.com.yaw.spgae.model.Aula;
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
		
		AulaPresencaBean aulaPresencaBeanAux;
		List<Aula> todasAulas = aulaDAO.getAll();
		List<Aluno> alunosDaUC = ucDAO.getAll().get(0).getAlunosDaUC(); //TODO escolher a aula correta.
		ArrayList<AulaPresencaBean> matrizAulaPresenca = new ArrayList<AulaPresencaBean>();
		
		/* Cada linha dessa Lista ira conter a o id de uma aula e a lista de alunos presentes nesse aula. (Um objeto AulaPresencaBean) */
		for (Aula aulaAux : todasAulas) {
			aulaPresencaBeanAux = new AulaPresencaBean();
			aulaPresencaBeanAux.setIdAula( aulaAux.getId() );
			aulaPresencaBeanAux.setAlunosPresentesNaAula( aulaAux.getAlunosPresentes() );
			
			matrizAulaPresenca.add( aulaPresencaBeanAux );
		}
		
		uiModel.addAttribute("active", "lista_presenca");
		uiModel.addAttribute("aulas", todasAulas);
		uiModel.addAttribute("alunos", alunosDaUC);
		uiModel.addAttribute("matrizAulaPresenca", matrizAulaPresenca);
		
		return "listaPresenca";
	}
	
	@RequestMapping(value = "listaAlunos", method = RequestMethod.GET)
	public String exibirListaAlunos(Model uiModel) {
		uiModel.addAttribute("active", "lista_alunos");
		uiModel.addAttribute("alunos", alunoDAO.getAll());
		uiModel.addAttribute("alunosPresentes", aulaDAO.getAll().get(0).getAlunosPresentes());
		
		return "listaAlunos";
	}
	
	@RequestMapping(value = "formUC", method = RequestMethod.GET)
	public String exibirFormUC(Model uiModel) {
		uiModel.addAttribute("active", "form_uc");
		//Listar Alunos no comboBox
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
		
		ArrayList<Key<Aluno>> alunosPresentesIDsAux;
		HashMap< Long, ArrayList<Key<Aluno>> > mapaAulaAlunosPresentes = new HashMap<Long, ArrayList<Key<Aluno>>>();
		Iterator it;
		Long idAulaAux, idAlunoAux;
		String[] strSplitAux;
		String[] teste = request.getParameterValues("presente");

		for (int i = 0; (teste!=null) && (i < teste.length); i++) {
			
			strSplitAux = teste[i].split(":"); /* Posicao 1: Id da Aula, Posicao 2: Id do Aluno presente nessa aula */
			idAulaAux = Long.parseLong(strSplitAux[0]);
			idAlunoAux = Long.parseLong(strSplitAux[1]);
			
			if ( !mapaAulaAlunosPresentes.containsKey( idAulaAux ) ) {
				alunosPresentesIDsAux = new ArrayList<Key<Aluno>>();
				mapaAulaAlunosPresentes.put(idAulaAux, alunosPresentesIDsAux);
			}
			
			alunosPresentesIDsAux = mapaAulaAlunosPresentes.get( idAulaAux );
			alunosPresentesIDsAux.add( alunoDAO.findById( idAlunoAux ).getKey() );
			mapaAulaAlunosPresentes.put( idAulaAux , alunosPresentesIDsAux );
		}

		it = mapaAulaAlunosPresentes.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        
	        Aula aulaAux = aulaDAO.findById((Long) pairs.getKey());
	        aulaAux.setIdAlunosPresentes( (ArrayList<Key<Aluno>>) pairs.getValue() );
	        aulaDAO.save(aulaAux);
	    }
		
		return "redirect:"+exibirListaDePresenca(uiModel);
	}
}
