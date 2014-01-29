package br.com.yaw.spgae.controller;

import java.text.DecimalFormat;
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

		return "listaAlunos";
	}

	@RequestMapping(value = "formUC", method = RequestMethod.GET)
	public String exibirFormUC(Model uiModel) {
		uiModel.addAttribute("active", "form_uc");
		uiModel.addAttribute("alunos", alunoDAO.getAll());
		return "formUC";
	}

	//TODO Aqui
	@RequestMapping(value = "incluirUC", method = RequestMethod.POST)
	public String incluirUC(Model uiModel, UC novaUC, HttpServletRequest request) {
		
		String idAlunos[] = request.getParameterValues("selecionado");
		
		for(int i = 0; i < idAlunos.length; i++){
			
			Aluno alunoAux = alunoDAO.findById(Long.parseLong(idAlunos[i]));
			
			novaUC.addAlunoUC(alunoAux);
			
			novaUC = ucDAO.save(novaUC);
			
			for(int j = 0; j < novaUC.getNumProvas(); j++)
			{
				Nota novaProva = new Nota();
				
				novaProva.setIdUC(novaUC.getKey());
				novaProva.setNome("Prova " + (i+1));
//				novaProva.setValor(0.0);
				novaProva.setValor(Math.random()*10);
				novaProva.setIdAluno(alunoAux.getKey());
				
				notaDAO.save(novaProva);
			}
			
			for(int j = 0; j < novaUC.getNumTrabalhos(); j++)
			{
				Nota novoTrabalho = new Nota();
				
				novoTrabalho.setIdUC(novaUC.getKey());
				novoTrabalho.setNome("Trabalho " + (i+1));
//				novoTrabalho.setValor(0.0);
				novoTrabalho.setValor(Math.random()*10);
				novoTrabalho.setIdAluno(alunoAux.getKey());
				
				notaDAO.save(novoTrabalho);
			}
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

		if((request.getParameterValues("botao")[0]).split(":")[0].equals("salvarPresenca")){

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
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			UC uc = ucDAO.findById(Long.parseLong((request.getParameterValues("botao")[0]).split(":")[1]));
			List<Aluno> alunosDaUC = uc.getAlunosDaUC();
			List<AlunoNotaBean> alunoNotaBeans = new ArrayList<AlunoNotaBean>();
			ArrayList<String> tableHeadList = new ArrayList<String>();
			
			/* Dados do Header da tabela */
			for(int i = 1; i <= uc.getNumProvas(); i++)
			{
				tableHeadList.add("Prova " + i);
			}
			for(int i = 1; i <= uc.getNumTrabalhos(); i++)
			{
				tableHeadList.add("Trabalho " + i);
			}
			tableHeadList.add("Média");
			
			/* Alunos e notas (Bean) */
			for (Aluno aluno : alunosDaUC) {
				
				double mediaProvas = 0.0, mediaTrabalhos = 0.0;
				AlunoNotaBean alunoNotaAux = new AlunoNotaBean();
				List<Nota> provasAluno = new ArrayList<Nota>();
				List<Nota> trabalhosAluno = new ArrayList<Nota>();
				List<String> notasString = new ArrayList<String>();
				
				alunoNotaAux.setIdAluno( aluno.getId() );
				alunoNotaAux.setNomeAluno( aluno.getNome() );
				
				provasAluno = aluno.getProvasAluno(uc.getKey());
				trabalhosAluno = aluno.getTrabalhosAluno(uc.getKey());
				
				for (Nota nota : provasAluno) {
					notasString.add(decimalFormat.format(nota.getValor()));
					mediaProvas += nota.getValor();
				}
				mediaProvas = (mediaProvas/provasAluno.size());
				
				for (Nota nota : trabalhosAluno) {
					notasString.add(decimalFormat.format(nota.getValor()));
					mediaTrabalhos += nota.getValor();
				}
				mediaTrabalhos = (mediaTrabalhos/trabalhosAluno.size());
				
				notasString.add(decimalFormat.format( (uc.getPesoProvas()*mediaProvas) + (uc.getPesoTrabalhos()*mediaTrabalhos)));
				alunoNotaAux.setNotasAluno(notasString);
				
				alunoNotaBeans.add(alunoNotaAux);
			}
			
			uiModel.addAttribute("tableHeadList", tableHeadList);
			uiModel.addAttribute("alunoNotaBeans", alunoNotaBeans);
			
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
