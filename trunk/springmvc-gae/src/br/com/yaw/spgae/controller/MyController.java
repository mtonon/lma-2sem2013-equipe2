package br.com.yaw.spgae.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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
import br.com.yaw.spgae.dao.NotaDAO;
import br.com.yaw.spgae.dao.UCDAO;
import br.com.yaw.spgae.model.Aluno;
import br.com.yaw.spgae.model.Aula;
import br.com.yaw.spgae.model.Nota;
import br.com.yaw.spgae.model.UC;

import com.googlecode.objectify.Key;
@RequestMapping(value="/")
@Controller
public class MyController {

	@Autowired
	private AlunoDAO alunoDAO;
	@Autowired
	private AulaDAO aulaDAO;
	@Autowired
	private UCDAO ucDAO;
	@Autowired
	private NotaDAO notaDAO;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model uiModel) {

		List<UC> todasUCs = ucDAO.getAll();

		uiModel.addAttribute("ucs", todasUCs);
		uiModel.addAttribute("active", "lista_uc");

		return "listaUC";
	}


	@RequestMapping(value = "listaUC", method = RequestMethod.GET)
	public String exibirListaDeUCs(Model uiModel) {

		List<UC> todasUCs = ucDAO.getAll();

		uiModel.addAttribute("ucs", todasUCs);
		uiModel.addAttribute("active", "lista_uc");

		return "listaUC";
	}

	@RequestMapping(value = "listaPresenca", method = RequestMethod.GET)
	public String exibirListaDePresenca(Model uiModel, HttpServletRequest request, Long idUC ) {

		Long ucID;
		String[] parameterValues = null;
		UC ucSelecionada;

		if( request!=null && 
				(parameterValues = request.getParameterValues("linkUC")) != null &&
				parameterValues.length > 0)
		{
			ucID = Long.parseLong(parameterValues[0]);
		}
		else{
			ucID = idUC;
		}

		ucSelecionada = ucDAO.findById(ucID);

		AulaPresencaBean aulaPresencaBeanAux;
		List<Aula> aulasDaUC = ucSelecionada.getAulasDaUC();
		List<Aluno> alunosDaUC = ucSelecionada.getAlunosDaUC();
		ArrayList<AulaPresencaBean> matrizAulaPresenca = new ArrayList<AulaPresencaBean>();

		aulasDaUC = ordenaAulasPorData(aulasDaUC);

		/* Cada linha dessa Lista ira conter a o id de uma aula e a lista de alunos presentes nesse aula. (Um objeto AulaPresencaBean) */
		for (Aula aulaAux : aulasDaUC) {
			aulaPresencaBeanAux = new AulaPresencaBean();
			aulaPresencaBeanAux.setIdAula( aulaAux.getId() );
			aulaPresencaBeanAux.setAlunosPresentesNaAula( aulaAux.getAlunosPresentes() );

			matrizAulaPresenca.add( aulaPresencaBeanAux );
		}

		uiModel.addAttribute("uc", ucSelecionada);
		uiModel.addAttribute("aulas", aulasDaUC);
		uiModel.addAttribute("alunos", alunosDaUC);
		uiModel.addAttribute("matrizAulaPresenca", matrizAulaPresenca);

		return "listaPresenca";
	}

	@RequestMapping(value = "listaAlunos", method = RequestMethod.GET)
	public String exibirListaAlunos(Model uiModel) {
		uiModel.addAttribute("active", "lista_alunos");
		uiModel.addAttribute("alunos", alunoDAO.getAll());
//		uiModel.addAttribute("alunosPresentes", aulaDAO.getAll().get(0).getAlunosPresentes());

		return "listaAlunos";
	}

	@RequestMapping(value = "formUC", method = RequestMethod.GET)
	public String exibirFormUC(Model uiModel) {
		uiModel.addAttribute("active", "form_uc");
		uiModel.addAttribute("alunos", alunoDAO.getAll());
		return "formUC";
	}

	@RequestMapping(value = "incluirUC", method = RequestMethod.POST)
	public String incluirUC(Model uiModel, UC novaUC, HttpServletRequest request) {
		
		int numProvas, numTrabalhos;
		String idAlunos[] = request.getParameterValues("selecionado");
		
		for(int i = 0; i < idAlunos.length; i++){
			novaUC.addAlunoUC(alunoDAO.findById(Long.parseLong(idAlunos[i])));
		}
		novaUC = ucDAO.save(novaUC);
		
		numProvas = novaUC.getNumProvas();
		numTrabalhos = novaUC.getNumTrabalhos();
		
		for(int i = 0; i < numProvas; i++)
		{
			Nota novaProva = new Nota();
			
			novaProva.setIdUC(novaUC.getKey());
			novaProva.setNome("Prova " + (i+1));
			novaProva.setValor(0.0);
			
			notaDAO.save(novaProva);
		}
		
		for(int i = 0; i < numTrabalhos; i++)
		{
			Nota novoTrabalho = new Nota();
			
			novoTrabalho.setIdUC(novaUC.getKey());
			novoTrabalho.setNome("Trabalho " + (i+1));
			novoTrabalho.setValor(0.0);
			
			notaDAO.save(novoTrabalho);
		}

		return "redirect: listaUC";
	}

	@RequestMapping(value = "formAluno", method = RequestMethod.GET)
	public String exibirFormAluno(Model uiModel) {
		uiModel.addAttribute("active", "form_aluno");
		return "formAluno";
	}

	@RequestMapping(value = "/incluirAluno", method = RequestMethod.POST)
	public String incluirAluno(Model uiModel, Aluno novoAluno, HttpServletRequest request) {

		alunoDAO.save(novoAluno);
		return "redirect: listaAlunos";
	}

	@RequestMapping(value = "listaPresencaAction", method = RequestMethod.POST)
	public String salvarPresenca(Model uiModel, HttpServletRequest request) {

		if(request.getParameterValues("botao")[0].equals("salvarPresenca")){

			System.out.println();

			ArrayList<Key<Aluno>> alunosPresentesIDsAux;
			HashMap< Long, ArrayList<Key<Aluno>> > mapaAulaAlunosPresentes = new HashMap<Long, ArrayList<Key<Aluno>>>();
			Iterator it;
			Long idAulaAux = 0l, idAlunoAux;
			String[] strSplitAux;
			String[] checkBoxValues = request.getParameterValues("presente");

			for (int i = 0; (checkBoxValues!=null) && (i < checkBoxValues.length); i++) {

				strSplitAux = checkBoxValues[i].split(":"); /* Posicao 1: Id da Aula, Posicao 2: Id do Aluno presente nessa aula */
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

			return exibirListaDePresenca(uiModel, null, aulaDAO.findById( idAulaAux ).getUc().getId() );
		}
		else{
			return "listaNotas";
		}
	}

	@RequestMapping(value = "criarNovaAula", method = RequestMethod.POST)
	public String criarNovaAula(Model uiModel, HttpServletRequest request) {

		Long idUC = Long.parseLong(request.getParameterValues("btnCriarNovaAula")[0]);
		Calendar dataAtual = Calendar.getInstance();

		Aula novaAula = new Aula();

		novaAula.setData(dataAtual.get(Calendar.DAY_OF_MONTH) + "/" + 
				(dataAtual.get(Calendar.MONTH)+1) + "/" +
				dataAtual.get(Calendar.YEAR) );

		novaAula.setUc( ucDAO.findById(idUC).getKey() );

		novaAula.setIdAlunosPresentes(ucDAO.findById(idUC).getIdAlunos());

		aulaDAO.save(novaAula);

		return exibirListaDePresenca(uiModel, null, novaAula.getUc().getId() );
	}

	private List<Aula> ordenaAulasPorData(List<Aula> aulas)
	{

		Collections.sort(aulas, new Comparator<Aula>() {
			@Override
			public int compare(Aula o1, Aula o2) {
				String o1StrAux, o2StrAux;
				String[] splitedStringAux;

				splitedStringAux = o1.getData().split("/");
				o1StrAux = splitedStringAux[2] + splitedStringAux[1] +
						splitedStringAux[0];

				splitedStringAux = o2.getData().split("/");
				o2StrAux = splitedStringAux[2] + splitedStringAux[1] +
						splitedStringAux[0];

				return o1StrAux.compareTo(o2StrAux);
			}
		});

		return aulas;
	}
}
